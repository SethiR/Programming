# Exceptions

Consider the below example, here if someone does not enter a number then
the conversion to int will fail.

```python
while True:
   x = int(input("Please enter a number : "))
   break 
```

We can handle this exception as shown below. In the below example the
user will be asked to enter the number again and again as long as he
enters it because any non number will be handled by the exception.

```python
while True:
    try:
        x = int(input("Please enter a number : "))
        break
    except Exception:
        print("Not a valid number, please try agian!")
```

There can be more than 1 `except` in a try clause, based on the
exception, however only 1 is ever executed. Usually in the last `except`
we emit the exception name(s) to act as a catch-all.

```python
for cls in [B, C, D]:
    try:
        raise cls()
    except (RuntimeError, TypeError):
        print("D")
    except C:
        print("My own user defined exception")
    except:  # omit the exception type for catch-all
        print("General Exception")
```

Try-except also has an optional `else` clause. It is useful for the code
which must be excecuted if an exception is not raised.

```python
try:
    print(10/10)
except Exception:
    print("Exception raised")
else:
    print("Else clause executed")
```

```python
1.0
Else clause executed
```

The exception may also have an argument associated with it which we can
access as below.

```python
try:
    10/0
except Exception as e:
    print("Exception : ", e)
```

```python
Exception :  division by zero
```

You can also raise your own exception as shown below. There are
different types of excpetions, thus you can raise them as such
`raise ValueError`, `raise NameError('HiThere')` etc...

```python
try:
    raise Exception('foo', 'bar')
except Exception as e:
    print("Exception", e)
```

```python
Exception ('foo', 'bar')
```

## User Defined Exceptions

Programs may create their own exceptions by subclassing their
`Exception` class directly or indirectly. Given below is an example
adapted from standard python tutorial. In the exception module we have a
base class Error which is subclassing `Exception` class. Then we have
various granular classes which raise particular type of exceptions and
take in various arguments for better handling or logging.

```python
class Error(Exception):
    """Base class for exceptions in this module."""
    pass

class InputError(Error):
    """Exception raised for errors in the input.

    Attributes:
        expression -- input expression in which the error occurred
        message -- explanation of the error
    """

    def __init__(self, expression, message):
        self.expression = expression
        self.message = message

class TransitionError(Error):
    """Raised when an operation attempts a state transition that's not
    allowed.

    Attributes:
        previous -- state at beginning of transition
        next -- attempted new state
        message -- explanation of why the specific transition is not allowed
    """

    def __init__(self, previous, next, message):
        self.previous = previous
        self.next = next
        self.message = message


user_input = "Incorrect Input"

try:
    if user_input != 0:
        raise InputError("Num Input", "Incorrect Input Received")
except InputError as err:
    print("Raised due to exception : ", err.expression)
    print("Message : ", err.message)
```

```python
Raised due to exception :  Num Input
Message :  Incorrect Input Received
```

The `try` clause also has another optional clause which is `finally`.
This is executed under all circumstances. Usually cleanup actions are
performed under the `finally` clause.
