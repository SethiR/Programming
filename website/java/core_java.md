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


### More Data Types

**Strings**

