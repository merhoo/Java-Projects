#!/bin/bash

file=IEEE754Converter.java

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

run_test_args "" "Usage: java IEEE754Converter <value> <conversion type>"$'\n'"   <conversion type> = S for single-precision, D for double-precision"
run_test_args "0.0" "Usage: java IEEE754Converter <value> <conversion type>"$'\n'"   <conversion type> = S for single-precision, D for double-precision"
run_test_args "0.0 S 2.3" "Usage: java IEEE754Converter <value> <conversion type>"$'\n'"   <conversion type> = S for single-precision, D for double-precision"
run_test_args "x s" "Error: Value 'x' is not a valid floating-point number."
run_test_args "1.0 q" "Error: Invalid conversion type 'q'."
run_test_args "0.0 s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|0|00000000|00000000000000000000000|"$'\n'"------------------------------------"$'\n'"Decimal value: 0.0"
run_test_args "-0.0 S" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|1|00000000|00000000000000000000000|"$'\n'"------------------------------------"$'\n'"Decimal value: -0.0"
run_test_args "0.0 d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|0|00000000000|0000000000000000000000000000000000000000000000000000|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: 0.0"
run_test_args "-0.0 D" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|1|00000000000|0000000000000000000000000000000000000000000000000000|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: -0.0"
run_test_args "NaN s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|0|11111111|10000000000000000000000|"$'\n'"------------------------------------"$'\n'"Decimal value: NaN"
run_test_args "NaN d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|0|11111111111|1000000000000000000000000000000000000000000000000000|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: NaN"
run_test_args "Infinity s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|0|11111111|00000000000000000000000|"$'\n'"------------------------------------"$'\n'"Decimal value: Infinity"
run_test_args "-Infinity s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|1|11111111|00000000000000000000000|"$'\n'"------------------------------------"$'\n'"Decimal value: -Infinity"
run_test_args "Infinity d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|0|11111111111|0000000000000000000000000000000000000000000000000000|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: Infinity"
run_test_args "-Infinity d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|1|11111111111|0000000000000000000000000000000000000000000000000000|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: -Infinity"
run_test_args "200.0 s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|0|10000110|10010000000000000000000|"$'\n'"------------------------------------"$'\n'"Decimal value: 200.0"
run_test_args "200.0 d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|0|10000000110|1001000000000000000000000000000000000000000000000000|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: 200.0"
run_test_args "123456.789 s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|0|10001111|11100010010000001100101|"$'\n'"------------------------------------"$'\n'"Decimal value: 123456.7890625"
run_test_args "123456.789 d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|0|10000001111|1110001001000000110010011111101111100111011011001001|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: 123456.789"
run_test_args "-1.79769313486231570E+308 d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|1|11111111110|1111111111111111111111111111111111111111111111111111|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: -1.7976931348623157E308"
run_test_args "1.79769313486231570E+308 d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|0|11111111110|1111111111111111111111111111111111111111111111111111|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: 1.7976931348623157E308"
run_test_args "-3.4028235E38 s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|1|11111110|11111111111111111111111|"$'\n'"------------------------------------"$'\n'"Decimal value: -3.4028234663852886E38"
run_test_args "3.4028235E38 s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|0|11111110|11111111111111111111111|"$'\n'"------------------------------------"$'\n'"Decimal value: 3.4028234663852886E38"
run_test_args "256.125 s" "Single-precision (32-bit):"$'\n'"------------------------------------"$'\n'"|0|10000111|00000000001000000000000|"$'\n'"------------------------------------"$'\n'"Decimal value: 256.125"
run_test_args "256.125 d" "Double-precision (64-bit):"$'\n'"--------------------------------------------------------------------"$'\n'"|0|10000000111|0000000000100000000000000000000000000000000000000000|"$'\n'"--------------------------------------------------------------------"$'\n'"Decimal value: 256.125"

echo -e "\nTotal tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
