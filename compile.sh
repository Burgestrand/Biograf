#!/usr/bin/env bash

TMPFILE=`mktemp`
find src -name *.java > $TMPFILE

if [ -e $TMPFILE ];
then
    javac -Xlint -d classes @$TMPFILE
    javadoc -d doc @$TMPFILE
    rm -f $TMPFILE
fi