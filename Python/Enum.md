# Enum

Enum is supported by many languages such as C, C++, Java etc... Below is
a very brief overview, there is a lot more going on with them so check
out this [link](https://docs.python.org/3/library/enum.html)

Creating an enum

```python
from enum import Enum

class Weekday(Enum):
    MONDAY = 0
    TUESDAY = 1
```

```python
>>> print(Weekday.MONDAY)
Weekday.MONDAY
```

The enums are hashable so they can be used as dictionary keys.

```python
>>> print(Weekday(1))
Weeksay.TUESDAY
```

You can use the decorator `@enum.unique` to ensure that enum values are
unique.