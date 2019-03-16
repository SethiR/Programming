# Beyond the basics

## Functions
**Simple Functions**

```python
def function_a(a, b, c=10):   # Taking in arguments, with default value
    d = a + b + c           # operation in the function
    return a, b, c, d       # returning multiple values


# args and kwargs (arguments and keyword arguments)
def function_b(a, b, *args, **kwargs):
    pass
```

---

**Lambda functions**

Small anonymous functions can be created with the lambda keyword.

```python
def make_incrementor(n):
    return lambda x: x + n

f = make_incrementor(42)
f(0) # 42
f(1) # 43
```

---

**Document Strings**

```python
def function_a()
    """
    Documentation
    """
    pass
```

## Exceptions
