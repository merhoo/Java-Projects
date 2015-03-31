#!/bin/bash

file=QuadraticEquation.java

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

run_test_args() {
    (( ++total ))
    echo -n "Running test $total..."
    expected=$2
    received=$( $command $1 2>&1 | tr -d '\r' )
    if [ "$expected" = "$received" ]; then
        echo "success"
        (( ++num_right ))
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
}

run_test_args "" "Usage: java QuadraticEquation <a> <b> <c>"
run_test_args "1" "Usage: java QuadraticEquation <a> <b> <c>"
run_test_args "1 2" "Usage: java QuadraticEquation <a> <b> <c>"
run_test_args "1 2 3 4" "Usage: java QuadraticEquation <a> <b> <c>"
run_test_args "x 1 2" "Error: Invalid value 'x' for a."
run_test_args "1 x 2" "Error: Invalid value 'x' for b."
run_test_args "1 2 x" "Error: Invalid value 'x' for c."
run_test_args "0 1 1" "Error: Coefficient 'a' cannot be 0 in a quadratic equation."
run_test_args "1.0 2.0 3.0" "x^2 + 2.0x + 3.0 = 0"$'\n'"Discriminant: -8.000000"$'\n'"The equation has no real roots."
run_test_args "1.0 2.0 1.0" "x^2 + 2.0x + 1.0 = 0"$'\n'"Discriminant: 0.000000"$'\n'"Root 1: -1.000000"$'\n'"Root 2: -1.000000"
run_test_args "21 130 98" "21.0x^2 + 130.0x + 98.0 = 0"$'\n'"Discriminant: 8668.000000"$'\n'"Root 1: -0.878522"$'\n'"Root 2: -5.311955"
run_test_args "1 5 6" "x^2 + 5.0x + 6.0 = 0"$'\n'"Discriminant: 1.000000"$'\n'"Root 1: -2.000000"$'\n'"Root 2: -3.000000"
run_test_args "1 -5 6" "x^2 - 5.0x + 6.0 = 0"$'\n'"Discriminant: 1.000000"$'\n'"Root 1: 3.000000"$'\n'"Root 2: 2.000000"
run_test_args "2 4 0" "2.0x^2 + 4.0x = 0"$'\n'"Discriminant: 16.000000"$'\n'"Root 1: 0.000000"$'\n'"Root 2: -2.000000"
run_test_args "1 1 0" "x^2 + x = 0"$'\n'"Discriminant: 1.000000"$'\n'"Root 1: 0.000000"$'\n'"Root 2: -1.000000"
run_test_args "1 0 0" "x^2 = 0"$'\n'"Discriminant: 0.000000"$'\n'"Root 1: 0.000000"$'\n'"Root 2: 0.000000"
run_test_args "1 0 -1" "x^2 - 1.0 = 0"$'\n'"Discriminant: 4.000000"$'\n'"Root 1: 1.000000"$'\n'"Root 2: -1.000000"
run_test_args "-1 0 2" "-1.0x^2 + 2.0 = 0"$'\n'"Discriminant: 8.000000"$'\n'"Root 1: -1.414214"$'\n'"Root 2: 1.414214"

echo -e "\nTotal tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
