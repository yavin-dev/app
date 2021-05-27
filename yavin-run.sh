#!/bin/sh

set -xe
artifact=${1:-"yavin-app"}
profile_opt=${2:-''}
download_only=${3:-''}
download_loc="/usr/local/lib/"
version=$(curl -s http://search.maven.org/solrsearch/select?q=g:"dev.yavin"+AND+a:"${artifact}" | tr "," "\n" | awk -F'[":]' '/"latestVersion"/{print $5}')

curl https://repo1.maven.org/maven2/dev/yavin/${artifact}/${version}/${artifact}-${version}.jar -o ${download_loc}/${artifact}-${version}.jar
cmd="java -jar ${download_loc}/${artifact}-${version}.jar"
[[ ! -z ${profile_opt} ]] && cmd="${cmd} --spring.profiles.active=${profile_opt}"
[[ -z ${download_only} ]] && echo ${cmd} && eval ${cmd}
