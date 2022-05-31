#!/bin/bash
#removing the space in the codebook.txt so that it is one long string
echo `cat $1` | tr -d ' ' > c0debook.txt
#decryptes by using the codebook as the first set
echo `cat $2` | tr `cat c0debook.txt` a-zA-Z