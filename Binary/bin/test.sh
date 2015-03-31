#!/bin/bash

file=BinaryEncoder.java

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

run_test_args_file() {
    (( ++total ))
    local filename=$( echo "$1" | cut -d ' ' -f1 )
    rm -f "$filename"
    echo -n "Running test $total..."
    expected=$2
    received=$( eval $command $1 2>&1 | tr -d '\r' )
    if [ -f "$filename" ]; then
        received=$( cat "$filename" | tr -d '\r' )
    fi
    if [ "$expected" = "$received" ]; then
        echo "success"
        (( ++num_right ))
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
    rm -f "$filename"
}

run_test_args_file "" "Usage: java BinaryEncoder <output file> <message>"
run_test_args_file "output.txt" "Usage: java BinaryEncoder <output file> <message>"
run_test_args_file "output.txt Hi Hi Hi" "Usage: java BinaryEncoder <output file> <message>"
run_test_args_file "output.txt Hi" "0100100001101001"
run_test_args_file "output.txt \"This is cool.\"" "01010100011010000110100101110011001000000110100101110011001000000110001101101111011011110110110000101110"
run_test_args_file "output.txt \"This output contains multiple lines.\nThis is line 2. This is line 3.\"" "010101000110100001101001011100110010000001101111011101010111010001110000011101010111010000100000011000110110111101101110011101000110000101101001011011100111001100100000011011010111010101101100011101000110100101110000011011000110010100100000011011000110100101101110011001010111001100101110010111000110111001010100011010000110100101110011001000000110100101110011001000000110110001101001011011100110010100100000001100100010111000100000010101000110100001101001011100110010000001101001011100110010000001101100011010010110111001100101001000000011001100101110"
run_test_args_file "numbers.txt 0123456789" "00110000001100010011001000110011001101000011010100110110001101110011100000111001"
run_test_args_file "letters.txt ABCDEFGHIJKLMNOPQRSTUVWXYZ" "0100000101000010010000110100010001000101010001100100011101001000010010010100101001001011010011000100110101001110010011110101000001010001010100100101001101010100010101010101011001010111010110000101100101011010"
run_test_args_file "letters.txt abcdefghijklmnopqrstuvwxyz" "0110000101100010011000110110010001100101011001100110011101101000011010010110101001101011011011000110110101101110011011110111000001110001011100100111001101110100011101010111011001110111011110000111100101111010"
run_test_args_file "chars.txt \"~!@#$%^&*()+[]=-<>?\"" "01111110001000010100000000100011001001000010010101011110001001100010101000101000001010010010101101011011010111010011110100101101001111000011111000111111"

echo -e "\nTotal tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
