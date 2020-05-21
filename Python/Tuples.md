# Tuples


Tuples are immutable and thus hashable.

## Tuple unpacking

``` {.python}
>>> student1 = ("Sam", 14, 8)
>>> name, age, grade = student1 # tuple unpacking
>>> name
'Sam'
>>> age
14
>>> grade
8
```

Tuple unpacking can also be used to return/accept multiple values from a
function.

``` {.python}
def return2():
    return 'a', 'b'

>>> return2()
('a', 'b')
>>> val1, val2 = return2()
>>> val1
'a'
>>> val2
'b'
```

Use \* for extra values. The `*` does not need to be the last parameter.

``` {.python}
>>> a, b, *rest = range(1, 5)
>>> a, b, rest
(1, 2, [3, 4])