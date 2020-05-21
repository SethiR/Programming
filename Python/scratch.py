from collections import UserDict


class DistinctError(ValueError):
    """Raised when value is not distinct"""
    pass


class DistinctDict(UserDict):
    def __setitem__(self, key, value):
        if value in self.values():
            raise DistinctError("Duplciate Value")
        super().__setitem__(key, value)


dd = DistinctDict()
dd['k1'] = 10
dd['k2'] = 20

for k, v in dd.items():
    print(f'{k} --> {v}')

dd['k11'] = 10

