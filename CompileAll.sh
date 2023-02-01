#!/bin/bash
cd test_files/c_out
for file in ./*;
do
  gcc -o "${file%.*}".exe "${file##*/}" -lm
done;

