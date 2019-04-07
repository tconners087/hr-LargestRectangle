# hr_LargestRectangle

This is a solution to the HackerRank challenge [Largest Rectangle](https://www.hackerrank.com/challenges/largest-rectangle/problem). The `main` function is different, as this implementation is meant to be run in a terminal and passed a file argument, but the algorithm works in their environment too. The input file format is slightly different than what is passed to the program on HackerRank's system (namely, my input files don't list the number of integers on the first line), but they are functionally identical.

## Features

The solution has O(n) time complexity where 'n' is the number of unique integers in an input file. It uses an iterative approach and, because I wanted to challenge myself, a single ArrayDeque object that functions solely as a Stack.

## How to use

This compiles using *javac* from `jdk1.8.0_201`. I'm confident it would probably compile with other versions, but I'm not sure. Once compiled, its usage is `java LargestRectangle <file_path>` where `file_path` points to a text file containing space-delimited integers. The `.txt` files in this repo are correctly formatted.

## Problem Description

The problem description as written on hackerrank.com will be provided in an attached PDF as soon as I can type it up.