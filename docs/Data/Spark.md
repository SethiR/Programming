# Spark

## Pyspark Install steps taken

_Spark Download_

Trial 1:

- Pyspark 2.4.5 was only working with python 3.7 and not python 3.8
- Ubuntu came by default with python 3.7 so I had to install python 3.7 
    - I built from source in /opt
    - Created a link as `/usr/bin/python3.7` pointing to `/opt/python3.3.7/python`
    - Configured `update-alternatives` to make python3.7 default.
- Installed Spark in `/opt` (nothing more than wget the 2.4.5 spark)
- Created a system link `/opt/spark` pointing to `/opt/spark2.xxxx`
- As I did not have `python` on my system and only had `python3` I added the following to `/spark/conf/spark-env.sh`
```sh
export PYSPARK_PYTHON=python3
```
- Added the following in my `.bashrc`
```sh
export SPARK_HOME=/opt/spark
export PATH=$SPARK_HOME/bin:$PATH
```

Trial 2:

Download spark from Apache spark website, un-tar it in some dir. Create a python venv or download Anaconda distribution, whatever you feel comfortable.

Set the `PYSPARK_PYTHON` variable in `conf/spark-env.sh`. For example, if Python executable is installed under /opt/anaconda3/bin/python3: `PYSPARK_PYTHON='/opt/anaconda3/bin/python3`


_PIP Pyspark_

This time I did things a little differently. I created a `venv` of python3.7 and installed pyspark. The system does not even have apache spark downloaded and insalled. Looks like this is not needed and pyspark (which is of size 225MB) comes with spark built in.

So this means we can test spark applications on my laptop with just a `pip3 install pyspark` and we'll see how to submit them to yarn cluster when the time comes.

## Spark Overview

- In Memory Computing


__Spark shell__

- `spark-shell` is located in spark/bin
- To goto spark shell execute the above `./spark-shell`
- In spark-shell you have 2 variables by default `sc` and `spark`
- `:help`
- `:history`


__RDD__

RDD is basic unit of data on which all operations are performed. RDD is immutable collection of data (which can be distributed). RDD is split into multiple partitions which are computed on different nodes.

RDD can be created on any hadoop input source (that is supported by hadoop).

Spark automatically partitions RDDs and distributes the partitions across different nodes. A partition in spark is an atomic chunk of data (logical division of data) stored on a node in the cluster.

We can create RDD using
- Parallalize method - Takes in a sequence (like list or array) and # of partitions you need the data split on.
- CreateDataFrame method

parallelize method
```sh
from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("Python Spark create RDD example").config("local[*]").getOrCreate()

df = spark.sparkContext.parallelize([(1, 2, 3, 'a b c'),(4, 5, 6, 'd e f'),(7, 8, 9, 'g h i')]).toDF(['col1', 'col2', 'col3','col4'])

print(df)
```

createDataFrame method
```sh
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
```
```
+---+----+------+----------+
| ID|Name|Salary|Department|
+---+----+------+----------+
|  1| Joe|  2000|         1|
|  1| Joe|  2000|         1|
|  1| Joe|  2000|         1|
|  1| Joe|  2000|         1|
+---+----+------+----------+

root
 |-- ID: long (nullable = true)
 |-- Name: string (nullable = true)
 |-- Salary: long (nullable = true)
 |-- Department: long (nullable = true)
```

Data can also be read from 
- csv files
- postgres database
- HDFS


Lets see some examples in Scala as well.

```scala
# Scala example
# Create an array
scala> val intArray = Array(1,2,3,4,5,6)
intArray: Array[Int] = Array(1, 2, 3, 4, 5, 6)

# Creating an RDD
scala> val intRdd = sc.parallelize(intArray)

# See the first method in your RDD
scala> intRdd.first()
res1: Int = 1

# Use the take method to get various elements from RDD
# Refer to spark docs for scala/python etc... for details on methods such as take.
scala> intRdd.take(2)  # takes first n elements.
res2: Array[Int] = Array(1, 2)

# to get all elements from an rdd
scala> intRdd.collect()
res4: Array[Int] = Array(1, 2, 3, 4, 5, 6)

# Executing on each rdd
scala> intRdd.collect().foreach(println)
1                                       
2                                       
3                                       
4                                       
5                                       
6                                       

# Checking partition size.
scala> intRdd.partitions.size
res10: Int = 4
```

```python
# Python example
# Similarly the RDD can be created in python. (Perf implications)
>>> a = [1,2,3,4,5]
>>> intRdd = sc.parallelize(a)

>>> type(intRdd.take(2))
<class 'list'>

>>> for x in intRdd.take(2):
...     print(x**2)         
...                         
1                           
4                           
```


There are 2 main types of Spark operations (Details in the below sections)
- Spark Transformations - Construct a new RDD from a previous one.
- Spark Actions - compute a result based on RDD, either return it to driver program or save it to external storage.

Lets look at examples.


The methods which we will see (which we will call on lists/sequences are standard scala methods such as `map`, `filter`, `flatmap` or `distinct`. So probably studying scala is not just about the per for RDD but its also how scala functions and what scala functions are utilized in spark (even though they are performed on an RDD and not scala Array or Vector))

```sh
scala> val sentences = Array("Today is monday", "The quick brown fox jumped over the lazy dog", "Hi There")
scala> val sentRdd = sc.parallelize(sentences)

# Filter function (a standard scala function as well)
scala> val filterRdd = sentRdd.filter(line => line.length > 12)

scala> filterRdd.collect.foreach(println)
Today is monday
The quick brown fox jumped over the lazy dog

# Map function (a standard scala map function)
scala> val mapRdd = sentRdd.map(line => line.length)
scala> mapRdd.collect()
res14: Array[Int] = Array(15, 44, 8)

# ex 2 with map --> here it creates an array of array
scala> val mapRdd2 = sentRdd.map(line => line.split(" "))
scala> mapRdd2.collect()
res15: Array[Array[String]] = Array(Array(Today, is, monday), Array(The, quick, brown, fox, jumped, over, the, lazy, dog), Array(Hi, There))

# Flatmap will flatten out the array of arrays and create just array.
scala> val mapRdd3 = sentRdd.flatMap(line => line.split(" "))
mapRdd3: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[7] at flatMap at <console>:25

scala> mapRdd3.collect()
res16: Array[String] = Array(Today, is, monday, The, quick, brown, fox, jumped, over, the, lazy, dog, Hi, There)
```

Lets see very similar examples in Python. You will see that its very similar the diff is mainly in using lambda funcs in Python vs fatarrow => functions in Scala.

```python
>>> sentences = ["Today is monday", "The quick brown fox jumped over the lazy dog", "Hi There"]  
>>> sentRdd = sc.parallelize(sentences)                                                          
                                                                                        
>>> mapRdd = sentRdd.map(lambda line : len(line) > 12 )                                          
>>> mapRdd.collect()                                                                             
[True, True, False]                                                                              

>>> mapRdd2 =sentRdd.filter(lambda line : len(line) > 12 )                                       
>>> mapRdd2.collect()                                                                            
['Today is monday', 'The quick brown fox jumped over the lazy dog']                              

>>> mapRdd3 = sentRdd.flatMap(lambda line : line.split(" ") )
>>> mapRdd3.collect()
['Today', 'is', 'monday', 'The', 'quick', 'brown', 'fox', 'jumped', 'over', 'the', 'lazy', 'dog', 'Hi', 'There']
```

---


Spark applications consist of 

- Driver program - This runs your `main()` function
- A set of executor processes.

The executors will pretty much be running spark code but the driver program can be written in number of different languages exposed via spark api.


---


__Scala or Python__

Lets look at Spark toolkit.

- Low Level API's
    - RDD's
    - Distributed variables.
- Structured API's
    - Datasets
    - Dataframes
    - SQL


If you are using Structured API's the choice of language does not matter because Spark will reduce the code to low level API's (mor efficiently than you could ever write RDD or distributed variable). If for some reason the structured api does not fit your need then its recommended you switch to Scala. (Note that you can sill write everything in Python but just write a small portion in Scala where it leverages custom RDD, Distribute variables).

My strategy is to stick to Python for structured API's and dive in Scala for low level api's.

---

__DataFrame__

The dataframe concept in spark is a little different than Python/R where the data can reside only on 1 machine. However in spark the data underneath that dataframe can reside on multiple machines. Its easy to convert pandas DF to spark.


```python
>>> from pyspark.sql import SparkSession
>>> spark = SparkSession.builder.appName("My App").config("local[*]").getOrCreate()
>>> number = spark.range(1000).toDF("number")
>>> number.show()
+------+
|number|
+------+
|     0|
|     1|
|     2|
|     3|
|     4|
|     5|
|     6|
|     7|
|     8|
|     9|
|    10|
|    11|
|    12|
|    13|
|    14|
|    15|
|    16|
|    17|
|    18|
|    19|
+------+
only showing top 20 rows
```

---

__Transformations__

In spark the core datastructures are immutable, they cannot be changed once created.

```python
>>> div_by_2 = number.where("number % 2 = 0")
# At this point a new df is not created as it will execute/evaluate this lazyly when someone will reqeust acess to it as we'll do next.
>>> div_by_2.show()
+------+
|number|
+------+
|     0|
|     2|
|     4|
|     6|
|     8|
|    10|
|    12|
|    14|
|    16|
|    18|
|    20|
|    22|
|    24|
|    26|
|    28|
|    30|
|    32|
|    34|
|    36|
|    38|
+------+
only showing top 20 rows
```

There are 2 types of transformations 
- narrow : each partition will contribute to only 1 output partition
- wide : input partition contributing to many output partitions. (shuffle)

---

__Action__

Transformations build a logical data transformation plan. To trigger computation we run action.

e.g.

```python
>>> div_by_2.count()
500
```

By doing count we 
- Started a spark job that runs filter transformation (divisible by 2), a narrow transformation.
- Aggregation (counting total) which is a wide transformation.

---

__Spark UI__

You can check out spark UI @ http://localhost:4040/

---

__Example__

A simple example where we read data from csv into a spark df.

```python
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
```

---

__Dataframes and SQL__

Spark can run transformations regardless of the language SQL or DataFrame (Scala, Python, R or Java). In the end all transformations (high level api's) are converted down into a physical plan.

This example shows how we can do SQL queries on a df which we convert to a view (temp table).

There is no performance diff as they compile down to the same plan.

```python
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

sqlWay.explain()
dfWay.explain()
```
```sh
== Physical Plan ==
*(2) HashAggregate(keys=[DEST_COUNTRY_NAME#10], functions=[count(1)])
+- Exchange hashpartitioning(DEST_COUNTRY_NAME#10, 200)
   +- *(1) HashAggregate(keys=[DEST_COUNTRY_NAME#10], functions=[partial_count(1)])
      +- *(1) FileScan csv [DEST_COUNTRY_NAME#10] Batched: false, Format: CSV, Location: InMemoryFileIndex[file:/home/rs/MEGA/repositories/technotes/docs/Data/spark/2015-summary.csv], PartitionFilters: [], PushedFilters: [], ReadSchema: struct<DEST_COUNTRY_NAME:string>

== Physical Plan ==
*(2) HashAggregate(keys=[DEST_COUNTRY_NAME#10], functions=[count(1)])
+- Exchange hashpartitioning(DEST_COUNTRY_NAME#10, 200)
   +- *(1) HashAggregate(keys=[DEST_COUNTRY_NAME#10], functions=[partial_count(1)])
      +- *(1) FileScan csv [DEST_COUNTRY_NAME#10] Batched: false, Format: CSV, Location: InMemoryFileIndex[file:/home/rs/MEGA/repositories/technotes/docs/Data/spark/2015-summary.csv], PartitionFilters: [], PushedFilters: [], ReadSchema: struct<DEST_COUNTRY_NAME:string>
```

Executing simple queries in DF or SQL
```python
>>> print(spark.sql("""
    SELECT max(count) from flight_df_sql
""").take(1))
```
```sh
[Row(max(count)=370002)]
```

A little more complicated example

```python
maxSql = spark.sql("""
SELECT DEST_COUNTRY_NAME, sum(count) as destination_total
FROM flight_df_sql
GROUP BY DEST_COUNTRY_NAME
ORDER BY sum(count) DESC
LIMIT 5
""")
maxSql.show()
```
```sh
+-----------------+-----------------+
|DEST_COUNTRY_NAME|destination_total|
+-----------------+-----------------+
|    United States|           411352|
|           Canada|             8399|
|           Mexico|             7140|
|   United Kingdom|             2025|
|            Japan|             1548|
+-----------------+-----------------+
```

---

### Spark Toolset Overview

__Running prod applications__

You can use `spark-submit` to send your code to a cluster and execute it there.

Scala example
```sh
./spark-submit --class org.apache.spark.examples.SparkPi --master local ../examples/jars/spark-examples_2.11-2.4.5.jar 10
```

Python example
```sh
./spark-submit --master local ../examples/src/main/python/pi.py 10
```

By changing the `master` argument of `spark-submit`, we can also submit the same application to a cluster running Spark’s standalone cluster manager, Mesos or YARN.

---

__Datasets__

Datasets are used for writing statically typed code in Java & Scala. Its not avaibale in Python or R.

The Dataset API gives users the ability to assign a Java/Scala class to the records within a DataFrame and manipulate it as a collection of typed objects, similar to a Java ArrayList or Scala Seq. 

e.g. for our flights data which has 3 cols. So in a sense each row in our csv or parquet file (in this instance) becomes an object of class Flight.

```scala
case class Flight(DEST_COUNTRY_NAME: String,
                  ORIGIN_COUNTRY_NAME: String,
                  count: BigInt)

val flightsDF = spark.read.parquet("/data/flight-data/parquet/2010-summary.parquet/")

val flights = flightsDF.as[Flight]

```

One final advantage is that when you call collect or take on a Dataset, it will collect objects of the proper type in your Dataset, not DataFrame Rows

One great thing about Datasets is that you can use them only when you need or want to. After we’ve performed our manipulations, Spark can automatically turn it back into a DataFrame, and we can manipulate it further by using the hundreds of functions that Spark includes. This makes it easy to drop down to lower level, perform type-safe coding when necessary, and move higher up to SQL for more rapid analysis. 

---

__Structured Streaming__

Structured Streaming is a high-level API for stream processing. <Will skip this for now and revisit later in the book>

---

__ML & Advanced Analytics__

---

__Lower Level API's__

There are some things that you might use RDDs for, especially when you’re reading or manipulating raw data, but for the most part you should stick to the Structured APIs. RDDs are available in Scala as well as Python. However, they’re not equivalent.  (Hint : at RDD level prefer Scala)

There are basically no instances in modern Spark, for which you should be using RDDs instead of the structured APIs beyond manipulating some very raw unprocessed and unstructured data.


## Structured API

DataFrames are untyped and DataSets are typed. Datasets API are only available in Scala or Java.

__Overview of structured api execution__

- Write DF, SQL, Dataset code.
- Spark converts to a logical plan.
- Spark converts from logical to physical plan + optimizations.
- Executes physical plan as RDD manipulations.

__Spark Types__

Listed below how to create a Spark Type in scala and py. For list of all sparktypes check out the [reference](https://spark.apache.org/docs/latest/sql-reference.html).

```scala
# Scala
import org.apache.spark.sql.types._
val b = ByteType
```

```python
# Python
from pyspark.sql.types import *
b = ByteType()
```

These sparktypes are used to instanciate or declare a col to be of certain type.

Lets create a dataframe using a json file. As we have seen that scala and py can both be used for these high level api's I'll use either. (No prferences).

```py
# Creating a dataframe
>>> df = spark.read.format("json").load("D:/Documents/MEGA/repositories/technotes/docs/Data/spark/2015-summary.json")
>>> df
DataFrame[DEST_COUNTRY_NAME: string, ORIGIN_COUNTRY_NAME: string, count: bigint]

>>> df.printSchema()
root
 |-- DEST_COUNTRY_NAME: string (nullable = true)
 |-- ORIGIN_COUNTRY_NAME: string (nullable = true)
 |-- count: long (nullable = true)

 >>> df.take(1)
[Row(DEST_COUNTRY_NAME='United States', ORIGIN_COUNTRY_NAME='Romania', count=15)]
```

Schema defines the col names of a DataFrame. (We can let spark read/infer the schema or define explicitly ourselves.) Note for spark in prod usage, its best to define explicit schema.

The schema is of `StructType`, lets see an example as read the abvoe json file with explict manual schema. Refer to the docs [here](https://spark.apache.org/docs/2.2.0/api/python/pyspark.sql.html#pyspark.sql.types.StructField)

```py
# in Python
from pyspark.sql.types import StructField, StructType, StringType, LongType
myManualSchema = StructType([
    StructField("DEST_COUNTRY_NAME", StringType(), True),
    StructField("ORIGIN_COUNTRY_NAME", StringType(), True),
    StructField("count", LongType(), False, metadata={"hello":"world"})
])
df = spark.read.format("json").schema(myManualSchema).load("D:/Documents/MEGA/repositories/technotes/docs/Data/spark/2015-summary.json")
```

Checking cols of a df. (you can loop on them its a list)
```py
>>> df.columns
['DEST_COUNTRY_NAME', 'ORIGIN_COUNTRY_NAME', 'count']
```

Getting the first row.

```py
>>> df.first()
Row(DEST_COUNTRY_NAME='United States', ORIGIN_COUNTRY_NAME='Romania', count=15)
>>> row1 = df.first()
>>> row1
Row(DEST_COUNTRY_NAME='United States', ORIGIN_COUNTRY_NAME='Romania', count=15)
>>> row1[0]
'United States'
>>> row1[1]
'Romania'
```

You can also create new rows.

```py
>>> myRow = Row("US", "Canada", 12)
```

You can convert the dataframe into temporary table where you can do sql queries on them.

You can also convert `Row` into `DataFrame` by using the function `createDataFrame`.

Doing simple queries on df.

```py
>>> df.select("DEST_COUNTRY_NAME").show(2)
+-----------------+
|DEST_COUNTRY_NAME|
+-----------------+
|    United States|
|    United States|
+-----------------+
only showing top 2 rows

>>> df.select(expr("DEST_COUNTRY_NAME AS destination")).show(2)
+-------------+
|  destination|
+-------------+
|United States|
|United States|
+-------------+
only showing top 2 rows

# SelectExpr --> Select from a DataFrame using a set of SQL expressions.
# In below example it creates a new col using comparison of 2 cols.
>>> df.selectExpr("*", "(DEST_COUNTRY_NAME = ORIGIN_COUNTRY_NAME) as withinCountry").show(2)
+-----------------+-------------------+-----+-------------+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|withinCountry|
+-----------------+-------------------+-----+-------------+
|    United States|            Romania|   15|        false|
|    United States|            Croatia|    1|        false|
+-----------------+-------------------+-----+-------------+
only showing top 2 rows

>>> df.selectExpr("*",("ORIGIN_COUNTRY_NAME = 'Romania'")).show(2)
+-----------------+-------------------+-----+-------------------------------+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|(ORIGIN_COUNTRY_NAME = Romania)|                     true|
|    United States|            Croatia|    1|                          false|
+-----------------+-------------------+-----+-------------------------------+
only showing top 2 rows

# Instead of using the selectExpr you can also use select and use expr function
# where you need to create a new col using sql statement as shown below.
# expr is avaiable from pyspark.sql.functions so do the following
# >>> from pyspark.sql.functions import *
>>> df.select("*",expr("ORIGIN_COUNTRY_NAME = 'Romania'")).show(2)
+-----------------+-------------------+-----+-------------------------------+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|(ORIGIN_COUNTRY_NAME = Romania)|
+-----------------+-------------------+-----+-------------------------------+
|    United States|            Romania|   15|                           true|
|    United States|            Croatia|    1|                          false|
+-----------------+-------------------+-----+-------------------------------+
only showing top 2 rows

# Creating a new col (count *2)
>>> df.select("*",(df["count"]*2)).show(2)
+-----------------+-------------------+-----+-----------+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|(count * 2)|
+-----------------+-------------------+-----+-----------+
|    United States|            Romania|   15|         30|
|    United States|            Croatia|    1|          2|
+-----------------+-------------------+-----+-----------+
only showing top 2 rows


# Rename that col.
>>> df.select("*",(df["count"]*2).alias("2day")).show(2)
+-----------------+-------------------+-----+----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|2day|
+-----------------+-------------------+-----+----+
|    United States|            Romania|   15|  30|
|    United States|            Croatia|    1|   2|
+-----------------+-------------------+-----+----+
only showing top 2 rows
```

Adding a new cols as literals

```py
# Adding a new col with value 1 will return a new df.
>>> df.select("*", lit(1)).show(2)
+-----------------+-------------------+-----+---+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|  1|
+-----------------+-------------------+-----+---+
|    United States|            Romania|   15|  1|
|    United States|            Croatia|    1|  1|
+-----------------+-------------------+-----+---+
only showing top 2 rows

# you can do the same using withColumn <-- This is a more formal way
>>> df.withColumn("One", lit(1)).show(2)
+-----------------+-------------------+-----+---+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|One|
+-----------------+-------------------+-----+---+
|    United States|            Romania|   15|  1|
|    United States|            Croatia|    1|  1|
+-----------------+-------------------+-----+---+
only showing top 2 rows

# As you see withColumn takes 2 args, 1) col name 2) expr
>>> df.withColumn("withinCountry", expr("DEST_COUNTRY_NAME == ORIGIN_COUNTRY_NAME")).show(2)
+-----------------+-------------------+-----+-------------+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|withinCountry|
+-----------------+-------------------+-----+-------------+
|    United States|            Romania|   15|        false|
|    United States|            Croatia|    1|        false|
+-----------------+-------------------+-----+-------------+
```

- You can also rename cols using func withColumnRenamed `df.withColumnRenamed("org_name", "new_name")`. If you wish to use some char which is now allowed esacpe it using `
- You can remove cols `df.drop("col_name1", "col_name_2")`
- You can cast the col into different type `df.withColumn("count2", col("count).cast("long"))`


Filtering rows.

```py
>>> df.filter(col("count") < 2).show(2)
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
|    United States|            Croatia|    1|
|    United States|          Singapore|    1|
+-----------------+-------------------+-----+
only showing top 2 rows
```

You can also use where to do the same thing.


```py
>>> df.where(col("count") < 2).show(2)
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
|    United States|            Croatia|    1|
|    United States|          Singapore|    1|
+-----------------+-------------------+-----+
only showing top 2 rows
```

Adding multiple where clauses

```py
>>> df.where(col("count") < 2).where(col("ORIGIN_COUNTRY_NAME") != "Croatia").show(2)
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
|    United States|          Singapore|    1|
|          Moldova|      United States|    1|
+-----------------+-------------------+-----+
only showing top 2 rows
```

Getting unique rows (based on cols selected)

```py
>>> df.select("ORIGIN_COUNTRY_NAME").distinct().count()
125
>>> df.select("ORIGIN_COUNTRY_NAME","count").distinct().count()
220
```

Getting a sample data out of df.

```py
>>> df.sample(fraction = .1).count()
23

>>> df.sample(withReplacement = False, fraction = .5, seed = 5).count()
126
```

Random Splits --> Creates multiple dataframes

```py
>>> df.randomSplit([0.1, 0.5, 0.4], seed = 4)                                                   
[DataFrame[DEST_COUNTRY_NAME: string, ORIGIN_COUNTRY_NAME: string, count: bigint], DataFrame[DES
_COUNTRY_NAME: string, ORIGIN_COUNTRY_NAME: string, count: bigint], DataFrame[DEST_COUNTRY_NAME:
string, ORIGIN_COUNTRY_NAME: string, count: bigint]]                                            
>>>                                                                                             
>>> len(df.randomSplit([0.1, 0.5, 0.4], seed = 4))                                              
3                                                                                               
```

Concatenate and Appending rows (Union)

Two dataframes which are to be concatenated should have the same schema

```py
# lets first split df into df1 and df2.
>>> df1, df2 = df.randomSplit([.5, .5])                                          
>>> df1.count()                                                                  
127                                                                              
>>> df2.count()                                                                  
129                                                                              

# Both have same schema
>>> df1.schema == df2.schema
True

>>> df1.union(df2).count()
256

# Putting some where clauses in union
>>> df1.union(df2).where("count = 1").show(2)
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
|     Burkina Faso|      United States|    1|
|           Cyprus|      United States|    1|
+-----------------+-------------------+-----+
only showing top 2 rows

>>> df1.union(df2).where("count = 1").where(col("ORIGIN_COUNTRY_NAME") != "United States").show(2)
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
|    United States|            Croatia|    1|
|    United States|             Cyprus|    1|
+-----------------+-------------------+-----+
only showing top 2 rows

>>> df1.union(df2).where("count = 1").where(col("ORIGIN_COUNTRY_NAME") != "United States").where(col("DEST_COUNTRY_NAME") != "United States").show(2)
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
+-----------------+-------------------+-----+
```

Sort or orderBy --> they both work the same way. `df.sort()` `df.orderBy()`

```py
>>> df.orderBy(col("count")).show(10)
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
|         Suriname|      United States|    1|
|    United States|             Cyprus|    1|
|    United States|          Gibraltar|    1|
|           Cyprus|      United States|    1|
|          Moldova|      United States|    1|
|     Burkina Faso|      United States|    1|
|    United States|            Croatia|    1|
|         Djibouti|      United States|    1|
|           Zambia|      United States|    1|
|    United States|            Estonia|    1|
+-----------------+-------------------+-----+
only showing top 10 rows

>>> df.orderBy(col("count"), "DEST_COUNTRY_NAME").show(10)
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
|     Burkina Faso|      United States|    1|
|    Cote d'Ivoire|      United States|    1|
|           Cyprus|      United States|    1|
|         Djibouti|      United States|    1|
|        Indonesia|      United States|    1|
|             Iraq|      United States|    1|
|           Kosovo|      United States|    1|
|            Malta|      United States|    1|
|          Moldova|      United States|    1|
|    New Caledonia|      United States|    1|
+-----------------+-------------------+-----+
only showing top 10 rows

>>> df.orderBy(col("count").desc()).show(5)
+-----------------+-------------------+------+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME| count|
+-----------------+-------------------+------+
|    United States|      United States|370002|
|    United States|             Canada|  8483|
|           Canada|      United States|  8399|
|    United States|             Mexico|  7187|
|           Mexico|      United States|  7140|
+-----------------+-------------------+------+
only showing top 5 rows
```

Limiting rows

```py
>>> df.limit(4).show()
+-----------------+-------------------+-----+
|DEST_COUNTRY_NAME|ORIGIN_COUNTRY_NAME|count|
+-----------------+-------------------+-----+
|    United States|            Romania|   15|
|    United States|            Croatia|    1|
|    United States|            Ireland|  344|
|            Egypt|      United States|   15|
+-----------------+-------------------+-----+
```

You can also repartition the data using `df.repartition(4)` command and check the # of partitions using `df.rdd.getNumPartition()` in py and `df.rdd.getNumPartition` in scala.

You can also repartition based on a column. `df.repartition(col("DEST_COUNTRY_NAME"))`. Coalesce, on the other hand, will not incur a full shuffle and will try to combine partitions. `df.repartition(5, col("DEST_COUNTRY_NAME")).coalesce(2)`

You can also "collect" the data in the driver i.e. the local machine by using `df.collect()`


---

There are many functions in Spark you should check them out at: - 
- API - http://spark.apache.org/docs/latest/api/scala/#package (Change python in link and remome #package)
- DataFrameStatFunctions
- DataFrameNaFunctions

I have noticed that Python API docs are not as good as Scala versions. You may with to check out the scala docs and then see if they have similar in python because the structured api is almost identical in both the scenarios.

---

__Boolean__

Lets pick up another dataset now (retail) and do some operations on it.

```py
>>> df = spark.read.format("csv").option("header","true").option("inferSchema","true").load("D:/Documents/MEGA/repositories/technotes/docs/Data/spark/retail.csv")
>>> df.printSchema()
root
 |-- InvoiceNo: string (nullable = true)
 |-- StockCode: string (nullable = true)
 |-- Description: string (nullable = true)
 |-- Quantity: integer (nullable = true)
 |-- InvoiceDate: timestamp (nullable = true)
 |-- UnitPrice: double (nullable = true)
 |-- CustomerID: double (nullable = true)
 |-- Country: string (nullable = true)

>>> df.where(col("InvoiceNo") == 'C579889').show()
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+
|InvoiceNo|StockCode|         Description|Quantity|        InvoiceDate|UnitPrice|CustomerID|       Country|
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+
|  C579889|    23245|SET OF 3 REGENCY ...|      -8|2011-12-01 08:12:00|     4.15|   13853.0|United Kingdom|
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+

>>> df.where(col("InvoiceNo") == 'C579889').select("Country","StockCode").show()
+--------------+---------+
|       Country|StockCode|
+--------------+---------+
|United Kingdom|    23245|
+--------------+---------+

# Stacking records vertically --> show function argument
>>> df.where(col("InvoiceNo") != 'C579889').select("Country","StockCode").show(n=2,vertical=True)
-RECORD 0-------------------
 Country   | United Kingdom
 StockCode | 84947
-RECORD 1-------------------
 Country   | United Kingdom
 StockCode | 23374
only showing top 2 rows


>>> df.where("InvoiceNo <> 579889").show(1)
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+
|InvoiceNo|StockCode|         Description|Quantity|        InvoiceDate|UnitPrice|CustomerID|       Country|
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+
|   579899|    23301|GARDENERS KNEELIN...|      24|2011-12-01 08:33:00|     1.65|   15687.0|United Kingdom|
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+
 ```

If Boolean statements (which we were doing above using where clause or other conditions e.g. == or <> or !=) are expressed serially (one after the other), Spark will flatten all of these filters into one statement and perform the filter at the same time, creating the and statement for us.


Lets check out another function `instr` --> which finds substring. If you make it `== 1` it means that the substring is on position 1 (index 0 usually). If you make it `>1` would mean that the substring is not the starting of the string but is present. `0` would mean its not present.

```py
>>> df.where(instr(col("StockCode"), "22699") == 1).show()
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+
|InvoiceNo|StockCode|         Description|Quantity|        InvoiceDate|UnitPrice|CustomerID|       Country|
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+
|   579927|    22699|ROSES REGENCY TEA...|       6|2011-12-01 09:20:00|     2.95|   12572.0|       Germany|
|   579938|    22699|ROSES REGENCY TEA...|       6|2011-12-01 10:18:00|     2.95|   14146.0|United Kingdom|
|  C579945|    22699|ROSES REGENCY TEA...|      -1|2011-12-01 10:41:00|     2.95|   15993.0|United Kingdom|
|   580066|    22699|ROSES REGENCY TEA...|       6|2011-12-01 13:33:00|     2.95|   14309.0|United Kingdom|
|   580115|    22699|ROSES REGENCY TEA...|       4|2011-12-01 16:22:00|     5.79|      null|United Kingdom|
+---------+---------+--------------------+--------+-------------------+---------+----------+--------------+
```

You can chain where clause and make & or | statements 
```py
DOTCodeFilter = col("StockCode") == "DOT"
priceFilter = col("UnitPrice") > 600
descripFilter = instr(col("Description"), "POSTAGE") >= 1

df.withColumn("isExpensive", DOTCodeFilter & (priceFilter | descripFilter))\
    .where("isExpensive")\
    .select("unitPrice", "isExpensive").show(5)
```


---

__Numbers__

```py
>>> df.selectExpr(
... "CustomerId",
... "(POWER((Quantity * UnitPrice), 2.0) + 5) as realQuantity").show(2)
+----------+------------------+
|CustomerId|      realQuantity|
+----------+------------------+
|   13853.0|1107.2400000000002|
|   15197.0|            6.5625|
+----------+------------------+
only showing top 2 rows
```

For rounding use `round` or `bround` functions.

You can calculate the co-relation b/w cols (numerical only)

```py
>>> df.stat.corr("Quantity","UnitPrice")
-0.027002171285054978
```

Similar to pandas you can call the describe function `describe()` to get statistical info about the data.

```py
>>> df.describe().show()
+-------+-----------------+------------------+--------------------+------------------+------------------+------------------+--------------+
|summary|        InvoiceNo|         StockCode|         Description|          Quantity|         UnitPrice|        CustomerID|       Country|
+-------+-----------------+------------------+--------------------+------------------+------------------+------------------+--------------+
|  count|             2901|              2901|                2900|              2901|              2901|              2226|          2901|
|   mean|580069.6631130064|27023.362062615102|                null| 9.244743192002758|3.5609996552912917| 15423.48382749326|          null|
| stddev|64.35305942291521|15666.751318292843|                null|28.675161860070975| 19.63596763946906|1701.1005317996028|          null|
|    min|           579899|             10135| 50'S CHRISTMAS G...|               -18|               0.0|           12553.0|          EIRE|
|    max|          C580131|              POST|ZINC WILLIE WINKI...|              1200|           1042.84|           18130.0|United Kingdom|
+-------+-----------------+------------------+--------------------+------------------+------------------+------------------+--------------+
```

There are many other statistical functions avaiable such as `freqItems` or `approxQuantile` etc...

Also there is a function `monotonically_increasing_id()` which generates row # starting from 0.

---

__Strings__

`initcap` -- makes first word capital

```py
>>> df.select(initcap(col("Description"))).show(10)
+--------------------+
|initcap(Description)|
+--------------------+
|Set Of 3 Regency ...|
|Antique Silver Te...|
|Red Spot Paper Gi...|
|Multi Colour Silv...|
|Botanical Gardens...|
|French Style Stor...|
|Sweetheart Bird H...|
|Ceramic Cake Stan...|
|Glass Apothecary ...|
|Egg Cup Henrietta...|
+--------------------+
only showing top 10 rows
```

Other functions to manipulate the case --> `lower`, `upper`

Another trivial task is adding or removing spaces around a string. You can do this by using lpad, ltrim, rpad and rtrim, trim:

Spark also supports regular expressions. Spark takes advantage of the complete power of Java regular expressions. There are two key functions in Spark that you’ll need in order to perform regular expression tasks: `regexp_extract` and `regexp_replace`. These functions extract values and replace values, respectively.


Below we ceate a new col 'No COLOR' by replacing individual colors with text "COLOR" on description col.
```py
>>> regex_string = "BLACK|WHITE|RED|GREEN|BLUE" 
>>> df.select(regexp_replace(col("Description"), regex_string, "COLOR").alias("NO COLOR"), col("Description")).show(3)
+--------------------+--------------------+
|            NO COLOR|         Description|
+--------------------+--------------------+
|SET OF 3 REGENCY ...|SET OF 3 REGENCY ...|
|ANTIQUE SILVER TE...|ANTIQUE SILVER TE...|
|COLOR SPOT PAPER ...|RED SPOT PAPER GI...|
+--------------------+--------------------+
```

To replace every occurance of a character use `translate` function.

```py
>>> df.select(translate(col("Description"), "LEET", "1337").alias("New"), col("Description").alias("OLD")).show(10)
+--------------------+--------------------+
|                 New|                 OLD|
+--------------------+--------------------+
|S37 OF 3 R3G3NCY ...|SET OF 3 REGENCY ...|
|AN7IQU3 SI1V3R 73...|ANTIQUE SILVER TE...|
|R3D SPO7 PAP3R GI...|RED SPOT PAPER GI...|
|MU17I CO1OUR SI1V...|MULTI COLOUR SILV...|
|BO7ANICA1 GARD3NS...|BOTANICAL GARDENS...|
|FR3NCH S7Y13 S7OR...|FRENCH STYLE STOR...|
|SW337H3AR7 BIRD H...|SWEETHEART BIRD H...|
|C3RAMIC CAK3 S7AN...|CERAMIC CAKE STAN...|
|G1ASS APO7H3CARY ...|GLASS APOTHECARY ...|
|3GG CUP H3NRI377A...|EGG CUP HENRIETTA...|
+--------------------+--------------------+
only showing top 10 rows
```

You can use the method `contains` to check for a substring. In python you don't need to explicityly call `contains` but can use the `instr` method as shown above.

__Date and timestamps__

Creating a df with current date and current timestamp.

```py
>>> spark.range(10).withColumn("today", current_date()).withColumn("Time", current_timestamp()).show(4, False)
+---+----------+-----------------------+
|id |today     |Time                   |
+---+----------+-----------------------+
|0  |2020-06-13|2020-06-13 21:13:44.232|
|1  |2020-06-13|2020-06-13 21:13:44.232|
|2  |2020-06-13|2020-06-13 21:13:44.232|
|3  |2020-06-13|2020-06-13 21:13:44.232|
+---+----------+-----------------------+
only showing top 4 rows
```

The schema will look something like this

```
root
 |-- id: long (nullable = false)
 |-- today: date (nullable = false)
 |-- Time: timestamp (nullable = false)
```

Adding or sub dates `date_add()` `date_sub()`

```py
>>> df.select(col("today"), date_sub(col("today"), 5), date_add(col("today"), 5) ).show(1)
+----------+------------------+------------------+
|     today|date_sub(today, 5)|date_add(today, 5)|
+----------+------------------+------------------+
|2020-06-13|        2020-06-08|        2020-06-18|
+----------+------------------+------------------+
only showing top 1 row
```

To calculate the diff b/w dates you can use the functions `datediff`, `months_between`. The `to_date` function allows you to convert a string to a date, optionally with a specified format. You can also convert to timestamp using `to_timestamp` function which always requires a format to be passed in.

You can compare dates using `>` and other usual operators.


__Working with nulls in the data__

As a best practice, you should always use nulls to represent missing or empty data in your DataFrames. Spark can optimize working with null values more than it can if you use empty strings etc... When interacting with nulls use the .na subpackage on a DataFrame.

Various functions to use -- (check out the docs if you wish to learn more) -- `df.na.drop()` `df.na.drop('any')` `df.na.fill()` `df.na.replace()`

__Working with complex types__

- Structs
- Arrays
- Map


__Structs__

You can think of structs as DataFrames within DataFrames.

```py
complexDF = df.select(struct("Description", "InvoiceNo").alias("complex"))   # creating a struct.

# The above will create a df with col 'complex' which has Description adn InvoiceNo in it.

# You can query it as below.
complexDF.select("complex.Description")
complexDF.select(col("complex").getField("Description"))
```

We can also query all values in the struct by using *. This brings up all the columns to the top-level DataFrame:
```py
complexDF.select("complex.*")
```

__Arrays__

Lets say you have a column such as description, you can split that by space into an array of words.

```py
split(col("Description"), " ")
```
Then you can use `array_contains` methods to look into the array.

You can also explode an array into multiple fields using explode `explode(col("splitted")))`.

__Maps__

Maps are created by using the map function and key-value pairs of columns. 

```py
df.select(map(col("Description"), col("InvoiceNo")).alias("complex_map"))\
.selectExpr("complex_map['WHITE METAL LANTERN']").show(2)
```

__Json__

TBD

__UDF__

Udf's are user defined functions.


---

__Aggregations__

- `df.count()`
- `df.countDistinct()` e.g. `df.select(countDistinct("StockCode")).show()`
- `approx_count_distinct()`
- `first()` - e.g. `df.select(first("StockCode"), last("StockCode")).show()`
- `last()`
- `min` & `max` --> `df.select(min("Quantity"), max("Quantity")).show()`
- `sum()`
- `sumdistinct()`
- `avg()`
- variance - `var_samp()`or `var_pop()`
- standard deviation - `stddev_pop` or `stddev_samp`
- and others

__Grouping__

```py
df.groupBy("InvoiceNo").agg(
count("Quantity").alias("quan"),
expr("count(Quantity)")).show()
```

## References

- [Spark Submit - Python Jobs](https://becominghuman.ai/real-world-python-workloads-on-spark-standalone-clusters-2246346c7040)
