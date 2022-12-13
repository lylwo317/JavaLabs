#!/bin/sh
DIR="tmp_"$$
JAR=$(echo $1 | tr '.' ' ' | awk '{ print $1 }')
#FILENAME=$(basename "$1")
PACKAGE_NAME=$(grep "package" $1 |awk '{print $2}' | tr ';' '.')
CLASS_NAME=$(grep -i "public class" $1 | awk '{ print $3 }')

rm -rf "$JAR".jar
mkdir -p $DIR/"$(dirname "$1")"
cp "$1" $DIR/"$1"
cd $DIR || exit
echo "** Compiling java file..."
javac -d . -g $1
echo "** Creating temporary jar..."
jar -cvfe Temp.jar "$PACKAGE_NAME""$CLASS_NAME" "${PACKAGE_NAME//\./\/}"*.class
echo "** Creating DEX file..."
#dx --dex --output=../classes.dex Temp.jar
d8 Temp.jar --output ../
#echo "** Creating Android compatible file..."
#aapt add ../$JAR.jar classes.dex
cd ..
rm -rf $DIR
echo "** Finished!"
echo ""
echo "Copy to your device"
echo ""
adb push classes.dex /data/local/tmp
echo ""
#echo "adb shell"
adb shell /system/bin/dalvikvm -classpath /data/local/tmp/classes.dex "$PACKAGE_NAME""$CLASS_NAME"