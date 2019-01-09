## Installation
- Python at C:\
- Intro to Python Shell
- Intro to Python IDLE -> Writing Python Files
- Pycharm
- VS Code

## Computer Science
### Types of computer languages
- Interpretted
- Compiled

## Python Language Basics

**Numbers**

```python
# Int
a = 10
print(type(a))

# Float
b = 10.1
print(type(b))

# Math operations
5%2     # Modulus
5**2    # Power
```

**Strings**

```python
c = "Hello" # You can use single or double quotes
c = 'Samantha\'s'  # Escaping quotes

# Multiline string
c = """
Hello There
New line
"""

# Slicing Strings
str = "Hello"
str[0:2]  # He

# Calculating the length of a string
len(str)    #5
```

**Indeces of Strings**
```python
+---+---+---+---+---+---+
 | P | y | t | h | o | n |
 +---+---+---+---+---+---+
 0   1   2   3   4   5   6  (L to R)
-6  -5  -4  -3  -2  -1      (R to L)
```

**String Operations**

There are lots of string methods which do operations on the string variables. Some of the common methods are described below.
You can find more information on the string methods [here](https://docs.python.org/3.7/library/stdtypes.html#string-methods)

```python
a = 'hello'
a.capitalize()    # 'Hello' --> capitalize the first character.

a = 'hello'
a.endswith('llo') # True

a = 'Today is Monday'
a.split(' ')    # ['Today', 'is', 'Monday'] --> Splits the string on delimeter.
```

**Conditionals**
- If
- Nested If
- Pass statement

**Range Object**

```python
range(start, stop, increment)
```

**For Loop**

- Loop on range
- Loop on list
- Nested loop
- `Break` and `Continue`
- `else` clause on loop
- enumerate
- zip()

**While Loop**

### Data Structures

**Lists**

```python
# Creating a list
squares = [1, 4, 9, 16, 25]

# indexing returns the item
squares[0] # 1

# slicing returns a new list
squares[-3:] # [9, 16, 25]

# you can concatenate lists
a = [1, 2, 3]
b = [4, 5, 6]
c = a + b
print(c) # [1, 2, 3, 4, 5, 6]

# append to the end of the list
a.append(4)
print(a) # [1, 2, 3, 4]

# Replacing some letters
letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g']
letters[2:5] = ['C', 'D', 'E']
print(letters) # ['a', 'b', 'C', 'D', 'E', 'f', 'g']

# Checking the length of the list
a = [1,2,4]
len(a)  # 3

# Deleting based on index
del a[0]
print(a) # [2, 4]

# Other list methods
# - pop
# - count
```

**List Comprehension**

```python
# Creating a list of squares
[x**2 for x in range(10)]

# Creating a list of tuples
[(x, y) for x in [1,2,3] for y in [3,1,4] if x != y]

# Nested functions in list
[str(round(pi, i)) for i in range(1, 6)]
```

**Dictionaries**

- create dict
- add to dict
- delete from dict
- dictionary comprehension



**Tuples**

Tuples are immutable.

```python
t = 12345, 54321, 'hello!'
t = (12345, 54321, 'hello!')
```


#### Sets
A set is an unordered collection with no duplicate elements. Set objects also support mathematical operations like union, intersection, difference, and symmetric difference.

```python
basket = {'apple', 'orange', 'apple', 'pear', 'orange', 'banana'}
```