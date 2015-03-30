#!/bin/bash

file=Pythagorean.java

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
    local time_limit=$2
    local expected=$3
    local received=$( echo -e $1 | $command 2>&1 | tr -d '\r' )
    if [[ $received =~ $expected ]]; then
        if [[ "${BASH_REMATCH[1]}" < "$time_limit" || "${BASH_REMATCH[1]}" == "$time_limit" ]]; then
            echo "success"
            (( ++num_right ))
        else
            echo -e "failure\nTime limit exceeded. Took ${BASH_REMATCH[1]} seconds, expected completion in $time_limit seconds.\n"
        fi
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
}

run_test "-1" "0.000" "--- Pythagorean Triple Generator ---"$'\n'$'\n'"Enter max value for c: Searching complete..."$'\n'"Elapsed time: ([0-9].[0-9][0-9][0-9]) seconds"
run_test "0" "0.000" "--- Pythagorean Triple Generator ---"$'\n'$'\n'"Enter max value for c: Searching complete..."$'\n'"Elapsed time: ([0-9].[0-9][0-9][0-9]) seconds"
run_test "x" "0.000" "--- Pythagorean Triple Generator ---"$'\n'$'\n'"Enter max value for c: Error: Input is not an integer."
run_test "5" "0.000" "--- Pythagorean Triple Generator ---"$'\n'$'\n'"Enter max value for c: 1\) 3 4 5"$'\n'"Searching complete..."$'\n'"Elapsed time: ([0-9].[0-9][0-9][0-9]) seconds" 
run_test "50" "0.001" "--- Pythagorean Triple Generator ---"$'\n'$'\n'"Enter max value for c: 1\) 3 4 5"$'\n'"2\) 5 12 13"$'\n'"3\) 7 24 25"$'\n'"4\) 9 40 41"$'\n'"Searching complete..."$'\n'"Elapsed time: ([0-9].[0-9][0-9][0-9]) seconds"
run_test "25000" "0.999" "--- Pythagorean Triple Generator ---"$'\n'$'\n'"Enter max value for c: 1\) 3 4 5"$'\n'"2\) 5 12 13"$'\n'"3\) 7 24 25"$'\n'"4\) 9 40 41"$'\n'"5\) 11 60 61"$'\n'"6\) 13 84 85"$'\n'"7\) 15 112 113"$'\n'"8\) 17 144 145"$'\n'"9\) 19 180 181"$'\n'"10\) 21 220 221"$'\n'"11\) 23 264 265"$'\n'"12\) 25 312 313"$'\n'"13\) 27 364 365"$'\n'"14\) 29 420 421"$'\n'"15\) 31 480 481"$'\n'"16\) 33 544 545"$'\n'"17\) 35 612 613"$'\n'"18\) 37 684 685"$'\n'"19\) 39 760 761"$'\n'"20\) 41 840 841"$'\n'"21\) 43 924 925"$'\n'"22\) 45 1012 1013"$'\n'"23\) 47 1104 1105"$'\n'"24\) 49 1200 1201"$'\n'"25\) 51 1300 1301"$'\n'"26\) 53 1404 1405"$'\n'"27\) 55 1512 1513"$'\n'"28\) 57 1624 1625"$'\n'"29\) 59 1740 1741"$'\n'"30\) 61 1860 1861"$'\n'"31\) 63 1984 1985"$'\n'"32\) 65 2112 2113"$'\n'"33\) 67 2244 2245"$'\n'"34\) 69 2380 2381"$'\n'"35\) 71 2520 2521"$'\n'"36\) 73 2664 2665"$'\n'"37\) 75 2812 2813"$'\n'"38\) 77 2964 2965"$'\n'"39\) 79 3120 3121"$'\n'"40\) 81 3280 3281"$'\n'"41\) 83 3444 3445"$'\n'"42\) 85 3612 3613"$'\n'"43\) 87 3784 3785"$'\n'"44\) 89 3960 3961"$'\n'"45\) 91 4140 4141"$'\n'"46\) 93 4324 4325"$'\n'"47\) 95 4512 4513"$'\n'"48\) 97 4704 4705"$'\n'"49\) 99 4900 4901"$'\n'"50\) 101 5100 5101"$'\n'"51\) 103 5304 5305"$'\n'"52\) 105 5512 5513"$'\n'"53\) 107 5724 5725"$'\n'"54\) 109 5940 5941"$'\n'"55\) 111 6160 6161"$'\n'"56\) 113 6384 6385"$'\n'"57\) 115 6612 6613"$'\n'"58\) 117 6844 6845"$'\n'"59\) 119 7080 7081"$'\n'"60\) 121 7320 7321"$'\n'"61\) 123 7564 7565"$'\n'"62\) 125 7812 7813"$'\n'"63\) 127 8064 8065"$'\n'"64\) 129 8320 8321"$'\n'"65\) 131 8580 8581"$'\n'"66\) 133 8844 8845"$'\n'"67\) 135 9112 9113"$'\n'"68\) 137 9384 9385"$'\n'"69\) 139 9660 9661"$'\n'"70\) 141 9940 9941"$'\n'"71\) 143 10224 10225"$'\n'"72\) 145 10512 10513"$'\n'"73\) 147 10804 10805"$'\n'"74\) 149 11100 11101"$'\n'"75\) 151 11400 11401"$'\n'"76\) 153 11704 11705"$'\n'"77\) 155 12012 12013"$'\n'"78\) 157 12324 12325"$'\n'"79\) 159 12640 12641"$'\n'"80\) 161 12960 12961"$'\n'"81\) 163 13284 13285"$'\n'"82\) 165 13612 13613"$'\n'"83\) 167 13944 13945"$'\n'"84\) 169 14280 14281"$'\n'"85\) 171 14620 14621"$'\n'"86\) 173 14964 14965"$'\n'"87\) 175 15312 15313"$'\n'"88\) 177 15664 15665"$'\n'"89\) 179 16020 16021"$'\n'"90\) 181 16380 16381"$'\n'"91\) 183 16744 16745"$'\n'"92\) 185 17112 17113"$'\n'"93\) 187 17484 17485"$'\n'"94\) 189 17860 17861"$'\n'"95\) 191 18240 18241"$'\n'"96\) 193 18624 18625"$'\n'"97\) 195 19012 19013"$'\n'"98\) 197 19404 19405"$'\n'"99\) 199 19800 19801"$'\n'"100\) 201 20200 20201"$'\n'"101\) 203 20604 20605"$'\n'"102\) 205 21012 21013"$'\n'"103\) 207 21424 21425"$'\n'"104\) 209 21840 21841"$'\n'"105\) 211 22260 22261"$'\n'"106\) 213 22684 22685"$'\n'"107\) 215 23112 23113"$'\n'"108\) 217 23544 23545"$'\n'"109\) 219 23980 23981"$'\n'"110\) 221 24420 24421"$'\n'"111\) 223 24864 24865"$'\n'"Searching complete..."$'\n'"Elapsed time: ([0-9].[0-9][0-9][0-9]) seconds"

echo -e "\nTotal tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
