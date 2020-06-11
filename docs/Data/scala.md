# Scala

- var - Values can change.
- val - Values cannot change, its a constant

```scala
// comment
/*
multi
line
comment
*/
```


__Data types__

Scala Tye system

![](https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg)

- Byte
- Boolean
- Char
- Short
- Int
- Long
- Float
- Double

To get a really big number you can use the `BigInt`.

To get big decimal you can use `BigDecimal`

All datatypes are objects in Scala.

Unit is similar to void in java. (No return)


__Creating variables__

```scala
val x : Int = 1234;
```

```scala
var y : Long = 234234;
```

```scala
val z : Char = 'Z';
```

```scala
val (myVar1: Int, myVar2: String) = Pair(40, "Foo")
```