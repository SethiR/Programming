import os

x = os.environ.get("PYSPARK_PYTHON", 'python3')

print(x)