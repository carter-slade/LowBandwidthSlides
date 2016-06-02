#!/bin/bash
mkdir ./images
ffmpeg -i $1 -vf fps=1 ./images/image-%07d.jpg
javac image_compare.java
java image_compare
rm -r ./images
