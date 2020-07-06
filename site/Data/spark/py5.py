from pyspark.sql import SparkSession
spark = SparkSession.builder.appName("P").config("local[*]").getOrCreate()

flight_df = spark.read.option("inferSchema","true").option("header","true").csv("2015-summary.csv")


# SQL WAY
# convert the df into a temp view table which you can run sql queries on
flight_df.createOrReplaceTempView("flight_df_sql")  # The df flight_df creates a table (view)  flight_df_sql
# Now we can do select query on flight_df_sql as though it was a table
sqlWay = spark.sql("""
    SELECT DEST_COUNTRY_NAME, count(1)
    FROM flight_df_sql
    GROUP BY DEST_COUNTRY_NAME
""")

# DF WAY
dfWay = flight_df.groupBy("DEST_COUNTRY_NAME").count()

# sqlWay.explain()
# dfWay.explain()

print(spark.sql("""
    SELECT max(count) from flight_df_sql
""").take(1))

maxSql = spark.sql("""
SELECT DEST_COUNTRY_NAME, sum(count) as destination_total
FROM flight_df_sql
GROUP BY DEST_COUNTRY_NAME
ORDER BY sum(count) DESC
LIMIT 5
""")
maxSql.show()

flight_df\
.groupBy("DEST_COUNTRY_NAME")\
.sum("count")\
.withColumnRenamed("sum(count)", "destination_total")\
.sort(("destination_total"))\
.limit(5)\
.show()
