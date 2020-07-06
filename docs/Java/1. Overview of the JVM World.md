Java Platform
-------------

Java now belongs to Oracle.

Java Platform is made up of: -

-   Java Programming Language
-   Java Runtime Env (JVM)
-   Standard Library

All of the above are combined in the JDK

Java code compiles to application bytecode. Java Bytecode can then be
translated to machine code by JVM or the Java runtime envirounment.

**Steps in compliing a Java File**

1.  Create a java file using the `.java` extention.
2.  The source code is compiled to bytecode using `javac`. This then
    creates a file of extention `.class`
3.  Then you can use the `java` command which starts up the JVM and
    executes the `.class` file on the JVM.

Adopting Java
-------------

**Philosophy**

*Portability*

WORA - Write once and Run Anywhere

JVM is designed for each operating system and architecture, so you just
have to swap the JVM on different machine. The same byte code can then
run on different machines.

*Conservative*

New features are considered but Java is conservative.

*Backward Compatibility*

Huge focus on backward compatability.

*Java is open*

Java Community Process (JCP) have say in how it develops. Oracle is not
the only Java implementation. IBM, Eclipse have their own Java
implementations.

*Open JDK*

[file:openjdk.java.net](openjdk.java.net)

**Why adopt Java**

-   Web Applications
-   Backend applications
-   Suited for large appliaitons
-   Wealth of libraries4
-   Strongly typed language

e.g. organizing the java code

`Classes > Packages > Modules`

*Productivity : Managed Rutime*

By using JVM you get these benefits automatically which you may not get
in lower level languages.

-   Automatic memory management
-   Garbage collection
-   Multi-threading

*When not to use Java*

-   Real time systems (self driving cars)
-   when you need low level access to system e.g. device drivers
-   Quick prototyping
-   For developers who want cutting-edge languages

**C\# vs Java**

-   C\# also now open source
-   Its a more faster moving lanaguge
-   As its only recently cross-platform its lacking in tools and
    libraries which target across platform
-   Still dominated by Microsoft

**Java vs Python** - Like Java its a high-level managed language - Like
Java its open ecosystem - Its interpreted language thus a bit slower -
Its not statically typed

**JavaScript vs Java** - With NodeJS JS can now come out of browser and
became and managed runtime. - Interpreted lanaguge and statically typed
- Single-threaded

Types of applications in Java
-----------------------------

**Desktop Java**

Here we are talking about GUI applications

-   AWT - GUI toolkit (less used now its old)
-   Swing (Better option than AWT)

    -   MVC based

-   JavaFX (Most recent and modern looking)

    -   Offers advanced components
    -   Can use CSS
    -   3D Graphics

**Enerprise Java or Java EE**

Enterprise have complex needs like batch jobs, monitoring, database
interactions etc...

With Java SE you will have to use 3rd party tools for a lot of this
funcitonality. Java EE is a set of API\'s which solve common enterprise
applications. e.g.

-   Data persistance
-   Web applications
-   Security
-   Messaging
-   XML/JSON

For Java EE you would need Java Application Server which runs on top of
JVM.

e.g. - Java Persistence Architecture - Enterprise Java Beans - Java
Server Faces - ... and others

There are multiple Java application servers available

-   Wildfly (Red Hat)
-   WebShpere (IBM)
-   WebLogic (Oracle)
-   Tomcat (Apache)

Note : - Java EE will become Jakarta EE

**Java for the cloud**

Java EE applications are almost always monolithic in nature. When
developing for cloud you should go for microservices in the cloud.

Popular microframework in `Sprint Boot` which is built on `Spring`
framework and other `Netflix libraries`. (Java SE and not EE)

Other microframework

-   MicroProfile (Java EE)
-   Vert.x (Open source by Redhat)
-   Play Framework

as of now `Spring Boot` is the king.

**Java on Mobile**

`Java !` Andriod=

Very different skillset.

Popular Java Libraries
----------------------

### Spring Framework

Main notion is `Dependency Injection`. It servers as
`Depencency Injection Container`. It also brings other technologies to
the table.

It decopules the helper and utility classes from the main application
class because the application class does not instanciate the other
classes. Thus decoupling is achieved.

`Spring WebFlux`

### Other libraries

**Utility Libraries**

-   Google Guava --\> collections, Cachching, IO helpers
-   Apache Commons --\> Extend core java func, collections, csv, IO
    (Broader than google guava)
-   Apache Log4J --\> Application Logging

**Distributed Systems**

-   Netty - High performance but low level (Networking)
-   Akka - (High level) concurrency, clustering and distribution
-   RxJava - Reactive programming, Async and event-based application
-   Apache Camel - Enterprise application integration

**Database**

-   JDBC (Too low level)

    -   MySQL
    -   Postgres
    -   Oracle
    -   H2

-   ORM\'s

    -   Hibernate
    -   EclipseLink

-   SQL DSL\'s

    -   jOOQ
    -   QueryDSL

**Data processing (Big Data)**

-   Apache Hadoop
-   Apache Spark (More scalable)
-   DL4J - Deep learning for Java

Stuff written in Java

-   Cassandra (No SQL database)
-   Neo4J - Graph Database in Java
-   ElasticSearch
-   Hadoop Distributed File System

Practices and Common Tools
--------------------------

-   Code

    -   IDEs

        -   Eclipse (Open Source)
        -   IntelliJ

-   Build (Runnable application)

    -   Uses

        -   They manage multiple modules
        -   Manage dependency

    -   Tools

        -   Maven (Most used)
        -   Gradle

            -   Uses Groovy scripts
            -   Supports incremental scripts

-   Test

    -   JUnit
    -   Mockito (Supports mocking)

-   Static Analysis

    -   Checkstyle
    -   Spotbugs
    -   PMD
    -   SobarQube : Continours Inspection

Maven central is like pypi for java.

**Continuous Integration Server**

-   Write Code
-   Push to CI server
-   It builds code
-   Runs tests
-   Analyse code
-   Also can deploy automatically
-   and out comes a working application

E.g.

-   Jenkins (Open Source)

Alternative JVM languages
-------------------------

The JVM can run btye code but it does not matter where this byte code
comes from e.g. it can come from a java file or any other file.

E.g. Scala, Kotlin can compile to Java byte code and can run on JVM.

**Why choose alternative JVM languages**

-   Productivity
-   Familiarity
-   Different paradigms e.g. Functional programming

**Goovy**

-   Dynamic scripting language.
-   Interpreted or compilied
-   Opt-in type system

**Scala**

-   Combines OO and Functional Programming
-   Compiled
-   Extensive type system
-   Akka, Spart written in Scala

**Kotlin**

-   Designed as a `better java` by WebStorm
-   seamless java interop
-   also andriod dev can be done in Java
-   also compiles to JS and can run in browser

JRE vs JDK
----------

-   JRE - Java runtime env

    -   End users will install it

-   JDK - Java development Kit

    -   Developers will install it.
    -   It contains the JRE

Java code will compile to platform independent bytecode which can be run
on specific JRE for that platform.
