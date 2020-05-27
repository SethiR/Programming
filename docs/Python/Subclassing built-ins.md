# Subclassing built-ins

Its not a good idea to subclass the builtins like \`dict\` and \`list\`
because they ignore the user defined functions, instead you should
subclass the collections module using UserDict, UserList, and
UserString, which are designed to be easily extended.

The below examples subclasses UserDict and raises error if the dict has
any non unique value for any key.

```python
from collections import UserDict


class DistinctError(ValueError):
    """Raised when value is not distinct"""
    pass


class distinctdict(UserDict):
    def __setitem__(self, key, value):
        if value in self.values():
            raise DistinctError("Duplciate Value")
        super().__setitem__(key, value)


dd = distinctdict()
dd['k1'] = 10
dd['k2'] = 20

for k, v in dd.items():
    print(f'{k} --> {v}')

dd['k11'] = 10 # __main__.DistinctError: Duplicate Value
```

The collections module contains extendend lists and other built in types
which may suite your implementation needs without you having to create
you own container type by subclassing the built in types.