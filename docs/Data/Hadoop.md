# Hadoop

## Installing Hadoop

__Installing Java__

Installing Java1.8
```sh
sudo apt install openjdk-8-jdk  
```

Check java version
```sh
java -version
```

Add JAVA_HOME to /etc/environment
```sh
vim /etc/environment
JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java"
```

Source the envirounment file again
```sh
source /etc/environment
```

make sure java home details are returned.
```sh
$echo JAVA_HOME
```

If you have multiple java versions installed then you can refer to this [link](https://linuxize.com/post/install-java-on-ubuntu-20-04/) to set default.

__Installing Hadoop__


_bashrc add java home_


_bashrc add hadoop variables_


_Create hdfs dirs_


- data directory
- Namenode directory
- Datanode directory

when I create these directories these are blank and do not contain anything (just blank of level folders, when hadoop uses them then it adds information to this directory.)




<<<<<< TODO - https://www.youtube.com/watch?v=l2n124ioO1I >>>>>



## Technology Stack

- [Ambari](https://ambari.apache.org/) : making hadoop management simpler
- [HIVE]() - Execute SQL queries
- Spark
- Pig
- Hbase
- Yarn
- Zookeeper
- Flume
- Scoop

## Concept

- Mapreduce is a good fit for problems that need to analyze the whole dataset in batch fashion. An RDBMS is good for point queries or udpates.
- Mapreduce shines where data is written once but read many times.
- It applies schema on read.
- Hadoop tries to locate the data closer to the compute node for fast access.
- Mapreduce focuses on shared nothing architecture thus the nodes have no dependency on each other.

## MapReduce

- Hadoop can run MapReduce programs written in various languages.
- MapReduce are inherently parallel.


## Basic File concept

HDFS (Hadoop distributed file system) is designed for storing very large files (petabytes) with streaming data access patterns(write once, read multiple times, not 100% sure but streaming may refer to the IO stream which you can setup on that petabyte text file), running on clusters of commodity hardware.

__Nodes__ - HDFS cluster has `namenode` and `datanode` concept i.e. Master and Workers.

__Name Node__ - NameNode is the centerpiece of HDFS. NameNode is also known as the Master. NameNode only stores the metadata of HDFS – the directory tree of all files in the file system, and tracks the files across the cluster. NameNode does not store the actual data or the dataset. The data itself is actually stored in the DataNodes. NameNode knows the list of the blocks and its location for any given file in HDFS. With this information NameNode knows how to construct the file from blocks. NameNode is usually configured with a lot of memory (RAM). Because the namenode holds filesystem metadata in memory, the limit of the number of files is goverened by the amount of memory on the namenode. (As a thumb rule each directory, block, file takes around 150 bytes)

__Writing data to file__ - Writers can write data to a file (1 at a time) and append only.

__Blocks__ - Usually 128MB in size. The large file is broken into blocks and distributed across data nodes. If file is less than 128MB it takes less than 128MB space.


__HDFS Directory Structure__

There is no 1 size fix all for directory structure and even after looking at various posts I did not find consistancy. Below is excerpt from this [link](https://nikhilsmotra.com/2017/06/19/enterprise-data-lake-data-organization-on-hdfs/). Another [link](https://www.quora.com/What-is-the-best-directory-structure-to-store-different-types-of-logs-in-HDFS-over-time/answer/Eric-Sammer) to go though.

Hadoop’s Schema-on-Read approach does not put any restrictions regarding how data is ingested into HDFS, BUT having some structure/order/standards around stored data gave us many benefits.

Standard structure made it easier to share data between teams working on same data sets.
Standard HDFS structure promoted reusability of data processing code.
Standard HDFS data organization allowed us to enforce multi-user access and quota controls (support for multi-tenancy).
We set up organization wide standards to stage data from external sources (B2B applications) before moving it to data lake. This prevented partially loaded data from being used for processing pipelines. We also used staging area to act as a silo to vet external data (for correctness).
Implemented cluster security using Kerberos and encryption (to prevent access to sensitive data).

HDFS – Directory structure and organizing file(s):

Defining standard practices around how data is organized on HDFS and enforcing them made it easier to find, use and share data. Based on our experience and mistakes we made, I suggest the following HDFS directory structure (at a high level):

- /user/{individual_user_sub_directory}: place to hold user specific files (xml, configuration, jar, sample data files) that are not part of business process and primarily used for ad-hoc experiments.
- /applications: location containing 3rd party jars, custom uber-jars, oozie workflow definitions, pig scripts etc that are required to run applications on Hadoop.
- /dsc: top level folder containing data in various stages of extract, transform and load (ETL) workflow. There will be sub-folders under root folder for various departments/groups OR applications that own the ETL process. Within each department or application specific sub-folder,  have a directory for each ETL process or workflow. Within each workflow/process folder, have a sub-folders for following stages: input, output, errors, intermediate-data.
- /data-sets: folders containing data-sets that are shared across the organization. This included raw data-sets as well as data created via transformation, aggregation in various steps of dsc. There should be strict controls around which user(s) can read and write its data with only ETL process(es) having write access. Since this folder acts as root folder for shared data-sets, there will be sub-folders for each data set.
- /tmp: short term storage for temporary data generated by tools or users.

In addition to the above directory structure, I suggest that one should make good use of techniques like Partitioning (reduces I/O required during data processing) and Bucketing (breaking large sets into smaller, more managable sub-sets) for organizing data.

__File System Operations__


Usually

Copying a file from local filesystem to HDFS. Source is left and right is destination. (By default it will be copied over to the users dir on HDFS)

```sh
[maria_dev@sandbox-hdp ~]$ hadoop fs -copyFromLocal a.txt a.txt
```

Similarly you can copy from hdfs to your local dir
```sh
hadoop fs -copyToLocal a.txt a.txt
```

The `ls` command shows similar information to unix system, below mentioned cols provide details.

- Col1 - File mode (read write access etc...)
- Col2 - Replication factorn (configured in hdfs-site.xml file) of file. (Replication factor dictates how many copies of a block should be kept in your cluster. By default its 3, however here its 1. Empty (-) for dir because this does not apply to them. 
)
- Col3 - file owner (maria_dev)
- Col4 - group (hdfs)
- Col5 - size in bytes
- Col6 - last modified date
- Col7 - last modified time
- Col8 - name

```sh
-rw-r--r--   1 maria_dev hdfs          4 2020-05-25 20:18 a.txt
drwxr-xr-x   - maria_dev hdfs          0 2020-05-26 01:00 mydir

```

_Curated Links_
- [hadoop vs hdfs commands](https://stackoverflow.com/questions/18142960/whats-the-difference-between-hadoop-fs-shell-commands-and-hdfs-dfs-shell-co)



---

Was looking at running my first hadoop program using java.
https://examples.javacodegeeks.com/enterprise-java/apache-hadoop/hadoop-hello-world-example/

---
