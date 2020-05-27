# Dict

-   They are highliy optimized.
-   Are based on hash tables - Thus only objects which are hashable can
    be used as keys. The `__hash__` data model method provides the hash
    value of an object. The `__eq__` method is used to compare the keys,
    if two objects are equal they should have the same hash. Though rare
    but collisions of hashes are possible.
-   Starting with Python 3.7 the order of insertion of item is preserved
    in the dict.

```python
>>> class Person:
...     def __init__(self, name):
...         self.name = name
...         
...     
... 
>>> sam1 = Person("sam")
>>> sam2 = Person("sam")
>>> 
>>> hash(sam1) == hash(sam2)
False
```

```python
>>> a = b = 1
>>> hash(a) == hash(b)
True
```

**dict comprehension**

they are similar to list comprehension

*Variations of dict*

-   dict
-   defualtdict
-   ordereddict
-   collections.ChainMap
-   collections.Counter
-   collections.UserDict
-   types.mappingproxy

`collections.UserDict` is designed to be subclassed, an example below.

```python
class StrKeyDict(collections.UserDict):
  def __missing__(self, key):
      if isinstance(key, str):
        raise KeyError(key)
      return self[str(key)]

  def __contains__(self, key):
      return str(key) in self.data

  def __setitem__(self, key, item):
      self.data[str(key)] = item
```

**`setdefualt()`**

``` {.python results="output" exports="both"}
```

``` {.python results="output" exports="both"}
>>> d = {'Name': 'Zara', 'Age': 7}
>>> d.setdefault("Sex", None)
>>> d
{'Name': 'Zara', 'Age': 7, 'Sex': None}
>>> d.setdefault("Name", 'Peter')
'Zara'
>>> d
{'Name': 'Zara', 'Age': 7, 'Sex': None}
```

## Defaultdict

Defualt dict is very similar to \`dict\` but you can pass a callable
that is used to produce a default value whenever `__getitem__` is passed
a nonexistant key.

```python
>>> s = [('yellow', 1), ('blue', 2), ('yellow', 3), ('blue', 4), ('red', 1)]
>>> d = defaultdict(list)
>>> for k, v in s:
...     d[k].append(v)
...
>>> sorted(d.items())
[('blue', [2, 4]), ('red', [1]), ('yellow', [1, 3])]
```

Dict\'s are very fast but they do consume lots of memory becasue they
need hash tables to operate which should be sparsely populated. If you
are processing large amounts of data you should create them as rows of
tuples/named tuples vs creating them as arrays of dicts (JSON) for space
considerations.

Check out the \`\_~slots~\_\_\` attribute which changes the storage of
instance attributes from a dict to a tuple in each instance.

The dict implementation is an example of trading space for time:
dictionaries have significant memory overhead, but they provide fast
access regardless of the size of the dictionary---as long as it fits in
memory.

Dictionaries are a keystone of Python. Beyond the basic dict, the
standard library offers handy, ready-to-use specialized mappings like
defaultdict, OrderedDict, ChainMap, and Counter, all defined in the
collections module. The same module also provides the easy-to-extend
UserDict class. Two powerful methods available in most mappings are
setdefault and update. The setdefault method is used to update items
holding mutable values, for example, in a dict of list values, to avoid
redundant searches for the same key. The update method allows bulk
insertion or overwriting of items from any other mapping, from iterables
providing (key, value) pairs and from keyword arguments. Mapping
constructors also use update internally, allowing instances to be
initialized from mappings, iterables, or keyword arguments. A clever
hook in the mapping API is the [[missing]{.underline}]{.underline}
method, which lets you customize what happens when a key is not found.
The collections.abc module provides the Mapping and MutableMapping
abstract base classes for reference and type checking. The little-known
MappingProxyType from the types module creates immutable mappings. There
are also ABCs for Set and Mutable
