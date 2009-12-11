#!/usr/bin/env bash

TMPFILE=`mktemp`
find src -name *.java > $TMPFILE

if [ -e $TMPFILE ];
then
    javac -verbose -Xlint -d classes @$TMPFILE
    javadoc -d doc @$TMPFILE
    rm -f $TMPFILE
fi