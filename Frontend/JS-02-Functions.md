---
tags: [Notebooks/JS]
title: JS-02-Functions
created: '2019-10-09T17:47:09.833Z'
modified: '2019-10-10T13:44:13.697Z'
---

# JS-02-Functions

## Declaration

```js
function <funcName>(<arg1>, <arg2> = <default>, ... ){
// code
return <result>
}
```

- A function with an empty return or without it returns undefined
- primitive values are pass by value.
- objects and arrays are pass by reference.

_Naming Conventions_

- "get…" – return a value,
- "calc…" – calculate something,
- "create…" – create something,
- "check…" – check something and return a boolean, etc.

## Function as Expression

Function can be assinged as values.

```js
let sayHi = function(name){
  alert("Hello " + name)
}

// later executed.
sayHi('Sam')

// also assign to another variable
let func = sayHi
func('Peter')
```

### Callback functions

These function expressions can be passed as arguments which are then 'called back'. The arguments `showOk` and `showCancel` of ask are called _callback functions_ or just _callbacks_.

```js
function ask(question, yes, no) {
  if (confirm(question)) yes()
  else no();
}

function showOk() {
  alert( "You agreed." );
}

function showCancel() {
  alert( "You canceled the execution." );
}

// usage: functions showOk, showCancel are passed as arguments to ask
ask("Do you agree?", showOk, showCancel);
```

## Arrow Functions

`let func = (arg1, arg2, ...argN) => expression` so left side of `=>` is arguments and right side is the function code.

```js
let age = prompt("What is your age?", 18);

let welcome = (age < 18) ?
  () => alert('Hello') :
  () => alert("Greetings!");

welcome(); // ok now
```
