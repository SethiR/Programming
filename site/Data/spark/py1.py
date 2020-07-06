from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("X").master("local[*]").enableHiveSupport().getOrCreate()

print(spark)

print(spark.sparkContext)

print(dir(spark.sparkContext))
print(dir(spark.sparkContext.appName))

spark.stop()