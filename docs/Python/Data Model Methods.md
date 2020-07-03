# Data Model Methods


There are many data model or special methods in Python, a handfull of
them are covered below. For details of other methods please check out
this ref link from [python
docs](https://docs.python.org/3/reference/datamodel.html)


## `__call__`

Allows the instance to be called with the parentheses syntax: `instance()`


```py
class A:

    def __init__(self):
        print("In Init")

    def __call__(self, *args, **kwargs):
        print("In Call")


a = A() # In Init
a() # In Call --> instance() --> invokes the __call__ function.
```

There can be various uses as shown below.

```py
class TriangleArea:
    
    def __call__(self, a, b, c):
        p = (a + b + c) / 2
        result = (p * (p - a) * (p - b) * (p - c)) ** 0.5
        return result
    
    
area = TriangleArea()

print(area(3, 4, 5))
```


## `__getitem__`

The `__getitem__` function if implemented on a class will support the
following - object\[0\] - get by position - you can use the standard
`random.choice()` to get any from the object. - slicing `object[x:y]`

```python
# __getitem__ example

import collections
from random import choice

Card = collections.namedtuple('Card', ('rank', 'suit'))

class FrenchDeck:
    ranks = [str(n) for n in range(2, 11)] + list('JQKA')
    suits = "spades diamonds clubs hearts".split(" ")

    def __init__(self):
        self._cards = [Card(rank, suit) for suit in self.suits for rank in self.ranks]

    def __len__(self):
        return len(self._cards)

    def __getitem__(self, position):
        return self._cards[position]


if __name__ == "__main__":
    deck = FrenchDeck()
    print(len(deck))
    print(deck[4])
    print(deck[4:10])
    print(choice(deck))
    print(Card('Q', 'hearts') in deck)
```

Slicing can return only the array but if you want slicing to return a
new object of that class but only sliced entries then you have to
implement something like this where `components` are arrays.

```python
def __len__(self):
    return len(self._components)

def __getitem__(self, index):
    cls = type(self)
    if isinstance(index, slice):
        return cls(self._components[index])
    elif isinstance(index, numbers.Integral):
        return self._components[index]
    else:
        msg = '{cls.__name__} indices must be integers'
        raise TypeError(msg.format(cls=cls))
```

If there is no `__iter__` python falls back on `__getitem__` for
iteration.

If there is no `__contains__` python falls back on `__getitem__` to
check for `x in y`.

## `__repr__`

Provides the string representation of the object. The representation
should match the source code needed to recreating this instance.

## `__str__`

`__str__` is called by the `str()` and implicitly used by the print
statement. This should return a string suitable for end users. The
differnce between `__str__` and `__repr__` is illustrated by this
example below.

If you only implement one of these special methods, choose `__repr__`,
because when no custom `__str__` is available, Python will call
`__repr__` as a fallback.

```python
class Point:

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __repr__(self):
        return 'Point({}, {})'.format(self.x, self.y)

    def __str__(self):
        return 'Point at {} and {}'.format(self.x, self.y)
```

```python
>>> p1 = Point(2, 10)
>>> p1
Point(2, 10)
>>> print(p1)
Point at 2 and 10
```

## `__abs__`

gives absolute value

## `__add__`

Addition of 2 objs

## `__mul__`

Multiplication of 2 objs

## `__bool__`


Python will accept any object in boolean context because it calls
`__bool__` to determine *truthy or falsy*

## `__del__`

Python will call the `__del__` method (if defined) on an object before
destroying it. (When the reference count reaches 0). This is for Cpython
which uses the reference counting and not all implementation of python
e.g. Pypy which uses garbage collection (this **del**) may not be called
immidiately.

## `__format__`

With datetime you can work with printing specific formats like
`>>> format(now, '%H:%M:%S')` and with float values you can use as
below.

```python
pi = 22/7
print(f'{pi:.4f}')
```

The `__format__` function lets you define your own format for your
object.

## `__hash__`

Make your objects hashable. Check example from fluent python

## `__getattr__`

`__getattr__` allows you to specify custom attributes on your object
which can get values of your elements in the class. e.g. n-d vector may
choose to implement x, y, z e.g. v.x v.y and v.z for first few
dimensions of it...

## `__setattr__`

along with getattr you may need to implement setattr in certain
scenarios.

`super().__setattr__(name, value)`

You can also use setattr to restrict setting any other/or any specific
attributes on that class as shown below.

```python
# cannot set any attribute on the class.
class X:

    def __setattr__(self, name, val):
        msg = "Cannot add any other attributes"
        raise Exception
```

## `__iter__`

`__iter__` is a generator function which, when called, builds a
generator object that implements the iterator interface.

## `__hash__`

provides the `hash` of an object.
