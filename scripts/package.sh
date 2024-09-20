#!/usr/bin/env bash

# ARGS

VERSION=$1

# UTILS

function usage() {
    echo "Usage : package.sh <TAG>"
}

function failed() {
  echo ""
  echo "$1"
}

# CLI CHECK

if [[ ${VERSION} == "" ]]
then
  usage
  exit 1
fi

# CREATE PACKAGE

BUILD_DIR="target"
PACKAGE_NAME="kriollo-${VERSION}"
PACKAGE_DIR="${BUILD_DIR}/${PACKAGE_NAME}"
JAR_FILE="${BUILD_DIR}/kriollo.jar"
SCRIPT_FILE="codegen/kriollo"

if [[ -d ${PACKAGE_DIR} ]]
then
  rm -r -- ${PACKAGE_DIR}
  if [[ $? != 0 ]]
  then
    failed "Cannot package, see previous errors"
    exit 2
  fi
fi

mkdir -p ${PACKAGE_DIR}
if [[ $? != 0 ]]
then
  failed "Cannot package, see previous errors"
  exit 2
fi

cp -v ${JAR_FILE} ${SCRIPT_FILE} ${PACKAGE_DIR}/
if [[ $? != 0 ]]
then
  failed "Cannot package, see previous errors"
  exit 2
fi

tar -czvf ${PACKAGE_DIR}.tar.gz -C ${BUILD_DIR} ${PACKAGE_NAME}
if [[ $? != 0 ]]
then
  failed "Cannot package, see previous errors"
  exit 2
fi

exit 0