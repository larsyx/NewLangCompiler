#!/bin/bash

cd test_files/c_out
gcc -o programma.exe $1 -lm
./programma.exe