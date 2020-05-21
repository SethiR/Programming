# Generator

## Background of Iterators

How re works

```python
import re
w = re.compile('\w+')
w.findall("This is word")  # ['This', 'is', 'word']
```

```python
import re

RE_WORD = re.compile('\w+')

# The below class implements the necessary methods for sequence.
class Sentence:

    def __init__(self, text):
        self.text = text
        self.word = RE_WORD.findall(self.text)

    def __getitem__(self, index):
        return self.word[index]

    def __len__(self):
        return len(self.words)
```

```python
s1 = Sentence("The quick brown fox jumped over the lazy dog")

for word in s1:
    print(word)
```

```python
The
quick
brown
fox
jumped
over
the
lazy
dog
```

**Why sequences are iterable even though we did not implement the
`__iter__` method ?**

-   Whenver we try to iterate over an object by using lets say for loop
    or any other method the python calls the `iter(object)`
-   The built in `iter` funtion then checks the following : -

    -   Is `__iter__` implement for that object?
    -   If no then it falls back on `__getitem__` and creates an
        iterator on top of that starting form index 0
    -   If that fails too raise TypeError

-   Generally a good idea to implement `__iter__` too. `__getietm__`
    only works for backward compatability.

**What is iterable** ?

Anything which can be iterated on, which can be done in couple of ways

-   Objects implementing `__iter__`
-   Objects which are sequences i.e. implementing `__getitem__`

**What is iterator then ?**

Python obtains iterator from iterables....

```python
iter(s1)  # This will give you an iterator.
```

```python
<iterator at 0x629e790>
```

**Iterator Details**

The iterator should have the following methods implemented.

-   `__iter__` - Returns self; this allows iterators to be used where an
    iterable is expected, for example, in a for loop.
-   `__next__` - Returns the next available item and takes no arguments,
    raising StopIteration when there are no more items.

Once all the items are exhausted you need to call `iter(object)` again.

Converting our Sentence class into iterator by implementing the next and
iter methods. However this is a bad idea.

```python
import re

RE_WORD = re.compile('\w+')

# The below class implements the necessary methods for sequence.
class Sentence:

    def __init__(self, text):
        self.text = text
        self.word = RE_WORD.findall(self.text)
        self.index = 0

    def __next__(self):
        try:
            word = self.word[self.index]
        except:
            raise StopIteration()
        self.index += 1
        return word

    def __iter__(self):
        return self
```

```python
s = Sentence("Hello World today is Monday")

iter1 = iter(s)
iter2 = iter(s)

print(next(iter1))
print(next(iter2))  # So we have a problem here, we cannot have 2 iterators on the s object individually.
```

```python
Hello
World
```

The best way to solve this would be to have 2 classes - our normal
Sentence class --\> will have the `__iter__` method which will return a
new instance of SentenceIterator class `SentenceIterator(self.words)`
every time `iter` is called on the `Sentence` class thus making sure
each iterator has independent execution. - Sentence iterator class --\>
will have the `__iter__` method which will return `self` and the
`__next__` method for raising StopException and taking index into
account.

However this is a much long winded approach.


## Generators


> Check out [Pep 255](https://www.python.org/dev/peps/pep-0255/)

**What is a generator ?**

A python function which has yeild in its body is a generator function.
(It will return a generator object). In other words, a generator
function is a generator factory. A generator function builds a generator
object and wraps the body of the function in it.

Convert **iter** to a generator object.

```python
# A simple generator

def my_generator():
    yield 1
    print("printing --> 1")
    yield 2
    print("printing --> 2")
    yield 3
    print("printing --> 3")
    return None
```

```python
# explicit next

g1 = my_generator()
print(f"Generator object --> {g1}")

print(next(g1))
print(next(g1))
print(next(g1))
```

```python
Generator object --> <generator object my_generator at 0x00BD2970>
1
printing --> 1
2
printing --> 2
3
```

```python
# implicit next

g1 = my_generator()
print(f"Generator object --> {g1}")

for x in g1:
    print(x)
    print('--')
```

```python
Generator object --> <generator object my_generator at 0x00BD2B30>
1
--
printing --> 1
2
--
printing --> 2
3
--
printing --> 3
```

**Converting the Sentence to a generator function**

```python
import re

RE_WORD = re.compile('\w+')

class Sentence:

    def __init__(self, text):
        self.text = text
        # you can also replace "findall" by "finditer" which is the lazy version of it. But then this statement goes in __iter__
        self.word = RE_WORD.findall(self.text)  
        self.index = 0

    def __iter__(self):
        for word in self.word:
            yield word
```

```python
s = Sentence("Hello World today is Monday")

iter1 = iter(s)
iter2 = iter(s)

print(next(iter1))
print(next(iter2))  # We do not have that problem here, we can have 2 iterators on the s object individually.
```

```python
Hello
Hello
```

```python
iter1, iter2  # Check these out they are both independent objects thus they do not share the state.
```

```python
(<generator object Sentence.__iter__ at 0x00BD2B70>,
 <generator object Sentence.__iter__ at 0x00BD2830>)
```

### Examples

Using a generator to capitalize sentence.

```python
def capitalize(values):
    print(values)
    for value in values:
        yield value.upper()


print("".join(capitalize('Hello Sir')))  # HELLO SIR
```

### Sending data to generator using yield statement

```python
```

### Generator Expression

Its a lazy version of list comprehension. `()` instead of `[]`

Generator expressions are syntactic sugar: they can always be replaced
by generator functions, but sometimes are more convenient. On the other
hand, generator functions are much more flexible: you can code complex
logic with multiple statements, and can even use them as coroutines.

```python
import re

RE_WORD = re.compile('\w+')

class Sentence:

    def __init__(self, text):
        self.text = text

    def __iter__(self):
        # match.group() --> Returns one or more subgroups of the match
        #If there is a single argument, the result is a single string; if there are multiple arguments, the result is a tuple with one item per argument. 
        return (word.group() for word in RE_WORD.finditer(self.text))  # <-- This is a generator expression.
```

```python
s = Sentence("The quick brown fox")

for word in s:
    print(word)
```

```python
The
quick
brown
fox
```

```python
iter(s)  # returns a generator.
```

```python
<generator object Sentence.__iter__.<locals>.<genexpr> at 0x010C0330>
```

**Built in Generators**

There are lots of built in generators in Python e.g. the itertools
module provides some 19 generators to use e.g. =itertools.count= and
`itertools.takewhile`. Check out the official documentation to explore
more generators.

```python
# Itertools.count is a generator
import itertools
itertools.count(1)  # Will run for ever
```

```python
count(1)
```

```python
# Using itertools.takewhile with itertools.count --> 2 generators.
gen = itertools.takewhile(lambda n: n<5,itertools.count(1))
list(gen)
```

```python
[1, 2, 3, 4]
```

### Yield From

Introduced in Python 3.3 check out
[PEP380](https://www.python.org/dev/peps/pep-0380/) for more information

```python
s = 'ABC'
n = (1,2,3)

def chain(*iterables):
    for i in iterables:
        yield from i

c = chain(s, n)
c
```

```python
<generator object chain at 0x010C0530>
```

```python
list(c)
```

```python
['A', 'B', 'C', 1, 2, 3]
```