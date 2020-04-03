#!/bin/bash

[ -e "bin/gen.sh" ] || exit 127
[ "$1" == "yes" ] || exit 127

echo "generating ..."
mkdir -p tools
mkdir -p gen

[ -e "tools/swagger-codegen-cli.jar" ] || wget https://repo1.maven.org/maven2/io/swagger/swagger-codegen-cli/2.4.13/swagger-codegen-cli-2.4.13.jar -O tools/swagger-codegen-cli.jar

java -jar tools/swagger-codegen-cli.jar generate -l spring -i api/sosbackend.v1.yaml -c config/sosb.json  -o gen

