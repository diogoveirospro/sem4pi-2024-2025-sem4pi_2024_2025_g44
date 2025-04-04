#!/bin/bash

echo "Running Maven tests..."
mvn test -DfailIfNoTests=false -X

# Check exit code
if [ $? -eq 0 ]; then
    echo "Tests completed successfully!"
else
    echo "Tests failed. Check the output above."
    exit 1
fi
