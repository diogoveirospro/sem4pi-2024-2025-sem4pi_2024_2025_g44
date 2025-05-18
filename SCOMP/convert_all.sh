#!/bin/bash

# Check if directory name is provided
if [ -z "$1" ]; then
  echo "Usage: ./convert_all.sh <directory_name>"
  exit 1
fi

# Check if directory exists
if [ ! -d "$1" ]; then
  echo "Error: Directory '$1' does not exist."
  exit 1
fi

# Apply dos2unix to all files in the directory
for file in "$1"/*; do
  if [ -f "$file" ]; then
    echo "Converting $file..."
    dos2unix "$file"
  fi
done

echo "Conversion complete."
