"""
Simple decorator.
"""

import functools

def funcA():
    print("In func A")


def decoratorA(func):
    def wrapper():
        print("In wrapper")
        return func()
    return wrapper


funcA = decoratorA(funcA)
    
@decoratorA
def funcB():
    print("In funcB")


# -------------------------------------------------
# Doing something in the wrapper.


import time


def timerA(func):
    def wrapper():
        before = time.time()
        time.sleep(.0001)
        result = func()
        after = time.time()
        print(f'Time taken is {after - before}')
        return result
    return wrapper


@timerA
def funcC():
    print("Hello world")


# ------------------------------------------------
# What if the function accepts arguments.



def timerB(func):
    @functools.wraps(func)
    def wrapper(*args, **kwargs):
        before = time.perf_counter_ns()
        result = func(*args, **kwargs)
        after = time.perf_counter_ns()
        print(f'Time taken is {after - before}')
        return result 
    return wrapper

@timerB
def funcD(a, b):
    return a * b

funcD(10, 12312312213321)



# ------------------------------------------------
# Decorator with arguments and function with arguments


def repeat(num_times = 0):
    def decorator_repeat(func):
        @functools.wraps(func)
        def wrapper(*args, **kwargs):
            for i in range(num_times):
                func(*args, **kwargs)
        return wrapper
    return decorator_repeat
    
    
@repeat(num_times = 4)
def add(x, y):
    print( x + y)


# -----------------------------------------------
# Using class as decorators.

class Decorator:

    def __init__(self, func):
        self.func = func
    
    def __call__(self, *args, **kwargs):
        print("Before Function Call") 
        result = self.func(*args, **kwargs)
        print(result)
        print("After function call")
        return result
        

@Decorator
def sub(x, y):
    return x - y


"""
Where can they be used in?
- Timer functions
- Logging functions
- Plugin system
- Authentication - is user logged in?
- Singleton
"""


"""
You can write class decorators as well i.e. which decorate the class and not functions
e.g. check out the dataclass

- Decorators can be stacked on top of each other.
"""

# In some cases it may be usefull to keep track of the state in a decorator.
# In the example below we add num_calls as a variable of a function itself.


def count_calls(func):
    @functools.wraps(func)
    def wrapper_count_calls(*args, **kwargs):
        wrapper_count_calls.num_calls += 1  # same as wrapper_count_calls.variable 
        print(f"Call {wrapper_count_calls.num_calls} of {func.__name__!r}")
        return func(*args, **kwargs)
    wrapper_count_calls.num_calls = 0
    return wrapper_count_calls

@count_calls
def x():
    pass

@count_calls
def y():
    pass

# Will track the state for x and y separately.
x()
x()
y()
y()
