# Bytes


Strings are sequences which are immutable and store only unicode texts.
`Bytes` and mutatble alternative `bytearray` store only bytes as
sequence values. (which are 0 \<= x \<= 256).

``` {.python results="output" exports="both"}
print(list(b'foo bar')) # use b to convert to byte
print(tuple(b'foo bar'))
```

``` {.example}
[102, 111, 111, 32, 98, 97, 114]
(102, 111, 111, 32, 98, 97, 114)
```

``` {.python results="output" exports="both"}
print(bytes([100,101,102, 103]))
```

``` {.example}
b'defg'
```

The bytes and bytearray allow to work with raw binary data e.g. image
files, video, audio and network packets etc...

You can create a bytearray using bytearray constructor like this
`bytearray(b'foo bar')`

There are various other things you can do with bytes and bytes arrays
which you can check out online.

``` {.python results="output" exports="both"}
```
