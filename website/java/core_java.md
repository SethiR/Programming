## Basics

__Comments__

```Java
// Line Comments

/* Block Comments */

/** Java Doc Comments */
```

---

__Introducing Packages__

- Packages provide organization
- Naming conventions
  - Packages follow standard naming convention
  - all lower case (Use reversed domain name)
    - e.g. packages created by rajatsethi.ca will be `package ca.rajatsethi`
    - packages created by pluralsight.com will be `package com.pluralsight`
    - You can then further subdivide by group within a company etc... e.g. `package com.pluralsight.projecta;`
    - For larger organization you can further divide into `package com.pluralsight.accounting.projecta;` and `package com.pluralsight.it.projecta;`
- Affect source code file structure
  - Each `.` will create a subfolder inside a `src` directory.

All members become part of that package. In the example given below the `Main` class becomes part of the `package com.company` and becomes
`com.company.Main`

```Java
package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}
```

---

__Primitive Data Types__

By convention we follow "Camel Casing" in java.

- First letter is lower case
- Start of each word is upper case
- rest all lower case
- e.g. bankAccountBalance, levelTwoTraining

```Java
// Primitive Data Types


// Int
byte
short
int
long

// Float
float
double

// Character
char regularU = '\u00DA';

// Boolean
boolean x = true;
```

Java primitive types are stored by value.

```Java
int x = 10;
int y = x;  // The is distinct sepereate memory location for y
```

---

__Operators__

Basic Math operators `+ - / * %`

Postfix and prefix `++ --`

Compound assignment operators `+= -= *= %= /=`



__Operator Precedence__

- Postfix `x++ x--`
- Prefix `++x --x`
- Multiplicative `* / %`
- Additive `+ -`

---

__Type conversions__

There are 2 types of `type conversions`
- implicit
- explicit

```Java
// Implicit conversion
int iVal = 50;
long lVal = iVal; // Java is doing int to long implicit conversion

// Explicit conversion
long lVal = 50;
int iVal = (int) lVal;
```

*Implict conversions*

- Usually the widening conversion are implicit i.e. automatic e.g. int to long
- Mixed - Java will use largest integer in equation.
- Mixed int and float - Will cast to largest floating point in the equation

*Explicit conversions*

- Both widening and narrowing
- Float to int --> fraction will be dropped
- Int to float --> can loose precesion


```Java
// Example
short shortVal = 10;
long longVal = 10;

// This will give you a error because implicit cannot be narrowing
short result = shortVal - longVal;
```

So do explicit type cast.

```Java
short shortVal = 10;
long longVal = 10;
short result = (short) (shortVal - longVal);
```

---

__Conditional Logic__

Relational Operators `> < >= <= == !=`

*Conditional Assignment*

```Java
// result = condition ? true_val : false_val

int v1 = 7;
int v2 = 5;

int result = v1 > v2 ? v1 : v2;
```

_If_

_Else if_

_Else_

_Nested If_

---

**Block Statement**

- A variable declared with the block statement is only visible inside the block statement.
- Where the variable is visible is its `scope`.

---

**Locical Operators**

- AND `&`
- Or `|`
- Exclusive Or (XOR) `^`
- Negation `!`



**Conditional Logical Operators**

These only execute the right-side if needed to determine the result.

- Conditional AND `&&`
- Conditional OR `||`

__& vs &&__

`&`
```Java
int rooms = 0;
int students = 150;

if (rooms > 0 & students/rooms > 30){   // This will give divide by 0 error.
    System.out.println("Crowded");
}
```

`&&`
```Java
int rooms = 0;
int students = 150;

if (rooms > 0 && students/rooms > 30){   // This will not give divide by 0 error because it evaluates right side only when left side is true.
    System.out.println("Crowded");
}
```

---

__While Loop__
```Java
while(condition){

}
```

__While Loop__
```Java
do{

}while(condition)
```

__For Loop__
```Java
for(initialize; condition; update){

}
```

---

__Arrays__

- Provide an ordered collection of elements of same type.

```Java
float[] theVals = new float[3];  // Array

for(int i=0; i<theVals.length; i++){

}

float[] theVals = {10.0f, 20.0f, 30.0f};  // alternate way of declaring array
```

__For Each__

For each loop executes the code once for each memeber of the array. It automatically handles getting the collection length and accessing each value.

```Java
float[] theVals = {10.0, 20.0, 30.0};
float sum = 0.0;

for(float currentVal : theVals){
    sum += currentVal;
}

System.out.println(sum);
```

---

__Switch__

Only primitive supported with `switch` are `char` and `int`

```Java
switch(test-value){
    case value-1:
        statements
    case value-2:
        statements
    default:
        statements
}
```

Example of switch. Note always put a `break` at end of ****
```Java
int iVal = 10;

switch(iVal % 2){
    case 0:
        System.out.print(iVal);
        System.out.println(" is even");
        break;
    case 1:
        System.out.print(iVal);
        System.out.println(" is odd");
        break;
    default:
        System.out.println("Oops it broke");
        break;
}

```





## Object Oriented Java

- Java is object oriented language
- Objects encapsulate data, operations and usage semantics
  - Allow storage and manipulation detail to be hidden
- When creating classes the source file name is same as the name of the class. (For public class its mandatory)

Classes are reference types. When we create 2 objects of the same class and we say `object2 = object1`, it means that `object1` and `object2` point to the same memory address.

---

__Encapsulation and Access Modifiers__

This concept is also often used to hide the internal representation, or state, of an object from the outside.

- `No access modifier` --> Only within own package
- `public` - Everywhere
- `private` - Only within its own class

---

__Naming Classes__

- Follow "Pascal Case"
    - All first char in words are capital e.g. `BankAccount`
    - Use simple, descriptive names

---

__Methods__

- `void` no return value
- A method can return a single value
  - a primitive value
  - a reference to an object
  - a reference to an arary (array are objects)

The below example demonstrates different return types.

```Java
// Class Flight
public class Flight{
    private int passengers;
    private int seats;

    // Constructors and other methods

    public boolean hasRoom(Flight f2){
        int total = passengers + f2.passengers;
        return total <= seats;
    }

    public Flight createNewWithBoth(Flight f2){  // returns a new object of the class.
        Flight newFlight = new Flight();
        newFlight.seats = seats;
        newFlight.passengers = passengers + f2.passengers;
        return newFlight;
    }


}
```

```Java
// Main Function
Flight lax1 = new Flight();
Flight lax2 = new Flight();
// add passengers to both flights

Flight lax3;
if (lax1.hasRoom(lax2)){
    lax3 = lax1.createNewWithBoth(lax2);
}
```

---

__Special References__

- `this` - implicit reference to the current object.
    - useful for reducing ambiguity
    - allows an object to pass itself as a parameter

```java
 public Flight createNewWithBoth(Flight f2){  // returns a new object of the class.
        Flight newFlight = new Flight();
        newFlight.seats = seats;
        newFlight.passengers = this.passengers + f2.passengers;
        return newFlight;
    }
```

- `null` - is a reference literal
    - represents an uncreated object
    - can be assigned to any reference variable


```Java
// Main Function
...
Flight lax3 = null;  // uncreated object is assigned null.
...
```

---

__Field Encapsulation__

We use `getters` and `setters` instead of exposing the fields of the class.

---

__Establishing Initial State of the fields__

There are 3 ways to do this

- Field Initial State
- Constructor
- Initialization Blocks

__1. Field Initial State__

The variables have to be initialized before you can use them. e.g. the below will give you an error.

```java
public static void main(String[] args) {
        int x;
        System.out.print(x); // Error - x is not initialized
    }
```

However `fields` i.e. class variables receive "zero" value by default.

- int --> defaults to `0`
- float --> defualts to `0.0`
- char --> defaults to `\u000`
- boolean --> defualts to `false`
- reference types --> defaults to `null`

or you can initialize them yourself

```java
public class Earth{
    long circum = 24901; // initializing manually
}
```

__2. Constructors__

- Constructor has no return type.
- Every class has at least 1 constructor.
- If there are no explict constructor Java provides one in the background.
- A class can have multiple constructors with different parameter list

_2.1 Chaining Constructors_

- You can call another constructor from within an other constructor. (This is called constructor chaining). You can do that by using `this()`
- Call to other constructor must be the first line of the current constructor.

e.g.

- In this class we have 4 constructors. Not all the constructors need to be `public`.

```Java
public class Passenger{

    public Passenger(){}

    public Passenger(int freeBags){
        this(freeBags > 1 25.0 : 50.0);
        this.freeBags = freeBags
    }

    public Passenger(int freeBags, int checkedBags){
        this(freeBags);
        this.checkedBags = checkedBags;
    }

    private Passenger(double perBagFee){
        this.perBagFee = perBagFee;
    }

}
```
```Java
// Main
Passenger jane = new Passenger(2,3);
```

1. `public Passenger(int freeBags, int checkedBags)`
2. then `this(freeBags)` is called from the above constructor
3. Which calls `public Passenger(int freeBags)`
4. Which in turn calls the `private Passenger(double perBagFee)` for setting the perBagFee
5. ...


__3. Initialization Blocks__

- Initialization blocks are share across all constructors
- Executed as if the code was placed at start of each constructor.
- There can be multiple initialization blocks and they are executed in top down fashion

```Java
public class Flight{

    private int seats;

    { // Start of initialization block

    } // end of initialization block

    public Flight(){

    }


}
```

__Order of Execution__

Java follows the below mentioned order for field initialization and constructor.

1. Field Initialization (Field initial state)
2. Initialization Block
3. Constructor


---

__Overloading__

A class can have the same method name multiple times. Signature needs to be different e.g.

- Number of parameters
- Type of each parameter

---

__Any number of parameters__

A method can be declared to accept variable number of parameters.
- Place `...` after parameter type
- It can be done only for the last parameter

```Java
public class Flight{

    public void addPassenger(Passenger... list){ // same as *args in Python
        for (Passenger p: list){
            // Code here
        }
    }

}
```

---

### Inheritance

Use `extends` keyword.

```Java
public class CargoFlight extends Flight{

}
```

One not commonly known phoenomenon is objects of the derived class can be created using the Base type

e.g.

```Java
Flight f = new CargoFlight();
```

now in `f` we can use `Flight` class methods and capabilities but not `CargoFlight` class capabilities. This is uselful in grouping the objects.

---

__Object Class__

The object class is the root of the java class hierarchy. So we can reference the object class as well.

```Java
Object[] stuff = new Object[3];
stuff[0] = new Flight();
stuff[1] = new Passenger(2, 4);
stuff[2] = new CargoFlight();
```

Another example

```Java
Object o = new Passenger();
o = new CargoFlight();

// as of yet o will only be able to access functionality of 'Object' class in Java
// When you need o to access functionality of CargoFlight you need to do the following

CargoFlight cf = (CargoFlight) o; // Typecast o to CargoFlight and cf will point to save memory address of o but will be able to access methods of CargoFlight class.

cf.addPassenger();
```

_Methods of Object class_

- `clone`
- `hashCode`
- `getClass`
- `finalize`
- `toString`
- `equals`

---

**Equality**

`==`

- For reference types it checks if the objects point to the same instance.

You can override the default `equals` implementations.

```Java
class Flight{
    private int flightNumber;
    private int flightClass;

    @Override
    public boolean equals(Object o){

        // As we are getting object o and we need data from the Flight class we need to cast it.
        if (!(o instanceof Flight){)
            return false;

        Flight other = (Flight) o;
        return this.flightNumber == other.flightNumber;
    }
}
```

---

**Super**

- `Super` treats the object as if it was an instance of its base class
- useful for accessing base class members that have been overridden

```Java
class Flight extends object
{
    @Override
    public boolean equals(Object o)
    {
        super.equals(o);  // Calling the super class method
    }
}
```

---

**Final and Abstract**

- By default all classes can be extended

Creating a final class. A `final` class cannot be extended or inherited from.

```Java
public final class Passenger{

}
```

You can also make a particular method as final and not the whole class thus that method cannot be overriden.

```Java
public class CargoFlight{
    public void methodA(){}
    public final void methodB(){}
}
```

__Abstract__

`Abstract` will require that the class _will be_ inherited or a method _will be_ overriden. If any method in a class is abstract you need to mark the whole class as abstract.

```Java
public abstract class Pilot{
    public void methodA(){

    }

    public abstract boolean canAccept(Flight f);  // this is an abstract method.
}
```


Abstract class cannot be instanciated. The below code will give you an error.

```Java
abstract class Pilot{
    Pilot(){}
}


public class Main {
    public static void main(String[] args) {
        Pilot p1 = new Pilot();  // Trying to instanciate a abstract class.
    }
}
```

---

**Inheritance and Constructor**

- Constructors are not inherited
- A base class constructor must always be called.
    - If you do not do this explicitly, Java will call the base class no argument constructor automatically.
    - If you call manually make sure you call the base class constructor in the first line of the current class constructor.


```Java
// TODO example (added a note on the video)
```

---

## More Data Types

**Strings**

- Java string has UTF-16 encoding
- Use double quotes ""
- contatenate using `+`
- String objects are immutable. (They cannot be changed but new value can be assigned to them). This can be inefficient


_Methods_

- `length`
- `valueOf` - convert non string value to a string
- `concat`
- `replace`
- `toLowerCase`
- `trim`
- `split`
- `format`
- `chatAt`
- `substring`
- `contains`
- `startsWith`
- `equals`
- `equalsIgnoreCase`
- `...`


[Documentation on `String` Methods](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html)


---

**String Equality**

```Java
String s1 = "I Love";
s1 += "Java";

String s2 = "I";
s1 += " Love Java";

if (s1 == s2) {} // Its False

```

The above example returns false because they are not the exact same instance of the String even though they have the same value in them.

```Java
if (s1.equals(s2)){}  //  This will return True
```

`.equals` does a char by char comparison.

As the char based comparisons are quiet expensive we use the `intern` method to compare strings. `intern` method returns a cannonical form of the string based on its value.

```Java
s1.intern() == s2.intern(); // Will return true
```

`intern` does have its overhead so use it only if you are doing comparisons over and over again. So lets say you have multiple strings which are master data and you want to search (multiple times) a new string exists in this array of strings or not. In this case turn the array of strings into `intern` and comapre using the `==` operator which will be inexpensive.


---

**Convert to String**

```Java
int iVal = 100;
String sVal = String.valueOf(iVal);

// sVal = "100"
```

Remember that `object` class provides some standard methods that all classes will have. Such a method is `toString` which is used to get the string representation of various objects e.g. an object of a class.


```Java
public class Flight{
    int flightNumber;

    @Override
    public String toString(){
        return "I fly to" + flightNumber;
    }
}
```

---

**String Builder**

Remember strings are immutable but sometimes we wish to manipulate them.

- StringBuilder provides mutable string buffer
    - General recomendation --> pre-size buffer


```Java
StringBuilder sb = new StringBuilder(40);

// Sample methods
// append
// insert

sb.append("I flew to ");
sb.append("Florida");

sb.insert(4, "at");

// convert back to String
String message = sb.toString();
```


---

**Classes vs Primitives**

!!! note ""
    You may not use this very often.

Classes

- Provide convenience
- incurs an overhead

Primitives

- Provide efficiency


So we sometimes we use `Primitive Wrapper Class`. The standard class hierarchy for primitive type looks like this.

Classes shown below: -

    Object
        Boolean
        Number
            Byte
            Short
            Integer
            Long
            Float
            Double
        Character


- All primitive wrapper class instances are immutable.

When you create an `int` variable it is an instance/object of the `Integer` class shown above. The conversion is done automatically.

Java also provides methods for explicit conversions.

- Primitive to wrapper -> `valueOf`. This is known as boxing.
- Wrapper to primitive => `xxxValue`. This is known as unboxing.
- String to primitive -> `parseXxx`
- String to wrapper -> `valueOf`

Using this you can treat the primitive type as an object.

_Example 1 - Treat as object_

```Java
Object[] stuff = new Object[3];
stuff[0] = new Flight();
stuff[1] = new Passenger(0,2);
stuff[2] = 100;
```

_Example 2 - Null References_

```Java
public class Flight{
    Integer flightNumber;  // note we are not creating int but Integer which creates it as an object
    Character flightClass;  // same as above

    @Override
    public String toString(){
        if (flightNumber != null){  // we can now compare int to null because its an object. Else as soon as you create an object of flight class the int will get value of 0 and if there is any flight number with value 0 the comparison will not work e.g. flightNumber != 0 is leaving 1 case out where as using int as Integer i.e. as object we can also cover the case of 0.

        }
        else if(flightClass != null){

        }
    }
}
```

[Sample documentation for Interger Class](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Integer.html)

Refer the same for other primitive wrapper classes.

**Wrapper Class Equality**

Did not read a lot on this becase seemed I may never use it. (Check slides)


**Final Fields**

- Final
- Static - Cannot be set by an object instance.


```Java
public class Flight{
    static final int MAX_FAA_SEATS = 500;
}
```

**Enumeration types**

Its useful for defining a type with a finite list of valid values. Declare with keyword `enum` and provide a comma separated value list of types.

```Java

public enum FlightCrewJob {
    Pilot,
    CoPilot,
    FlightAttendant
}

public class CrewMember{
    private FlightCrewJob job;
}

// How to create
CrewMember judy = newCrewMember(FlightCrewJob.CoPilot);
```


## Exceptions

- `try/catch/finally`
- `Try` block will run the normal code
- `Catch` block is called only if matching exception is thrown
- runs in all cases when `try` block or `catch` block finishes. It usually contains clean up code.

```Java
public class test {
    public static void main(String[] args) {
        int i = 1;
        int j = 0;

        try {
            System.out.println(i/j);
        }catch (Exception e){
            System.out.println("Error : " + e.getMessage());
            System.out.println(e.getStackTrace());
        }finally {
            System.out.println("Program continues...");
        }

    }
}
```

Reading file in `JAVA` using exception handling.

```Java

import java.io.BufferedReader;
import java.io.FileReader;

public class test {
    public static void main(String[] args) {

        BufferedReader reader = null;
        int total = 0;

        try {  // try reading the file
            reader = new BufferedReader(new FileReader("C:\\a.txt"));
            String line = null;

            while((line = reader.readLine()) != null){  // read line by line
                System.out.println(line);
                total += Integer.valueOf(line);
                System.out.println("Total = " + total);
            }
        }
        catch (Exception e){  // print any exception when reading the file
            System.out.println(e.getMessage());
        }
        finally {  // here we close the file using nested try and catch
            try {
                if (reader != null)
                    reader.close();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}

```

---

**Exception Class Hierarchy**
(Check slide)

- Object
    - Throwable
        - Error
            - Linkage Error
            - ...
        - Exception
            -Runtime Exception
              - Null Pointer Exception
            - ...


Some of the excpetions are checked exceptions and some are unchecked exceptions. Its mandatory to handle `Checked Exceptions`

Exception are handled by Type

- Each type of exception can have a seperate catch block.
- each catch is tested from top to bottom
- first assignable catch is selected

- You should start with Specific exceptions at the top and then get general as you go down.

---

__Exception and methods__

In some cases a method does a processing of say a file open. But the file name is being passed by another method.

The method which is opening the file will get the exception if the file name is not correct but it should be the method which sends the file name which should be made aware of this exception so we can use `throws Exception` on the method which is opening the file and this will propogate up the call stack.


```Java
public class Flight{
    public void addPassengers(String filename) throws IOException{
        // ...
        try{
            // open file
        }
        finally{
            // close file
        }
    }
}
```

As you see in the above example the method which is receiving the file name is not catching the exception its just `throws IOException` to the caller method.

- The throws clause of an overriding method must be compatable with the throws clause of the overriden method.

---

__Throwing Exception__

Exceptions are objects, they have to be created before they are thrown. Put meaning full information in it. When caused by another exception, include orignal originating exception by using `initCause` method.


You can also create your own excpetion types and throw them however in most of the cases you will use the existing exception types.

- Inherit from Exception class.
- Make them checked excpetions.
- Constructors are often their only members


---

## Packages

A package is a group of related types

- It creates a namespace, useful in naming collisions. Usually use reverse domain naming.
- It provides access boundaries
- It acts as a unit of distribution

e.g.

```Java
package com.examplesite.travel;
public class Flight{

}
```

to use this you can fully qualify the type like we do below
```Java
com.examplesite.travel.Flight lax178 = ...;
```

- Whenever you are working in package you don't have to fully qualify
- standard ones need not be fully qualify
- for others use type imports

_Type imports_

we do this using `import` statement.

e.g.
```Java
import com.pluralsight.travel.Flight;
import com.xyzcompany.bar;

Flight = ;
Wine = ;
```

Package can serve as an access boundary. No access modifier is by default a package private. Others are public, private and protected.