from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("Python Spark create RDD example").config("local[*]").getOrCreate()

df = spark.createDataFrame([
    (1,'Joe',2000,1),
    (1, 'Joe', 2000, 1),
    (1, 'Joe', 2000, 1),
    (1, 'Joe', 2000, 1),],
    ['ID','Name','Salary','Department']
)
df.show()
df.printSchema()