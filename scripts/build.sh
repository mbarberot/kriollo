#!/usr/bin/env bash

# /!\ warning
# this file is generated by kriollo, do not edit it manually

echo "> Building jar"
mvn clean package -DskipTests

if [[ $? > 0 ]]
then
    echo
    echo "> ! Build failed"
    exit 1
else
    echo
    echo "> Copy jar into codegen dir"
    cp target/kriollo.jar codegen/
fi

if [[ $? > 0 ]]
then
    echo
    echo "! Build failed"
    exit 1
else
    echo
    echo "> Build successful"
    exit 0
fi