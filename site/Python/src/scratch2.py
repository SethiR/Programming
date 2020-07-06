def file_iterator(file):
    with open(file) as f:
        line = f.readline()
        yield line
        while line:
            yield f.readline()


class FileIterator:

    def __init__(self, file):
        self.file = file
        self.header = None
        # return iter(self)

    def __iter__(self):
        with open(self.file) as f:
            self.header = f.readline()
            line = True
            while (line):
                line = f.readline()
                yield line

    def lines(self):
        return iter(self)
    

def file(file_name):
    return iter(FileIterator(file_name))

a = FileIterator("/home/rs/MEGA/techprojects/proj1/uspollution_pollution_us_2000_2016_1.csv")
for x in a.lines():
    print(x)
    break
print(a.header)

# print(a.header)


# class A:

#     def __init__(self):
#         self.x = "x"

#     def __iter__(self):
#         return self

#     def __next__(self):
#         for i in range(10):
#             return i
#         raise StopIteration()


# a = A()
# print(a.x)

# for i in a:
#     print(i)


