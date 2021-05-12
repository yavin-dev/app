#!/bin/sh

set -xe
artifact=${1:-"app"}
version=$(curl -s http://search.maven.org/solrsearch/select?q=g:"dev.yavin"+AND+a:"${artifact}" | tr "," "\n" | awk -F'[":]' '/"latestVersion"/{print $5}')

curl https://repo1.maven.org/maven2/dev/yavin/${artifact}/${version}/${artifact}-${version}.jar -o ${artifact}-${version}.jar
java -jar ${artifact}-${version}.jar
