# Sets

They are implemented very similiarly to dictionaries. The key is the
element itself (Behind the scenes they are implemented exactly like
dictionaries but with dummy values). Sets allow for very fast addition,
deletion and checking for existance. Sets are mutable.

## Set


A set is a collection of unique objects. A set is mutable.

*Creating a set*

```python
>>> a = {'a', 'b'}
>>> type(a)
<class 'set'>
```

-   Intersection `a & b`
-   Union `a | b`
-   Difference `a - b`

``` {.python}
>>> foods = {"chicken", "bread", "tomatao", "onions"}
>>> veges = {"tomatao", "onions"}
>>> 
>>> foods - veges
{'bread', 'chicken'}
>>> 
>>> foods & veges
{'tomatao', 'onions'}
```

You cannot create an empty set like this `{}`, empty set needs to be
created with a constructor like `s = set()`.

Sets are also implemented with hash tables in the background, the value
is hashed and stored (so we have unique values). Similar to dicts they
are fast in membership testing but not so good on memory.

## Frozen Set

A frozenset is immutable and thus hashable. You can create a frozenset
as shown below.

```python
>>> x = frozenset({1, 2, 3})

>>> type(x)
<class 'frozenset'>

>>> x.__hash__()
-272375401224217160
```
