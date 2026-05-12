# Brute Force Password Cracker

## Overview
For my final CS1181 project I was tasked with utilizing threads to crack into a password protected zip file. There are two difficulty levels. protected3.zip and protected5.zip. These files have a 3 lowercase letter password and 5 lowercase letter password respectively.
## Purpose
The purpose of this project was to learn to chain threads together to work on a larger task as well as using recursion to generate all possible passwords.
## Usage
Upon running the program, all possible 3 letter passwords are generated and tested. Once found, the password and the time to execute are outputted. This process is very fast. After that, the 5 letter sequence is ran. This process will take a long time depending on how many threads are partitioned. 