#!/bin/bash

file=Client.java

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
    received=$( echo -e $1 | $command 2>&1 | tr -d '\r')
    if [ "$expected" = "$received" ]; then
        echo "success"
        (( ++num_right ))
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
}

run_test "212\n100\n4\n86400" "Enter degrees in Fahrenheit: 212.00 F = 100.00 C"$'\n'"Enter degrees in Celsius: 100.00 C = 212.00 F"$'\n'"Enter number of days: 4.00 days = 345600 seconds"$'\n'"Enter number of seconds: 86400 seconds = 1.00 days"
run_test "x\nwho cares?\n3\n60" "Enter degrees in Fahrenheit: Error: Invalid input received."$'\n'"Enter degrees in Celsius: Error: Invalid input received."$'\n'"Enter number of days: 3.00 days = 259200 seconds"$'\n'"Enter number of seconds: 60 seconds = 0.00 days"
run_test "100.0\n37.78\n2.5\n172800" "Enter degrees in Fahrenheit: 100.00 F = 37.78 C"$'\n'"Enter degrees in Celsius: 37.78 C = 100.00 F"$'\n'"Enter number of days: 2.50 days = 216000 seconds"$'\n'"Enter number of seconds: 172800 seconds = 2.00 days"
run_test "0.0\n0.0\n0.0\n0.0" "Enter degrees in Fahrenheit: 0.00 F = -17.78 C"$'\n'"Enter degrees in Celsius: 0.00 C = 32.00 F"$'\n'"Enter number of days: 0.00 days = 0 seconds"$'\n'"Enter number of seconds: Error: Invalid input received."
run_test "0.0\n0.0\n0.0\n0" "Enter degrees in Fahrenheit: 0.00 F = -17.78 C"$'\n'"Enter degrees in Celsius: 0.00 C = 32.00 F"$'\n'"Enter number of days: 0.00 days = 0 seconds"$'\n'"Enter number of seconds: 0 seconds = 0.00 days"

echo -e "\nTotal tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
