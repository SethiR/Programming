---
tags: [Notebooks/JS]
title: JS-03-Objects
created: '2019-10-10T13:44:48.220Z'
modified: '2019-10-12T00:33:01.983Z'
---

# JS-03-Objects

## Plain Objects

- Objects are stored and copied by reference.
- Equality test is true if both are same object.
- Objects declared as `const` can be changed but the variable cannot point to other object or value.
- To clone the object use `.cloneDeep(obj)`

```js
let user = new Object(); // "object constructor" syntax
let user = {};  // "object literal" syntax
```

There are 2 notations to use with objects.

- `.` (dot)
- `[]` (square brackets notation)


__Add/Delete__

```js
user = {
  "likes bird" : true,  // enclose in quotes if separated by space
  isManager : false,
}

user.name = "Sam"
user["age"] = 30

delete user.age
```

__Computed Properties__

```js
let fruit = prompt("Enter Fruit?", "apple");

let bag = {
  [fruit]: 5, // the name of the property is taken from the variable fruit
};
```

__short hand__

```js
function makeUser(name, age) {
  return {
    name, // same as name: name
    age   // same as age: age
    // ...
  };
}
```

__existance check__ - use keyword `in`
```js
let user = {age: 30}
console.log("age" in user)   // note - property in quotes
```

__loop over__
```js
for (let key in user){

}
```

__Other objects__

`{}` objects (or what we have seen till now) are called plain object, there are other kinds of objects in JS e.g. Array, Date, Error and so on.

## Symbols

Object properties can be 
- Strings
- Symbols

__Properties of Symbols__
- They do not participate in `for .. in` loop
- They are gaureenteed to be unique even if the text is the same.
- They are used to create hidden properties inside an object. (Think object exhange between 2 scripts, one script wants to add something to the object without notifiying other script, they can use Symbol() because its hidden. )


```js
let id1 = Symbol("id")
let id2 = Symbol("id")

let user = {
    fname: "Sam",
    [id1]: 1,  // when adding symbols use []
    [id2]: 2, 
}
```

__Global Symbols__

- In order to read (create if absent) a symbol from the registry, use `Symbol.for(key)`

```js
// get symbol by name
let sym = Symbol.for("name");
let sym2 = Symbol.for("id");

// get name by symbol
alert( Symbol.keyFor(sym) ); // name
alert( Symbol.keyFor(sym2) ); // id
```

There exist many “system” symbols that JavaScript uses internally, and we can use them to fine-tune various aspects of our objects.

## `this`

`this` is a keyword used to refer to the current object.

When we write our code using objects to represent entities, that’s called object-oriented programming, in short: “OOP”.

__this is not bound__

The value of this is evaluated during the run-time, depending on the context.

```js
let user = { name: "John" };
let admin = { name: "Admin" };

function sayHi() {
  alert( this.name );
}

// use the same function in two objects
user.f = sayHi;
admin.f = sayHi;

// these calls have different this
// "this" inside the function is the object "before the dot"
user.f(); // John  (this == user)
admin.f(); // Admin  (this == admin)

admin['f'](); // Admin (dot or square brackets access the method – doesn't matter)
```

## Constructor and `new`

- The main purpose of constructors – to implement reusable object creation code. 
- The “capital letter first” is a common agreement, to make it clear that a function is to be run with new.



```js
// Approach1 -> Constructor Function
function Person(name){
    this.name = name;
    this.isAdmin = false;
}

let user1 = new Person("Jack") // Person {name: "Jack", isAdmin: false}


// Approach2 -> Literals.
let user2 = {
    name: "Jack",
    isAdmin: false,
} // {name: "Jack", isAdmin: false}

```

- Usually, constructors do not have a return statement. Their task is to write all necessary stuff into this, and it automatically becomes the result.

Technically you can call any function with new as shown below. however in this case the return statement when returns any primitive is ignored and it returns this i.e. the object which was created by new.

```js
function sum(a, b){
    return a + b
}

console.log(new sum(10, 20)) // sum {}
```

### Methods in constructor functions.

A normal object can have methods as well as constructor objects can have methods as shown below.

```js
literalUser = {
    fname: "John",
    lname: "Nelson",
    getFullName: function(){
        return this.fname + " " + this.lname
    }
}

console.log(literalUser)
console.log(literalUser.getFullName())

function ConstructorUser(fname, lname){
    this.fname = fname;
    this.lname = lname;
    this.getFullName = function(){
        return this.fname + " " + this.lname;
    }
}

let cuser = new ConstructorUser("John", "Nelson");
console.log(cuser);
console.log(cuser.getFullName());
```

## Map and Set

Map is a collection of keyed data items, just like an Object. But the main difference is that Map allows keys of any type.

- `new Map()` – creates the map.
- `map.set(key, value)` – stores the value by the key.
- `map.get(key)` – returns the value by the key, undefined if key doesn’t exist in map.
- `map.has(key)` – returns true if the key exists, false otherwise.
- `map.delete(key)` – removes the value by the key.
- `map.clear()` – removes everything from the map.
- `map.size` – returns the current element count.

```js
let john = { name: "John" };

// for every user, let's store their visits count
let visitsCountMap = new Map();

// john is the key for the map
visitsCountMap.set(john, 123);

alert( visitsCountMap.get(john) ); // 123
```

__Looping over maps__


For looping over a map, there are 3 methods:

- `map.keys()` – returns an iterable for keys,
- `map.values()` – returns an iterable for values,
- `map.entries()` – returns an iterable for entries [key, value], it’s used by default in for..of.

A Set is a special type collection – “set of values” (without keys), where each value may occur only once.

```js
let set = new Set();

let john = { name: "John" };
let pete = { name: "Pete" };
let mary = { name: "Mary" };

// visits, some users come multiple times
set.add(john);
set.add(pete);
set.add(mary);
set.add(john);
set.add(mary);

// set keeps only unique values
alert( set.size ); // 3
```

## WeakMap

The first difference from Map is that WeakMap keys must be objects, not primitive values. The main area of application for WeakMap is an additional data storage. If we’re working with an object that “belongs” to another code, maybe even a third-party library, and would like to store some data associated with it, that should only exist while the object is alive – then WeakMap is exactly what’s needed.

We put the data to a WeakMap, using the object as the key, and when the object is garbage collected, that data will automatically disappear as well.

```js
weakMap.set(john, "secret documents");
// if john dies, secret documents will be destroyed automatically
```

Another common example is caching: when a function result should be remembered (“cached”), so that future calls on the same object reuse it.


## WeakSet

WeakSet behaves similarly:

- It is analogous to Set, but we may only add objects to WeakSet (not primitives).
- An object exists in the set while it is reachable from somewhere else.
- Like Set, it supports add, has and delete, but not size, keys() and no iterations.


## Destructuring assignment

Destructuring assignment is a special syntax that allows us to “unpack” arrays or objects into a bunch of variables, as sometimes that’s more convenient. Destructuring also works great with complex functions that have a lot of parameters, default values, and so on.

```js
let arr = ["Ilya", "Kantor"]
let [firstName, surname] = arr;
```

Ignore elements using commas

```js
let [firstName, , title] = ["Julius", "Caesar", "Consul", "of the Roman Republic"];
```

The Rest

```js
let [name1, name2, ...rest] = ["Julius", "Caesar", "Consul", "of the Roman Republic"];
```

Working with objects
```js
let {var1, var2} = {var1:…, var2…}
```

_Example_

```js
let options = {
  size: {
    width: 100,
    height: 200
  },
  items: ["Cake", "Donut"],
  extra: true
};

// destructuring assignment split in multiple lines for clarity
let {
  size: { // put size here
    width,
    height
  },
  items: [item1, item2], // assign items here
  title = "Menu" // not present in the object (default value is used)
} = options;

alert(title);  // Menu
alert(width);  // 100
alert(height); // 200
alert(item1);  // Cake
alert(item2);  // Donut
```
