#!/bin/bash

file=IntAdder.java

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

run_test_grep() {
    (( ++total ))
    echo -n "Running test $total, looking for '$1' in code..."
    line=$( grep -n "$1" "$file" 2>/dev/null )
    if [ $? -eq 0 ]; then
        echo -e "success\nFound:\nLine $line\n"
        (( ++num_right ))
    else
        echo -e "failure\nRequired keyword '$1' not found in source code.\n"
    fi
}

run_test_args "" "Usage: java IntAdder <list of integers>"
run_test_grep "toString()"
run_test_args "-1" "-1"
run_test_args "1" "1"
run_test_args "x" "Error: Cannot parse integer 'x'."
run_test_args "1 2 3 3.5" "Error: Cannot parse integer '3.5'."
run_test_args "1 2 3 4" "1 + 2 + 3 + 4 = 10"
run_test_args "-1 -2 -3 -4" "-4 + -3 + -2 + -1 = -10"
run_test_args "-100000 50000 50000 -100000" "-100000 + -100000 + 50000 + 50000 = -100000"
run_test_args "10 9 8 7 6 5 4 3 2 1 0 -1 -2 -3 -4 -5 -6 -7 -8 -9 -10" "-10 + -9 + -8 + -7 + -6 + -5 + -4 + -3 + -2 + -1 + 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 = 0"

echo -e "\nTotal tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
