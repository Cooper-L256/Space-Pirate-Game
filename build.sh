#!/bin/bash
if [ -z "$1" ]; then
    echo "Usage: build.sh SourceFile.java"
    exit 1
fi
javac -cp ".:lib/*" "$@"