# Arrays

Use arrays (which are as lean as the arrays in language C) if - The list
will contain only numbers

You need to specify the `type` when creating the array.

Functions to read and write data from and to array are easy and very
fast as compared to reading/writing text files. (upto 60 times faster) -
Array.tofile - Array.fromfile

**Creating a simple Array**

```python
from array import array

floats = array('d', (10.1, 10.2))  # array('d', [10.1, 10.2])

print(floats)
```

**Saving data in file**

```python

from array import array
from random import random

data = array('d', (10.1, 10.2))

# Write to file.
fp = open('data.bin', 'wb')
data.tofile(fp)
fp.close()
```

**Reading data from file**

You can use `array.fromfile(f, n)` where `f` is file and `n` is number
of items. (Yes you need to know the number of items.) Check other
methods [here](https://docs.python.org/3/library/array.html)