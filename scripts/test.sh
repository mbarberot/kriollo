#!/usr/bin/env bash

# /!\ warning
# this file is generated by kriollo, do not edit it manually

echo "> Running tests"
mvn clean test $*

if [[ $? > 0 ]]
then
    echo
    echo "! Tests failed"
    exit 1
else
    echo
    echo "> Tests successful"
    exit 0
fi