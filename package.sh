#!/bin/bash

BUILD_TYPE=$1

if [ $# -eq 0 ]
	then
		BUILD_TYPE="DEV"
fi

rm -f -r artifacts
mkdir artifacts

cp -r res artifacts/res
cp desktop/build/libs/desktop-SNAPSHOT.jar "artifacts/desktop-${BUILD_TYPE}.jar"

rm -r -f release
mkdir release

zip -r "release/desktop-${BUILD_TYPE}.zip" artifacts
