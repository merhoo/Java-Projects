#!/bin/bash

file=CoinFlip.java

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
    local expected=$2
    local received=$( echo -e $1 | $command 2>&1 | tr -d '\r' )
    if [ "$expected" = "$received" ]; then
        echo "success"
        (( ++num_right ))
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
}

run_test_multiple_outputs() {
    local len=${#expected[@]}
    local max_loops=$2
    let max_index=$len-1
    declare -a output
    for (( i = 0; i < $len; i++ )); do
        output[$i]=0
    done

    local loop=0
    while [ $loop -lt $max_loops ]; do
        (( ++total ))
        (( ++loop ))
        echo -n "Running test $total..."
        local received=$( echo -e $1 | $command 2>&1 | tr -d '\r' )
        local correct=0
        for (( i = 0; i < $len; i++ )); do
            if [ "${expected[$i]}" = "$received" ]; then
                correct=1
                output[$i]=1
                (( ++num_right ))
                break
            fi
        done
        if [ $correct -eq 0 ]; then
            echo -e "failure\n\nExpected$line"
            for (( i = 0; i < $len; i++ )); do
                echo -e "${expected[$i]}\n"
                if [ $i -ne $max_index ]; then
                    echo -e "    OR...\n"
                fi
                done
            echo -e "Received$line\n$received\n"
            break
        fi
        echo "success"

        local count=0
        for (( i=0; i<$len; i++ )); do
            (( count = count + ${output[$i]} ))
        done

        if [ $count -eq $len ]; then
            return 0
        fi
    done

    local flag=0
    if [ $loop -eq $max_loops ]; then
        for (( i=0; i<$len; i++ )); do
            if [ ${output[$i]} -eq 0 ]; then
                echo -e "\n*** Failure with Random Number Generation ***\nNever saw expected output:\n${expected[$i]}\n"
                flag=1
            fi
        done
        if [ $flag -eq 1 ]; then
            echo -e "The previous $max_loops tests will be marked as incorrect."
            (( num_right -= max_loops ))
        fi
    fi
    return 1
}

run_test "junk" "Let's flip a coin..."$'\n'"Enter heads or tails: Error: You must enter heads or tails. Please rerun the program."
run_test "" "Let's flip a coin..."$'\n'"Enter heads or tails: Error: You must enter heads or tails. Please rerun the program."

declare -a expected
expected[0]="Let's flip a coin..."$'\n'"Enter heads or tails: Coin flipped: heads"$'\n'"You win!"
expected[1]="Let's flip a coin..."$'\n'"Enter heads or tails: Coin flipped: tails"$'\n'"Sorry, you lose."
run_test_multiple_outputs "heads" 20

expected[0]="Let's flip a coin..."$'\n'"Enter heads or tails: Coin flipped: heads"$'\n'"Sorry, you lose."
expected[1]="Let's flip a coin..."$'\n'"Enter heads or tails: Coin flipped: tails"$'\n'"You win!"
run_test_multiple_outputs "tails" 20

echo -e "\nTotal tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
