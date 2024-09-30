#!/usr/bin/env bash

# ARGS

TAG=$1

# UTILS

function usage() {
    echo "Usage : release.sh <TAG>"
}

function failed() {
  echo ""
  echo "$1"
}

# CLI CHECK

if [[ ${TAG} == "" ]]
then
  usage
  exit 1
fi

# UPDATE VERSION

sed -i '0,/version .*/s//version "'${TAG}'"/' codegen/codegen.kdl
# > regenerate code
./codegen/kriollo build
# > rebuild
./scripts/build.sh
# > regenerate with version
./codegen/kriollo build
# > commit
git add .
git commit -m "chore(release): prepare for ${TAG}"

# GIT CHECK

git diff --cached --exit-code
if [[ $? != 0 ]]
then
  failed "You must commit your changes first"
  exit 2
fi

# CREATE TAG

git tag ${TAG}
if [[ $? != 0 ]]
then
  failed "Cannot release, see previous errors"
  exit 2
fi
