@echo off
setlocal enabledelayedexpansion

echo LOG: Generate PlantUML Diagrams
set "exportFormat=svg"
:: set "monochrome=true"
set "extra=-SdefaultFontSize=20"
:: set "extra=-SdefaultFontName=Times New Roman -SdefaultFontSize=10"
set "outputDir=../images"

for /R "docs" %%F in (*.puml) do (
    echo Processing file: %%F
    java -jar libs\plantuml-1.2023.1.jar !extra! -t!exportFormat! -o !outputDir! "%%F"
)

echo Finished
