#!/bin/bash

# Navigate to your Android project directory
cd /home/master/AndroidStudioProjects/app || {
  echo "❌ Project directory not found!"
  exit 1
}

echo "🧹 Cleaning build and KSP caches..."

# Delete build and KSP-related caches
rm -rf build/ .gradle/ .idea/ .kotlin/ ksp/ kspCaches/ || echo "⚠️ Some folders might not exist. Skipping."

# Optional: remove Kotlin cache in root dir too
cd ..
rm -rf build/ .gradle/ ksp/ kspCaches/

echo "✅ Cache cleared."

echo "🔁 Running Gradle clean..."
./gradlew clean || {
  echo "❌ Gradle clean failed!"
  exit 1
}

echo "🔨 Rebuilding the project..."
./gradlew assembleDebug || {
  echo "❌ Build failed!"
  exit 1
}

echo "✅ Build completed successfully!"

