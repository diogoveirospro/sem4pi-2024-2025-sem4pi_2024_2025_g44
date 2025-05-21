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

# Apply dos2unix to all files in the directory and subdirectories
find "$1" -type f | while read -r file; do
  echo "Converting $file..."
  dos2unix "$file"
done

echo "Conversion complete."