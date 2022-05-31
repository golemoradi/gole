#!/bin/bash
#removing the space in the codebook.txt so that it is one long string
echo `cat $1` | tr -d ' ' > c0debook.txt
#translating the message using the concatenated codebook as the second set
echo `cat $2` | tr a-zA-Z `cat c0debook.txt` 