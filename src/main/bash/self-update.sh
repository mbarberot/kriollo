#!/usr/bin/env bash

codegen_dir=codegen
archive=kriollo.tar.gz
tmp=/tmp/kriollo
tmp_dir=${tmp}/self-update

echo "Kriollo"
echo "-------"
echo
echo "> Self update"

echo
echo "-> Find the last version"
base_url=https://gitlab.com/api/v4/projects/61145249
last_package_id=$(curl -XGET ${base_url}/packages\?sort\=desc 2>/dev/null | jq '.[0].id')
last_package_version=$(curl -XGET ${base_url}/packages\?sort\=desc 2>/dev/null | jq --raw-output '.[0].version')
last_package_file_id=$(curl -XGET ${base_url}/packages/${last_package_id}/package_files 2>/dev/null | jq '.[0].id')
download_url=https://gitlab.com/mbarberot/kriollo/-/package_files/${last_package_file_id}/download

echo
echo "-> Clean temporary files"
rm -rvf -- ${tmp}

echo
echo "-> Downloading the last version"
mkdir -vp ${tmp_dir}
echo "curl ${download_url} --output ${tmp_dir}/${archive} 2>/dev/null"
curl ${download_url} --output ${tmp_dir}/${archive} 2>/dev/null

echo
echo "-> Extract archive"
echot "tar -xzvf ${tmp_dir}/${archive} -C ${tmp_dir}"
tar -xzvf ${tmp_dir}/${archive} -C ${tmp_dir}

echo
echo "-> Install into ${codegen_dir}"

archive_extracted=kriollo-${last_package_version}
cp -v ${tmp_dir}/${archive_extracted}/codegen/kriollo ${codegen_dir}/
cp -v ${tmp_dir}/${archive_extracted}/codegen/kriollo.jar ${codegen_dir}/

echo
echo "> Done"
exit 0