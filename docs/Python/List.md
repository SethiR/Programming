# List


Lists in Python are contiguous arrays of references to other objects.
The head of the list stores the pointer to this list and its length.
When new elements are added to the list there may be a chance that the
array of references needs to be reallocated (as the previous allocated
memory is exhausted.)

## Slicing

For slicing with \[start:stop:increment\] under the hood python calls
`seq.__getitem__(slice(start, stop, step))`

By using the above knowledge we can define our own named slices and make
the code more elegant.

```python
>>> data = """
1010 CA Ontario
1020 US New York
1030 IN Delhi
"""
>>> ID = slice(0, 4)
>>> COUNTRY = slice(5,7)
>>> 
>>> for row in data.split("\n"):
    print(row[ID])

1010
1020
1030
```

**`__getitem__ and __setitem__`**

The \[\] operator is handled by getitem and setitem. In other words, to
evaluate a\[i, j\], Python calls `a.__getitem__((i, j))`.

## Copy


Copies are shallow by default.

Example of shallow copy, even though `l1` and `l2` the id is different
their inner list refers to the same list so appending 10 to l1 appends
it to l2 as well.

```python
In [15]: l = [1, [1]]

In [16]: l1 = [1, [1]]

In [17]: l2 = list(l1)

In [18]: l1[1].append(10)

In [19]: l1
Out[19]: [1, [1, 10]]

In [20]: l2
Out[20]: [1, [1, 10]]

In [21]: id(l1)
Out[21]: 2159380834888

In [22]: id(l2)
Out[22]: 2159379541128
```

You can use the following for copying - Shallow copy - `copy.copy()` -
Deep copy - `copy.deepcopy()`

In order for a class to define its own copy implementation, it can
define special methods `__copy__()` and `__deepcopy__()`

**Tip** - Never have mutable types as defualt parameters of your
arguments.

## Starred Expression


```python
first, second, *rest = [1,2,3,4,5]
```
