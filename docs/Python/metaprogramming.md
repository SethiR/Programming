# Metaprogramming

This is based on David Beazley tutorial on Metaprogramming on Youtube. I
have tried to go through his tutorial and tried to understand and pick
up things which he has used in his code.

## Prerequisites

In this section I have documented the things which I found out when I
was searching for things which were mentioned in the talk directly or
indirectly.

**Reduce**

This is provided by functools

```python
reduce(lambda x, y: x*y, [1,2,3,4,5]) #120
reduce(lambda x, y: max(x,y), [1,2,3,4,5]) #5
```

**Property Decorator** The property decorator creates sort of an alias
for that function but access as a property and not a function.

```python
class Person:

    def __init__(self, fname, lname):
        self.fname = fname
        self.lname = lname

    @property
    def full_name(self):
        return f"{self.fname} {self.lname}"


sam_nelson = Person("Sam", "Nelson")
print(sam_nelson.full_name)  # can access as a property
print(sam_nelson.__dict__)  # as you see full_name is not there in the dict
```

```python
Sam Nelson
{'fname': 'Sam', 'lname': 'Nelson'}
```

Instead of property decorator you can also use `@cached_property`
decorator which caches the result and does not execute the function
again and again.

**lru~cache~**

This is provided by functools and cache the results of function, very
useful, check it out online. If the argument was provided before to that
function the result will be returned by cache else will be executed.
Makes sense where the calculation is complex but the return value is
very less in size e.g. calculating if prime or not (difficult but return
is just true or false), fibonacchi (difficult to calc 100th fib value
but result is just 1 int value.)

**partial** This is provided by functools.

Take an example below where we have some top level function such as
`add` which does some stuff. Now we can create a closure over it and
create another function which always adds 1 i.e. `add_one` function.

```python
def add(a, b):
    return a + b

def add_one(a):
    return add(a, 1)

print(add_one(10))

```

```python
11
```

`partial` from functools simplifies this behaviour by always passing in
1 for the first argument and the rest user has to provide.

```python
from functools import partial

def add(a, b):
    print("Default = ", a, "user supplied = ", b)
    return a + b

add_one = partial(add, 1)

print("Result", add_one(4))
```

```python
Default =  1 user supplied =  4
Result 5
```

You can also provide value of b by default instead of a as in the above
code, you need to provide it as a keyword argument as shown below.

```python
from functools import partial

def add(a, b):
    print(a, b) # Now b is always 1 and a is supplied by the caller.
    return a + b

add_one = partial(add, b=1) # Notice the change

add_one(4)
```

```python
4 1
```

**Class Variable vs Instance Variable**

```python
class Spam:
    a = 1 # class variable
    def __init__(self, b):
        self.b = b # instance variable
```

```python
Spam.a
Out[3]: 1
Spam(10).b
Out[4]: 10
```

## The Talk

### Decorators

**A simple decorator**

Decorators are used to wrap a function and execute some new
fucntionality which is defined in the decorator as shown in the example below. The key idea is that you can change the implementation of the decorator independently of the code you are using it on.

```python
# A simple decorator which prints the name of the func
from functools import wraps
def mydeco(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        print(func.__qualname__) # extra stuff : printing func name
        return func(*args, **kwargs)
    return wrapper


@mydeco
def add(x, y):
    return x + y


print(add(20, 20))
```

```python
add
40
```

The decorator can be create to take in optinal arguments as well, so
that differnt functions can use the same decorator in a different way.

```python
from functools import wraps

def mydeco(prefix=''):
    def decorate(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            print(prefix + func.__qualname__)  # extra stuff : printing func name
            return func(*args, **kwargs)
        return wrapper
    return decorate


@mydeco(prefix="**")
def add(x, y):
    return x + y


print(add(20, 20))
```

```python
**add
40
```

In the above case the decorator has to be always called via `()` e.g.
`mydeco()` the function style and not just `@mydeco`. There is a hack
which David mentions (which I did not understand fully)

```python
from functools import wraps, partial

def mydeco(func=None, *, prefix=''):
    if func is None:
        return partial(mydeco, prefix=prefix)

    @wraps(func)
    def wrapper(*args, **kwargs):
        print(prefix + func.__qualname__)  # extra stuff : printing func name
        return func(*args, **kwargs)
    return wrapper

@mydeco
def add(x, y):
    return x + y

@mydeco(prefix="**")
def mult(x, y):
    return x * y

print(add(20, 20))
print(mult(20, 20))

```

```python
add
40
**mult
400
```

**Class Decorator**

Moving on a little bit more in depth, lets say you have a class and there are many functions in a class. How are you supposed to decorate all methods of that class. Putting decorator on each function sort of repetitive, thus we have a class decorator to simplify that.


When you create a class you can access the attribues of a module, class or instance by using the `vars` function.

```python
class Person:

    def __init__(self, fn, ln):
        self.fn = fn
        self.ln = ln

    def full_name(self):
        return f"{self.fn} {self.ln}"
```
```python
>>> vars(Person)
mappingproxy({'__module__': '__main__', '__init__': <function Person.__init__ at 0x041FA100>, 'full_name': <function Person.full_name at 0x041FA070>, '__dict__': <attribute '__dict__' of 'Person' objects>, '__weakref__': <attribute '__weakref__' of 'Person' objects>, '__doc__': None})
```

You can then further check if the attribute is callable or not using the `callable` keyword as shown below.

```python
>>> callable(vars(Person)['full_name'])
True
>>> callable(vars(Person)['__doc__'])
False
```

Before jumping in to the actual decorator python has another function `setattr` which lets you set the attribute on an object. e.g. set a new attribute (which could be a function, variable, doc etc...) on a class. (Remember `vars` gives you the attributes and `setattr` lets you set the attributes.)

Syntax is : `setattr(object, name, value)`

Once you have identified the callable you can then wrap it with a decorator.

```python
from functools import partial, wraps


def mydeco(func=None, *, prefix=''):
    if func is None:
        return partial(mydeco, prefix=prefix)

    @wraps(func)
    def wrapper(*args, **kwargs):
        print(prefix + func.__qualname__)  # extra stuff : printing func name
        return func(*args, **kwargs)
    return wrapper


# creating class deco which sets mydeco to each callable of that class
def classdeco(cls):
    for key, val in vars(cls).items():
        if callable(val):
            setattr(cls, key, mydeco(val))  # instead of val wrap it into mydeco and set the callable
     return cls


@classdeco  # setting decorator on class
class Person:

    def __init__(self, fn, ln):
        self.fn = fn
        self.ln = ln

    def full_name(self):
        return f"{self.fn} {self.ln}"
```
```python
>>> sam = Person("Sam", "Nelson")
Person.__init__  # comes from the decorator
>>> sam.full_name()
Person.full_name  # comes from the decorator
'Sam Nelson'
```

The above method will only set the decorator on the instance methods as the class methods or static methods are not identified as callable as shown below.

```python
class Person:

    def __init__(self, fn, ln):
        self.fn = fn
        self.ln = ln

    def full_name(self):
        return f"{self.fn} {self.ln}"

    @classmethod
    def class_details(cls):
        pass
```
```python
>>> callable(vars(Person)['class_details'])
False
```

Lets create a simple example of class decorator which modifies the `__getattribute__` (which does the attribute lookup).

```python
def snooper(cls):
    orggetattribute = cls.__getattribute__

    def __getattribute__(self, name):
        print('Get :', name)
        return orggetattribute(self, name)

    cls.__getattribute__ = __getattribute__

    return cls

@snooper
class Person:

    def __init__(self, fn, ln):
        self.fn = fn
        self.ln = ln

    def full_name(self):
        return f"{self.fn} {self.ln}"

    @classmethod
    def class_details(cls):
        pass
```
```python
sam = Person("S", "N")  # More output than what's displayed below.
>>> sam.fn
Get : fn
'S'
```

### Metaclasses

Before we dive into metaclasses, lets understand how the classes are made in Python. Consider the code below, type of a is `int` and type of `int` is `type` thus `type` must be a class whoes instances are classes.

```python
a = 10
type(a)
<class 'int'>
type(int)
<class 'type'>
```

Lets further verify this by using a custom class

```python
class A:
    pass

>>> type(A)
<class 'type'>
```

Also we can use the type function to create a class. There are few steps involved.

```python
def x(self):  # creating a simple function
    print(10)

# creating a class dict which attaches a name to that function.
clsdict = {'x' : x}

# Creating the class, now Spam is a class
Spam = type('Spam', (object,), clsdict)
```
```python
>>> Spam
<class '__main__.Spam'>
```

The idea behind metaclass is to use something other than `type`, so whatever you put in `class X(metaclass=...)` is going to be used to create the class.

e.g.

```python
# Instead of this (which is the default behaviour, even if you do not put in the type)
class X(type):
    pass

# We want
class Y(metaclass=our_own_implementation_of_type):
    pass
```

Lets see the structure to create a simple metaclass. 
- Inherit from `type`
- Redefine `__new__` or `__init__`

In below example `mytype` is a simple metaclass. (which does not do anything though)
```python
class mytype(type):
    def __new__(cls, name, bases, clsdict):
        clsobj = super().__new__(cls, name, bases, clsdict)
        return clsobj


class Spam(metaclass=mytype):
    pass
```

Lets see a simple example where a metaclass will prevent multiple inheritance in python.

```python
class mytype(type):
    def __new__(cls, name, bases, clsdict):
        if len(bases) > 1:
            raise TypeError("Multiple Inheritance is not allowed")
        return super().__new__(cls, name, bases, clsdict)


class Base(metaclass=mytype):
    pass

class A(Base):
    pass

class B(Base):
    pass

class C(A, B):
    pass
```
When we run this we get error as expected.
```sh
Traceback (most recent call last):
  File "C:/Users/RS/AppData/Roaming/JetBrains/PyCharmCE2020.1/scratches/scratch.py", line 17, in <module>
    class C(A, B):
  File "C:/Users/RS/AppData/Roaming/JetBrains/PyCharmCE2020.1/scratches/scratch.py", line 4, in __new__
    raise TypeError("Multiple Inheritance is not allowed")
TypeError: Multiple Inheritance is not allowed
```

So the idea behind metaclass is that set it on the base class and it will propogate throughout wherever the inheritance hierarchy goes. (could be in your whole framework)


Key points
- Simple decorator - decorates or wraps 1 function or multiple (if applied)
- Class decorator - decorates or wraps all methods (instance) of that class
- Metaclass - decorates or wraps throughout the whole class hierarchy


So metaclasses get information about class definitions at the time of definition, so they can
- inspect this data
- modify this data
However they have bad reputation in python community.


Difference b/w class decorater and metaclass is that class decorator is called after the class is created/formed and metaclass is called before. (Subtle difference thus use case may vary depending on scenario)

<<<<<<<< ----  Min 39.20  ---- >>>>>>>>