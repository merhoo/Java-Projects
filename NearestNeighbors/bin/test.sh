#!/bin/bash

file=NearestNeighbors.java

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

run_test_args_file() {
    (( ++total ))
    local filename=$1
    local expected=$2
    echo -n "Running test $total..."
    local received=$( eval $command $1 2>&1 | tr -d '\r' )
    if [ -f "$filename" ]; then
        received=$( cat "$filename" | tr -d '\r' )
    fi
    if [ "$expected" = "$received" ]; then
        echo "success"
        (( ++num_right ))
    else
        echo -e "failure\n\nExpected$line\n$expected [in $filename]\nReceived$line\n$received [in $filename]\n"
    fi
    rm -f "$filename"
}

run_test_not_file() {
    (( ++total ))
    echo -n "Running test $total..."
    if [ ! -f "`eval echo $1`" ]; then
        echo "success"
        (( ++num_right ))
    else
        echo -e "failure\nFile '$1' found where it should not be.\n"
    fi
}

rm -rf *.csv
run_test_args "" "Usage: java NearestNeighbors <input file>"
run_test_args "file1.csv file2.csv" "Usage: java NearestNeighbors <input file>"
run_test_args "notfound.csv" "Error: Cannot open 'notfound.csv' for processing."
rm -rf *.csv

(cat << ENDOFTEXT
Ari,2,0.5
Isaac,-0.333,-2
Catherine,,
Joey,3,-0.667
ENDOFTEXT
) > neighbors.csv
run_test_args "neighbors.csv" "Error: Invalid format on line 3."
rm -rf *.csv

(cat << ENDOFTEXT
Ari,x,0.5
Isaac,-0.333,-2
Catherine,,
Joey,3,-0.667
ENDOFTEXT
) > neighbors.csv
run_test_args "neighbors.csv" "Error: Invalid x-coordinate 'x' on line 1."
rm -rf *.csv

(cat << ENDOFTEXT
Ari,2,0.5 
Isaac,-0.333,2.y 
Catherine,, 
Joey,3,-0.667 
ENDOFTEXT
) > neighbors.csv
run_test_args "neighbors.csv" "Error: Invalid y-coordinate '2.y' on line 2."
rm -rf *.csv

(cat << ENDOFTEXT
ENDOFTEXT
) > neighbors.csv
run_test_args "neighbors.csv" "Error: No data in 'neighbors.csv'."
rm -rf *.csv

(cat << ENDOFTEXT
ENDOFTEXT
) > blank.csv
run_test_args "blank.csv" "Error: No data in 'blank.csv'."
rm -rf *.csv

(cat << ENDOFTEXT
Ari,2,1.5
Isaac,-1.333,-2
Catherine,2,-1
Joey,3.2,-0.667
ENDOFTEXT
) > grades.csv
run_test_args "grades.csv" "Neighbors:"$'\n'"   Ari: (2.0, 1.5)"$'\n'"   Isaac: (-1.333, -2.0)"$'\n'"   Catherine: (2.0, -1.0)"$'\n'"   Joey: (3.2, -0.667)"$'\n'$'\n'"Closest neighbors:"$'\n'"   Catherine: (2.0, -1.0)"$'\n'"   Joey: (3.2, -0.667)"$'\n'$'\n'"Distance: 1.245"
rm -rf *.csv

(cat << ENDOFTEXT
Allison,0,0
Ben,0,0
Chris,0,0
ENDOFTEXT
) > grades.csv
run_test_args "grades.csv" "Neighbors:"$'\n'"   Allison: (0.0, 0.0)"$'\n'"   Ben: (0.0, 0.0)"$'\n'"   Chris: (0.0, 0.0)"$'\n'$'\n'"Closest neighbors:"$'\n'"   Allison: (0.0, 0.0)"$'\n'"   Ben: (0.0, 0.0)"$'\n'"   Chris: (0.0, 0.0)"$'\n'$'\n'"Distance: 0.000"
rm -rf *.csv

(cat << ENDOFTEXT
Allison,0,1
Ben,1,0
Chris,-1,0
ENDOFTEXT
) > grades.csv
run_test_args "grades.csv" "Neighbors:"$'\n'"   Allison: (0.0, 1.0)"$'\n'"   Ben: (1.0, 0.0)"$'\n'"   Chris: (-1.0, 0.0)"$'\n'$'\n'"Closest neighbors:"$'\n'"   Allison: (0.0, 1.0)"$'\n'"   Ben: (1.0, 0.0)"$'\n'"   Chris: (-1.0, 0.0)"$'\n'$'\n'"Distance: 1.414"
rm -rf *.csv

(cat << ENDOFTEXT
Allison,0,1
Ben,1,0
Chris,-1,0
Dan,5,5
Eugene,5.5,4.5
ENDOFTEXT
) > grades.csv
run_test_args "grades.csv" "Neighbors:"$'\n'"   Allison: (0.0, 1.0)"$'\n'"   Ben: (1.0, 0.0)"$'\n'"   Chris: (-1.0, 0.0)"$'\n'"   Dan: (5.0, 5.0)"$'\n'"   Eugene: (5.5, 4.5)"$'\n'$'\n'"Closest neighbors:"$'\n'"   Dan: (5.0, 5.0)"$'\n'"   Eugene: (5.5, 4.5)"$'\n'$'\n'"Distance: 0.707"
rm -rf *.csv

points=$(echo "scale=2; 25 * $num_right / 3" | bc)
echo -e "\nTotal tests run: $total"
echo "Number correct : $num_right"
echo "Points earned  : $points"

total=0
num_right=0
echo -e "\nTesting for extra credit..."

(cat << ENDOFTEXT
Allison,0,1
Ben,1,0
Chris,-1,0
Dan,5,5
Eugene,5.5,4.5
Frank,-109,-87
Gretchen,-108.5,-86.5
ENDOFTEXT
) > grades.csv
run_test_args "grades.csv" "Neighbors:"$'\n'"   Allison: (0.0, 1.0)"$'\n'"   Ben: (1.0, 0.0)"$'\n'"   Chris: (-1.0, 0.0)"$'\n'"   Dan: (5.0, 5.0)"$'\n'"   Eugene: (5.5, 4.5)"$'\n'"   Frank: (-109.0, -87.0)"$'\n'"   Gretchen: (-108.5, -86.5)"$'\n'$'\n'"Closest neighbors:"$'\n'"   Dan: (5.0, 5.0)"$'\n'"   Eugene: (5.5, 4.5)"$'\n'$'\n'"   Frank: (-109.0, -87.0)"$'\n'"   Gretchen: (-108.5, -86.5)"$'\n'$'\n'"Distance: 0.707"
rm -rf *.csv

(cat << ENDOFTEXT
Allison,0,1
Ben,1,0
Chris,-1,0
Dan,5,5
Eugene,6,6
Frank,-109,-87
Gretchen,-108,-86
ENDOFTEXT
) > grades.csv
run_test_args "grades.csv" "Neighbors:"$'\n'"   Allison: (0.0, 1.0)"$'\n'"   Ben: (1.0, 0.0)"$'\n'"   Chris: (-1.0, 0.0)"$'\n'"   Dan: (5.0, 5.0)"$'\n'"   Eugene: (6.0, 6.0)"$'\n'"   Frank: (-109.0, -87.0)"$'\n'"   Gretchen: (-108.0, -86.0)"$'\n'$'\n'"Closest neighbors:"$'\n'"   Allison: (0.0, 1.0)"$'\n'"   Ben: (1.0, 0.0)"$'\n'"   Chris: (-1.0, 0.0)"$'\n'$'\n'"   Dan: (5.0, 5.0)"$'\n'"   Eugene: (6.0, 6.0)"$'\n'$'\n'"   Frank: (-109.0, -87.0)"$'\n'"   Gretchen: (-108.0, -86.0)"$'\n'$'\n'"Distance: 1.414"
rm -rf *.csv

echo -e "\nExtra credit tests run: $total"
echo "Number correct        : $num_right"
extra_points=$(( $num_right * 5 ))
echo "Points awarded        : $extra_points"
total_points=$(echo "scale=2; $points + $extra_points" | bc)
echo -e "\nTotal points earned on assignment: $total_points"

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
