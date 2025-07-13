#!/bin/bash

echo "🔪 Killing any Gradle/Kotlin/Java processes..."
pkill -f 'GradleDaemon' || true
pkill -f 'org.gradle' || true
pkill -f 'kotlin.daemon' || true
sleep 2

echo "🧹 Deleting build and KSP cache directories..."
rm -rf \
  ./app/build \
  ./app/.kotlin \
  ./app/ksp \
  ./app/kspCaches \
  .gradle \
  .idea/.gradle \
  ~/.gradle/caches \
  ~/.gradle/daemon \
  ~/.gradle/kotlin

echo "✅ Cache deleted. Now running full clean & build..."
./gradlew clean
./gradlew assembleDebug

