#!/bin/sh

set -xe
artifact=${1:-"yavin-app"}
profile_opt=${2:-''}
version=$(curl -s http://search.maven.org/solrsearch/select?q=g:"dev.yavin"+AND+a:"${artifact}" | tr "," "\n" | awk -F'[":]' '/"latestVersion"/{print $5}')

curl https://repo1.maven.org/maven2/dev/yavin/${artifact}/${version}/${artifact}-${version}.jar -o ${artifact}-${version}.jar
cmd="java -jar ${artifact}-${version}.jar"
[[ ! -z ${profile_opt} ]] && cmd="${cmd} --spring.profiles.active=${profile_opt}"
echo ${cmd} && eval ${cmd}
