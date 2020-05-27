# Collections

## Queue & Deque

### Deque

The class collections.deque is a thread-safe double-ended queue designed
for fast inserting and removing from both ends.

```python
>>> from collections import deque
>>> dq = deque(range(10), maxlen=10)
>>> dq
deque([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], maxlen=10)
>>> dq.rotate(3)
>>> dq
deque([7, 8, 9, 0, 1, 2, 3, 4, 5, 6], maxlen=10)
>>> dq.rotate(-4)
>>> dq
deque([1, 2, 3, 4, 5, 6, 7, 8, 9, 0], maxlen=10)
>>> dq.appendleft(-1)
>>> dq
deque([-1, 1, 2, 3, 4, 5, 6, 7, 8, 9], maxlen=10)
>>> dq.extend([11, 22, 33])
>>> dq
deque([3, 4, 5, 6, 7, 8, 9, 11, 22, 33], maxlen=10)
>>> dq.extendleft([10, 20, 30, 40])
>>> dq
deque([40, 30, 20, 10, 3, 4, 5, 6, 7, 8], maxlen=10)
```

## Other queues

There are multiple other types of queues which are supported by Python

-   queue
-   multiprocessing --\> Queue
-   asyncio --\> Queue, LifoQueue, PriorityQueue, JoinableQueue

## Named Tuples

Creating named tuples is easy.

`nt = namedtuple('t', ('a', 'b'))` - Where \'t\' is the name of named
tuple - `a` and `b` are parameters

namedtuple can be used to build classes of objects that are just bundles
of attributes with no custom methods, like a database record.

```python

import collections

Student = collections.namedtuple('Student', ('name', 'age', 'id'))

sam = Student("Sam", 10, 11)

print(sam)
print(sam.name)
print(sam.age)
```

You can check the fields of a named tuple using `_fields`

```python
>>> Student._fields
('name', 'age', 'id')
```

-   Named tuples can be nested in another named tuples.

Other useful methods

**`_asdict()`**

```python
>>> sam = Student("Sam", 10, 11)
>>> 
>>> sam._asdict()
OrderedDict([('name', 'Sam'), ('age', 10), ('id', 11)])
```

**`_make()`** ~make~() allow you to instantiate a named tuple from an
iterable

### Why can some tuples be hashed and some do not?

Tuples are immutable objects (by themselves). However tuples can contain
lists which are mutable (and cannot be hashed). Thus you get the
scenario below.

```python
In [7]: t1 = (1)

In [8]: t2 = ([1])

In [9]: hash(t1)
Out[9]: 1

In [10]: hash(t2)
---------------------------------------------------------------------------
TypeError                                 Traceback (most recent call last)
<ipython-input-10-c1655d61dc02> in <module>
----> 1 hash(t2)

TypeError: unhashable type: 'list'
```