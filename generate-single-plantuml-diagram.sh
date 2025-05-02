#! /bin/sh

if [ "$#" -ne 1 ]; then
  echo "Usage: $0 ./docs/SPRINT_2/USxxx/puml/filename.puml"
  exit 1
fi

echo "LOG: Generate Single Plantuml Diagram"

file="$1"

#inputDir=$(dirname "$file")

outputDir="../images"

exportFormat="svg"
#monochrome="true"
extra="-SdefaultFontSize=20"
#extra="-SdefaultFontName=Times New Roman -SdefaultFontSize=10"

mkdir -p "$outputDir"

#-Smonochrome=$monochrome
java -jar ./libs/plantuml-1.2023.1.jar $extra -t$exportFormat -o "$outputDir" "$file"

echo "File: $file"

echo "Finished"
