# Functions


## Attributes of a function

You can check attributes of a function by using the dir function
`dir(func)`. e.g. the `__dict__` attribute is used to store the user
attributes assigned to it.

```python
def x(a = 2):
    a = 10
    b = 20

>>> x.__dict__
{}
```

Assigning arbitrary attributes to functions as shown below is not a very
common practice in general.

```python
def x(a = 2):
    a = 10
    b = 20

>>> x.a = 10
>>> x.__dict__
{'a': 10}
```

## Only positional arguments


`Python3` introduces only positional arguments for a function, you need
to put a `*` as shown below.

```python
def main(*, a, b):  # This function will only take 2 keyword arguments
    return a + b

>>> main(a = 10, b = 20)
30
```

There can be various variations of it, e.g. =def main(a, \*, b)= where
`a` is positional and `b` is keyword. Note that keyword-only arguments
do not need to have a default value: they can be mandatory.

## Inspect Module


You can do a lot with inspect module to check the code of the function
etc... e.g. checking the signature using `inspect.signature()`. Inspect
module is used a lot when creating an ORM becuase when converting a
dynamic class to SQL you need to look into the class or that instance of
the class.

## Functional programming modules


-   `operator` module
-   functools.partials

## Closures

A closure is a function with an extended scope that encompasses
nonglobal variables referenced in the body of the function but not
defined there. It does not matter whether the function is anonymous or
not; what matters is that it can access nonglobal variables that are
defined outside of its body.

```python
# make averager using closures. This can also be done using class and __call__ method.
def averager():
    series = []

    def ave(num):
        series.append(num)
        return sum(series)/len(series)

    return ave
```

Here you see that `ave` has closure over the `series` variable in the
`averager`.

```python
>>> a = averager()
>>>
>>> a(10)
10.0
>>> a(1)
5.5
>>> a(123)
44.666666666666664
```

Lets inspect the `a` for variables and free variables (which are not
bound to the local scope)

```python
>>> a.__code__
<code object ave at 0x000001D277E3C660, file "main.py", line 4>
>>> a.__code__.co_varnames
('num',)
>>> a.__code__.co_freevars
('series',)
>>> a.__closure__
(<cell at 0x000001D27829CF48: list object at 0x000001D2788111C8>,)
```

The binding for series is kept in the **closure** attribute of the
returned function. Each item in `a.__closure__` corresponds to a name in
`a.__code__.co_free vars`. These items are cells, and they have an
attribute called cell~contents~ where the actual value can be found.

```python
>>> a.__closure__
(<cell at 0x000001D27829CF48: list object at 0x000001D2788111C8>,)
>>>
>>> a.__closure__[0]
<cell at 0x000001D27829CF48: list object at 0x000001D2788111C8>
>>> a.__closure__[0].cell_contents
[10, 1, 123]
```

To summarize: a closure is a function that retains the bindings of the
free variables that exist when the function is defined, so that they can
be used later when the function is invoked and the defining scope is no
longer available.

### `nonlocal`

Consider the example below.

```python
def outer():
    counter = 0

    def inner(num):
        counter += num
        return counter

    return inner


my_counter = outer()

my_counter(10)
```

When you run this python gives an error
`UnboundLocalError: local variable 'counter' referenced before assignment`.
The reason is that when you are trying to increment `counter +` num= you
are treating it as a bounded variable and not as a free variable. So to
use that (and because counter is not a global variable) you need to use
the keyword `nonlocal`.

```python
def outer():
    counter = 0

    def inner(num):
        nonlocal counter
        counter += num
        return counter

    return inner


my_counter = outer()

my_counter(10)
```

```python
>>> my_counter(1)
11
>>> my_counter(12)
23
```