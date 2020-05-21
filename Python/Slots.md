# Slots

`__slots__` can save space if you are dealing with millions of instances
with few attributes. (They use tuples instead of the standard `__dict__`
to store the instance attributes.)

```python

class Vector2d:
    __slots__ = ('__x', '__y')
```

Slots will treat `x` and `y` as instance variables.

There are some considerations which come with `__slots__` like cannot
add other instance variables unless you add `__dict__` to slots and
cannot use weakref unless you add `__weakref__` to slots.