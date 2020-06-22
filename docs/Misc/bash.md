# Bash/Shell Scripting 

A simple bash script 

```sh
#! /bin/bash

echo "hello bash scripting"
```

Once you create a bash script you can make it executable by adding executable permission on it.

```sh
chmod +x <filename>
```

If you wish the output of the script to go to another file you can 

```sh
#! /bin/bash

echo "hello bash scripting" > file1.txt
```

This will open shell in write mode and you can write anything and output will go to file.ext
```sh
cat > file.txt
```

To append use `>>` and not `>`

multiline comments

```
: '
sdfasf
sa
fas
fasf'
```

Heredoc -- This will show up when you run the file.

```sh
cat << meaningfull_variable_name
Some output
here
to
display
meaningfull_variable_name
```

---

Conditional statements

```sh
count=10

if [ $count -eq 10  ]
    then
        echo "this condition is true"
    else
        echo "this condition is false"
fi
```

for not equal use `-ne`, `-gt` greater than.

If you wish use `>` sign use the below format.

```sh
if (( $count > 9))
then
    xxxx
else
    xxxx
fi
```

elif syntax is something like this.

```sh
if (( $count > 9))
then
    xxxx
elif (( condition ))
then
    xxxx
elif (( condition ))
then
    xxxx
else
    xxxx
fi
```

and ``&&` operator

```sh
age=10
if [ "$age" -gt 18 ] && [ "$age" -lt 40 ]
# --> same as above if [ "$age" -gt 18 -a "$age" -lt 40 ]
then
    echo "Age is correct"
else
    echo "Age is not correct"
fi
```
or operator

```sh
if [ "$age" -gt 18 ] || [ "$age" -lt 40 ]
# or if [ "$age" -gt 18 -o "$age" -lt 40 ]
```

---

Loops.

```sh
#! /bin/bash

number=1

while [ $number -lt 10 ]
do
        echo "$number"
        number=$(( number + 1))
done
```

You have `until` loop as well, which runs untill the  condition becomes true. --> i.e. the condition is false.

```sh
until [ condition ]
do
    xxxx
done
```

for loop

```sh
for i in 1 2 3 4 5
do
    xxxx
done
```

```sh
for i in {0..20}
do
    xxx
done
```
Increment of 2
```sh
for i in {0..20..2}
do
    xxx
done
```

```sh
for i in (( i=0; i<5; i++ ))
do
    xxx
done
```

Break and continue statement.

`break` and `continue`

---

Script input

$0 $1 $2 --> are the arguments 0th would be the shell script (file) name which you are running.

```sh
#! /bin/bash

echo $0 $1 $3
```
```sh
# running the script
> ./sh5.sh today is monday
./sh5.sh today monday
```

Storing them in array instead of individual variables.

```sh
args=("$@")
echo ${args[0]} ${args[1]} ${args[2]}
```

As you see the script name no longer goes to $0
```sh
> ./sh5.sh abc def ghi
abc def ghi
```

You can also print all the values and not just individual by printing the whole array itsef.

```sh
args=("$@")
echo $@
```
```sh
> /sh5.sh abc def ghi jkl sdf ers
abc def ghi jkl sdf ers
```

To measure the length of array you can use `$#`

Read the file one line at a time.
```sh
while read line
do
    echo "$line"
done < "${1:-/dev/stdin}"
```
