# Callables

The `__call__` has a syntactic operator `()`. To check if the object is
callable use the `callable()` buit in function.

The python data model lists 7 types of callable types.

-   User Defined Functions like named or lambda functions
-   Built in functions like =len=\
-   Buit in methods like dict.get
-   Methods of a class
-   Classes. (When invoked it runs `__new__` and then `__init__` )
-   Classes instalce --\> If the class has a `__call__` method
-   Generator functions --\> use `yield`
-   There are other 2 as well but they deal with async and co-routines
    check this link for further details.
    [Link](https://docs.python.org/3/reference/datamodel.html)

You can implement `__call__` method and make objects callable
e.g. example given below. This is an interesting way to implement
function like callables which need an internal state. This can also be
done with the help of a `closure`.

```python
from random import randint

class RandomNumber:

    def __init__(self):
        self.calls = 0


    def __call__(self):
        self.calls += 1
        return randint(1, 100)   
```

Testing

```python
>>> r = RandomNumber()
>>> r()
62
>>> r()
28
>>> r.calls
2
```