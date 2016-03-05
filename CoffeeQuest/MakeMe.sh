#! /bin/bash

# Compile everything

javac -d "./" src/*.java
javac -d "./" -cp ./:./junit-4.12.jar:./mockito-all-1.10.19.jar tests/*.java
