#!/bin/sh
# Fallback launcher (no NetBeans needed): compiles and runs. Requires JDK 11+.
cd "$(dirname "$0")"
mkdir -p build/classes
find src -name '*.java' > build/sources.txt
javac -encoding UTF-8 --release 11 -d build/classes @build/sources.txt || exit 1
cp src/hiking/db/*.sql src/hiking/db/*.csv build/classes/hiking/db/
java -cp build/classes hiking.Main
