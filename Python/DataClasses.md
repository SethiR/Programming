# DataClasses

Data classes are a new addition to Python 3.7 The dataclass decorator
auto writes the code for `__init__()`, `__repr__()` and `__eq__()`
dunder methods. It also can make the instance frozen by taking in an
argument `frozen=True` in the decorator as an argument, thus the
instance can be hashable.