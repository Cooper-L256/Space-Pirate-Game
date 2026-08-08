#!/bin/bash
if [ -z "$1" ]; then
    echo "Usage: run.sh className"
    exit 1
fi
java -cp ".:lib/*" --enable-native-access=ALL-UNNAMED "$@"