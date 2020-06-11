from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("P").config("local[*]").getOrCreate()

flight_df = spark.read.option("inferSchema","true").option("header","true").csv("2015-summary.csv")

# As of now the Dataframe has set # of cols and unspecified rows (because it did not read all rows)
# -- Think lazy evaulation

print(flight_df.printSchema())

# Returns first 'n' # of rows.
print(flight_df.take(3))

# The explain command prints out the 'Plan' which spark will take provide output.
# Read from bottom to top. Bottom being step 1 and top being last step.
# Remember .sort() does not modify the df but returns a new df.
print(flight_df.sort("count").explain())

"""
You can also adjust the # of partitions using the below config
spark.conf.set("spark.sql.shuffle.partitions","5")
"""


