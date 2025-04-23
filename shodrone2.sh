#!/usr/bin/env bash
set -e  # Exit if any command fails

echo "Checking if build is necessary..."

# Define paths
PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
TARGET_DIR="$PROJECT_DIR/shodrone.app.user/target"
JAR_FILE="$TARGET_DIR/shodrone.app.user-0.1.0.jar"
HASH_FILE="$PROJECT_DIR/hash_builds/.build_hash"
EXECUTABLE_FILE="shodrone.app.user-0.1.0.jar"

# Compute the current source hash (excluding target directory!)
SOURCE_HASH=$(find "$PROJECT_DIR" -type f \( -name "*.java" -o -name "pom.xml" \) ! -path "*/target/*" -exec sha256sum {} + | sort -k2 | sha256sum | awk '{ print $1 }')

# Remove all spaces from the SOURCE_HASH
SOURCE_HASH=$(echo "$SOURCE_HASH" | tr -d '[:space:]')

echo "Computed Source Hash: $SOURCE_HASH"

# Load the previous hash (if exists)
if [ -f "$HASH_FILE" ]; then
    PREVIOUS_HASH=$(cat "$HASH_FILE")
    # Remove all spaces from the PREVIOUS_HASH
    PREVIOUS_HASH=$(echo "$PREVIOUS_HASH" | tr -d '[:space:]')
    echo "Previous Source Hash: $PREVIOUS_HASH"
else
    PREVIOUS_HASH=""
    echo "No previous hash found. This may be the first run."
fi

# Check if JAR exists and compare hashes
if [ -f "$JAR_FILE" ] && [ "$SOURCE_HASH" == "$PREVIOUS_HASH" ]; then
    echo "No changes detected in source files. Skipping build."
else
    echo "Changes detected or first-time build required. Running build-all.sh..."

    # Run build script and only save the hash if successful
    if bash "$PROJECT_DIR/build-all.sh" "$1"; then
        echo "$SOURCE_HASH" > "$HASH_FILE"
        sync  # Ensure the hash file is actually written before proceeding
        echo "Build completed successfully. Updated hash saved."
    else
        echo "Build failed. Not updating hash."
        exit 1  # Prevent running the JAR if the build failed
    fi
fi

# Run the application
echo "Running the application..."
cd "$TARGET_DIR" || exit
java -jar "$EXECUTABLE_FILE"
