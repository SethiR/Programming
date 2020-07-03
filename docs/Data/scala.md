# Scala

## The basics

Declaring variables.

In Scala, you are encouraged to use a val unless you really need to change the contents. Perhaps surprisingly for Java or C++ programmers, most programs don’t need many `var` variables.

```scala
val a = 10 // val declares a constant
val xmax, ymax = 100

var x = 20 // var declares a variable
```

You can specify the type of variable if you wish to.

```scala
val a: Int = 10
val b : String = "Hello World"
var greeting, message: String = null
```

Semicolons are optional and required only if multiple statements on the same line.

Scala has 7 numeric types, all these are classes though. Thus you can call methods on them.
- Byte
- Char
- Short
- Int
- Long
- Float
- Double

```scala
val a = 10
a.toString
```

You also have 
- BigInt
- BigDecimal

Simple math operations
```scala
a + 10 // This is a shorthand for a.+(10)
```

In general, you can write `method b` as a shorthand for `a.method(b)` e.g. `1.to(10)` can be written as `1 to 10`

If the method has no parameters, you don’t have to use parentheses e.g. `"Bonjour".sorted` and `"Bonjour".sorted()` are pretty much the same.

__Apply Method__

`s.apply(4)` shorthand is `s(4)`

```scala
val s = "Hello"
s(4) // o
```

__Conditionals__

Single liner if statement `if (cond) statement1 else statement2`. This if statement has a value as shown below.

```scala
scala> if (x > 1) 1 else 0
val res24: Int = 1
```
```scala
if (x > 10){
  println("x is greater than 10")
} else if (x == 10){
  println("x is equal to 10")
}
else {
  println("x is less than 10")
}
```

__Blocks__

The value of the block is the value of the last expression.
```scala
val distance = { val dx = x - x0; val dy = y - y0; sqrt(dx * dx + dy * dy) }
```
A block which ends in assignment has a `Unit` value which is same as `void` in java.

```scala
{ r = r * n; n -= 1 }
```
__print__

```scala
print()
println()
print(f"Hello, $name, your age in 6 months will be ${age + 6}")  // formatted string
```

__reading input__

```scala
import scala.io.StdIn
val name = StdIn.readLine("Your name : ")
println(name)
```

To read Int, Double etc... you can use `readInt`, `readDouble`

__loops__

while loop

```scala
var n = 1
while (n < 10){
  n+=1
  println(n)
}
```

for loop
```scala
for (i<- 1 to 10){
  println(i)
}
```

```scala
val name = "Sam Nelson"

for(c<- name)
  println(c)
```

```scala
val name = "Sam Nelson"

for(i<- 0 until name.length)
  println(name(i))
```

```scala
for (i<- 1 to 3; j<- 1 to 4) println(f"i - $i j = $j")
```
generates the output
```
i - 1 j = 1
i - 1 j = 2
i - 1 j = 3
i - 1 j = 4
i - 2 j = 1
i - 2 j = 2
i - 2 j = 3
i - 2 j = 4
i - 3 j = 1
i - 3 j = 2
i - 3 j = 3
i - 3 j = 4
```

```scala
for (i<- 1 to 3; j<- 1 to 4 if i != j ) println(f"i - $i j = $j")
```
generates
```
i - 1 j = 2
i - 1 j = 3
i - 1 j = 4
i - 2 j = 1
i - 2 j = 3
i - 2 j = 4
i - 3 j = 1
i - 3 j = 2
i - 3 j = 4
```

When body of loop starts with yield it constructs a collection of values.

```scala
for (i <- 1 to 10) yield i % 3
// Yields Vector(1, 2, 0, 1, 2, 0, 1, 2, 0, 1)
```

__functions__

To define a function use the `def` keyword, you must specify the types of all parameters. As long as function is not recursive you need not specify the return type of the function.

Note : Remember in scala function cannot be defined outside a class or a object. --> In Python lets say you can do that.

You also do not need a return statement, last statement is returned.

```scala
def abs(x : Double) = {
  if (x>=0) x else - x
}
```

you can specify the default value of argument. Looks very-very similar to Python where the un-named must come first and named comes after in the call of the function.

A function which takes variable number of arguments.

```scala
def sum(args: Int*) = {
  var result = 0
  for(element<- args)
    result +=  element
  result
}

sum(10, 20, 30, 40, 50) // 150 
// sum(1 to 10) // error <-- as this is range and not int.
sum(1 to 10:_*)  // This notation : _* tells the compiler to pass each element of arr as its own argument
```

if you omit the `=` in function definition it becomes a procedure and does not return anything i.e. returns `Unit`

e.g.

```scala
def x(){ // look no = e.g. no x() =, better stick with x(): Unit = to be explicit 
}
```

__lazy values__
If lazy is used lazy, the variable/constant initialization is deferred until it is accessed for the first time.
```scala
lazy val words = xxxx
```

__Exceptions__

Scala exceptions work the same way as in Java or C++. When you throw an
exception, for example
```scala
if (x >= 0) { sqrt(x)
} else throw new IllegalArgumentException("x should not be negative")
```

syntax for catching exception

```scala
val url = new URL("http://horstmann.com/fred-tiny.gif")
try {
    process(url)
} catch {
    case _: MalformedURLException => println(s"Bad URL: $url")
    case ex: IOException => ex.printStackTrace()
}
```

you also have finally which is executed whether or not the process function throws an exception

```scala
finally {
in.close()
}
```

### Arrays

- Fixed length array = `Array`
- Variable length array = `ArrayBuffer`

```scala
val nums = new Array[Int](10)  // new is not req if you are supplying initial values

for(i<- 0 until 10)
  nums(i) = i

for(i<- 0 until 10)
  println(nums(i))
```

```scala
val s = Array("Hello", "World")

for (word<- s)
  println(word)
```

Lets do a few examples with ArrayBuffer.

```scala
import scala.collection.mutable.ArrayBuffer

val names = ArrayBuffer[String]()  // new not req because we did not specify the # of elements req in the ArrayBuffer

names += "Sam"
names += ("Pam", "Lam", "Tam")

for(name<-names)
  println(name)
```

removing from end

```scala
names.trimEnd(2)

for(name<-names)
  println(name)  // Sam Pam
```

You can use `insert` and `remove` function to do operations on arbitraty index but those refactor the entire array. Adding at end or removing from end are better for performance.

You can convert from Array to ArrayBuffer and vice versa `.toArray` or `.toBuffer` 

You can create a transformed new array from another array using `yeild` statement in the loop.

```scala
val arr1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

for (element<- arr1)
  yield element*2  // creates a new array
```

```scala
val arr1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

for (element<- arr1 if element %2 == 0)
  yield element*2
```

common functions
```scala
val arr1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

arr1.sum
arr1.max
```

sorting
```scala
val arr1 = Array(1, 10, 3, 106, 8, 9)

arr1.sorted  // retuns a new sorted array, there is also sortInPlace
```

There are a couple of other sorters in `import scala.util.Sorting.`

Display array by making it a string.

```scala
val arr1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

arr1.mkString(", ") // val res0: String = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
```

Counting # of negetive vals.

```scala
val arr1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

arr1.count(_ < 0) // _ acts as a each variable, I tried with other variable names just works with _
```

or you can do the same as below without _

```scala
val arr1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

arr1.count(a => a < 0)
```

Multi dimension arrays.

```scala
val matrix = Array.ofDim[Double](3, 4) // Three rows, four columns
matrix(row)(column) = 42
```

### Maps

```scala
val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)  // immutable map
val scores = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)  // mutable map

// You can omit -> by use of ()

val scores = Map(("Alice", 10), ("Bob", 3), ("Cindy", 8))  // immutable map

// getting values from map - If value is not there exception is thrown
val bobsScore = scores("Bob")

// To avoid exception use getOrElse
val bobsScore = scores.getOrElse("Bob", 0)

// Update values or create new ones --> only in mutable maps
scores("Bob") = 10
scores("Fred") = 7
scores += ("Bob" -> 10, "Fred" -> 7)

// remove values
scores -= "Alice"
```

Iterating over map

```scala
val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)  // immutable map

for ((k, v) <- scores)
  println(f"Name $k - Score $v")
```

There are two common implementation strategies for maps: hash tables and balanced trees. Hash tables use the hash codes of the keys to scramble entries, so iterating over the elements yields them in unpredictable order. By default, Scala gives you a map based on a hash table because it is usually more efficient. If you need to visit the keys in sorted order, use a `scala.collection.mutable.SortedMap` instead.

If you want to visit the keys in insertion order, use a `scala.collection.mutable.LinkedHashMap`

### Tuples

Creating a tuple
```scala
val t = (1, 3.14, "Fred")
println(t._2) // 3.14 --> index starts from 1 and not 0
```

Accessing values the better way.
```scala
val t = (1, 3.14, "Fred")
val (id, score, name) = t  // unpacking a tuple.
val (row_id, _, _) = t // use _ if you do not need all components
```

Zipping

```scala
val data = Array(Array(1, 3.14, "Fred"), Array(2, 4.14, "Sam"))
val cols = Array("id", "score", "name")

for (record <- data){
  for ((k,v) <- cols.zip(record))
    println(k, v)
}
```
```
(id,1)
(score,3.14)
(name,Fred)
(id,2)
(score,4.14)
(name,Sam)
```

### Classes

A simple Scala class

```scala
class Counter{  // values must be initialized
  private var value = 0
  def increment() {value += 1}  // methods are public by default
  def current() = value  // a function which retuns value of `value` sort of a getter
}

val counter = new Counter
counter.increment()
println(counter.current)
```

It is considered a good style to use `()` for mutator methods (a method that chagnes object state) and drop the `()` for accessor methods (which do not change the object state)

__Getters and Setters for private variables__

If you have a private variable and you wish to generate getters and setters this is the recommended way to doing it in scala.

```scala
class Counter{  // values must be initialized

  private var _value: Int = 0
  def value = _value
  def value_=(newVal : Int){  // pay attention to = before () and not after
    if (newVal > 0) _value = newVal
  }
}

var counter = new Counter()
counter.value= -10
println(counter.value)
```

__object private fields__

Any private variable or constant can be accessed by the methods of that class, also if you pass another object you can still access the private variable of that object. (E.g. when comparing 2 instances of same class)

If you wish that the private variable remains private for only that instance then you can declare it as `private[this]`. Its sometimes called object private.

__Bean properties__

Bean property will auto generate
- Scala getter setter
- Java getter setter

`import scala.beans.BeanProperty`

```scala
import scala.beans.BeanProperty
class Person{
  @BeanProperty var name: String = ""
}
```

__Constructors__

A Scala class can have many.
- 1 Primary Constructor. The primary constructor is intervowen in class definition. 
- Any number of auxiliary constructors called `this`. Each auxiliary constructor must start with a call to previously defined auxiliary constructor or the primary constructor.


```scala
class Person{
  private var name = ""
  private var age = 0

  def this(name : String){  // auxiliary constructor
    this()  // calls primary constructor
    this.name = name
  }

  def this(name : String, age : Int){ // auxiliary constructor
    this(name) // calls the previous constructor
    this.age = age
  }
}
```

Lets look at some examples of primary constructor.

```scala
class Person(private val name: String, var age: Int = 0){  // primary 
// constructor arguments
  println("I am part of primary constructor")  // any statement in the class not in method is part of primary constructor.
  def birthday() = this.age += 1
}

val sam = new Person("Sam", 10)
sam.birthday()
println(sam.age)
println(sam.name) // error as name is private
```

If you wish to mark the whole primary constructor as private

```scala
class Person private(private val name: String, var age: Int){}

```

If you do not put var or val in the constructor argument it becomes a field which is private[this] to the primary constructor. It’s just a regular parameter that can be accessed in the code of the primary constructor.

Constructor arguments mapping to getters and setters.
- no val/var - object private field
- private val/var - private field private getter/setter
- val/var - private field public getter/setter
- @BeanProperty - private field public java and scala getter/setter

__Nested Class__

Will cover this later.

### Objects

Scala has no static methods or fields, instead use object. --> Defines a single instance of a class.

```scala
object Account{
  private var serial = 0
  def newSerial() = {
    serial += 10
    serial
  }
}

for (i<- 1 until 10)
  println(f"A new serail # ${Account.newSerial()}")
```

```
A new serail # 10
A new serail # 20
A new serail # 30
A new serail # 40
A new serail # 50
A new serail # 60
A new serail # 70
A new serail # 80
A new serail # 90
```

The constructor of object is executed when its first used.

```scala
object Account{
  print("First use")
  def x() = println("In x")
}

Account.x() // First Use In x
Account.x() // In x
```

They are mostly used for

- Home for utility functions or constants
- Single immutable instance can be shared
- When a single instance is required to co-ordinate some service e.g. reading env config etc...
- As a companion objects.


A companion object has the same name as the class and can access class's private variable and methods, it needs to be placed in the same source file as the class itself and has the same name as the class.

```scala
class Account(val holder : String){
  val accountNumber = Account.newNumber()  // you cannot directly call newNumber() must do Account.newNumber()
  println(accountNumber)
}

object Account{
  private var serial = 10
  def newNumber() = {
    serial += 1
    serial
  }
}


// Introducing main function
object Main{
  def main(args: Array[String]) {
    val a1 = new Account("Sam Nelson")
    val a2 = new Account("Pete Nelson")
  }
}
```

An object can extend a class and/or one or more traits. The result is an object of a class that extends the given class and/or traits, and in addition has all of the features specified in the object definition.

__Apply method__

Its similar to `__call__` in python. You do not need to say `.apply()`

```scala
object Mult{
  def apply(x : Int) = x / 2
}

Mult(10) // 5
Mult.apply(10)  // 5
```

Apply can be used on Object or on a Class.

- On Object it will act on the Object.
- On class it will act on the instance of that class.

Lets see an example on the class.

```scala
class Person{
  def apply() = println("In apply")
}

val p1 = new Person()

p1() // calls the apply method
```

Typically, such an apply method returns an object of the companion class.

```scala
class Person private(){
}

object Person{
  def apply() = {
    println("In apply")
    new Person()
  }
}

val p1 = Person() // no need of new
val p2 = new Person()  // cannot do this as constructor of the class is private
```

__Application Objects__

Each Scala program must start with an object's main method of type Array[String]

```
object Hello{
    def main(args: Array[String]){
    }
}
```

Instead of providing main method you can also extend App

```scala
object main extends App {
    println("sfd")
}
```

In the above case if you need commandline arguments you can get from args property e.g. `args(0)` etc...

__Enumerations__

```scala
object TrafficLightColor extends Enumeration {
  val Red, Yellow, Green = Value
}
```

To study in more detail.

### Packages and import

You can import package using the import statement.
```scala
import java.awt.color.ColorSpace
```

can use wildcard to import eveything as below.

```scala
import scala.collection._
```

Imports can be anywhere not just on top of the file. The scope of the import extends will the end of the block in which the import was placed.

Import multiple things from the same package.

```scala
import java.awt.color{ActiveEvent, AWTError}
```

You can rename the class/object the same was you do in Python e.g. `import x as y`

```scala
import java.util.{HashMap => JavaHashMap}
```

__Creating packages__

To add your items to a package you can nest them as shown below.

```scala
package com{
  package mylastname{
    package myfirstname{
      class Person(){}
    }
  }
}

package com{
  package compname{
      class Person(){}
    }
}

package com.anothercomp{  // dont create 2 nested package just chain them
  class Person{}
}
```

Then you can access it as `com.mylastname.myfirstname.Person()`

- You can define 1 package in multiple files.
- You can define multiple packages in 1 file as shown above.

Instead of nesting everything in package you can put them on top of the file as shown below but everything in that file will then belong to that pcakge.

```scala
package com.company.mypackage

class Person{}
```
alternatively

```scala
package com.company
package mypackage  // breaking it down into 2 instead of just 1

class Person{}
```

A package can contain classes, objects, and traits, but not the definitions of functions or variables. To address this we have package objects. Every package can have one package object.

You define it in the parent package,

```scala
package com.horstmann.impatient
package object people {
  val defaultName = "John Q. Public"
}
```


__Package visibility__

To control the visibility of a class member package wide you can add a package as shown below

```scala
private[impatient] def description = s"A person with name $name" // where impatient is the package name
```

### Inheritance

Extends keyword inherits the file

```scala
class A{}
class B extends A{}
```

You can declare a class as `final` and it will not be inheritable.

```scala
final class c{} // cannot he inherited
```

To override method use the `override` keyword (where method is not abstract).

```scala
class A{
  def x()={}
}
class B extends A{
  override def x() = {}
}
```

Invoking a superclass method in Scala works exactly like in Java, with the keyword `super`

```scala
class A{
  def x()={}
}
class B extends A{
  super.x()
}
```

Only a primary constructor of the subclass can call the primary constructor of a super class.

Here you see a class Employee getting created which extends Person and its primary constructor also passes the values (name, age) to Person class. (As the primary constructor is intertwined with the class itself.)
```scala
class Employee(name: String, age: Int, val salary : Double) extends Person(name, age)
```

Overriding fields
- A def can override another def
- A val can override another val
- A var can override an abstract var

Anonymous class

```scala
class Person(name : String){}

val alien = new Person("Fred") {
  def greeting = "Greetings, Earthling! My name is Fred." // anonymus class
}
```

You can create abstract class in Scala, which cannot be instanciated

```scala
abstract class Person{}
```

In Scala the `eq` or `equals` method checks whether they refer to the same instance. You can override this to get the desired functionality.

It will not hurt to read the class chapter again as I skimmed through a some of the material.



### Files

The below examples creates a line iterator to process line 1 at a time.

```scala
import scala.io.Source

object main ext
ends App{
  val source = Source.fromFile("src/main/resources/file.txt")
  val lineIterator = source.getLines()

  for(line <- lineIterator)
    println(line)
}
```

```scala
import scala.io.Source

object main extends App{
  val source = Source.fromFile("src/main/resources/file.txt")
  var lines = source.getLines.toBuffer // or use toArray

  for (line<- lines) {
    println(line)
  }
}
```