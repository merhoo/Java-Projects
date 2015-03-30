#!/bin/bash

file=EstimateEandPi.java

if [ ! -f "$file" ]; then
    echo -e "Error: File '$file' not found.\nTest failed."
    exit 1
fi

num_right=0
total=0
line="________________________________________________________________________"
compiler=
interpreter=
language=
if [ "${file##*.}" = "py" ]; then
    if [ ! -z "$PYTHON_PATH" ]; then
        interpreter=$(which python.exe)
    else
        interpreter=$(which python3.2)
    fi
    command="$interpreter $file"
    echo -e "Testing $file\n"
elif [ "${file##*.}" = "java" ]; then
    language="java"
    command="java ${file%.java}"
    echo -n "Compiling $file..."
    javac $file
    echo -e "done\n"
fi

run_test() {
    (( ++total ))
    echo -n "Running test $total..."
    expected=$2
    received=$( echo -e $1 | $command 2>&1 | tr -d '\r' )
    if [ "$expected" = "$received" ]; then
        echo "success"
        (( ++num_right ))
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
}

run_test "" "Enter number of iterations: Error: Invalid input '' received."
run_test "x" "Enter number of iterations: Error: Invalid input 'x' received."
run_test "-1" "Enter number of iterations: e : 1.00000"$'\n'"pi: 4.00000"
run_test "0" "Enter number of iterations: e : 1.00000"$'\n'"pi: 4.00000"
run_test "1" "Enter number of iterations: e : 2.00000"$'\n'"pi: 2.66667"
run_test "10" "Enter number of iterations: e : 2.71828"$'\n'"pi: 3.23232"
run_test "100" "Enter number of iterations: e : 2.71828"$'\n'"pi: 3.15149"
run_test "1000" "Enter number of iterations: e : 2.71828"$'\n'"pi: 3.14259"
run_test "10000" "Enter number of iterations: e : 2.71828"$'\n'"pi: 3.14169"
run_test "100000" "Enter number of iterations: e : 2.71828"$'\n'"pi: 3.14160"
run_test "1000000" "Enter number of iterations: e : 2.71828"$'\n'"pi: 3.14159"
run_test "10000000" "Enter number of iterations: e : 2.71828"$'\n'"pi: 3.14159"

echo -e "\nTotal tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
