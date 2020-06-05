# Spark

## Pyspark Install steps taken


_Trial 1_
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

_Trial 2_
This time I did things a little differently. I created a `venv` of python3.7 and installed pyspark. The system does not even have apache spark downloaded and insalled. Looks like this is not needed and pyspark (which is of size 225MB) comes with spark built in.

So this means we can test spark applications on my laptop with just a `pip3 install pyspark` and we'll see how to submit them to yarn cluster when the time comes.

## Spark

- In Memory Computing


__Spark shell__

- `spark-shell` is located in spark/bin
- To goto spark shell execute the above `./spark-shell`
- In spark-shell you have 2 variables by default `sc` and `spark`
- `:help`
- `:history`


__RDD__

RDD is basic unit of data on which all operations are performed. RDD is immutable collection of data (which can be distributed). RDD is split into multiple partitions which are computed on different nodes.

We can create RDD using
- Para