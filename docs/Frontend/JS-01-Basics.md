---
tags: [Notebooks/JS]
title: JS-01-Basics
created: '2019-10-09T14:05:23.635Z'
modified: '2019-10-11T19:06:21.233Z'
---

# JS-01-Basics


## Language History and Tools

- JavaScript is based on specification for ECMAScript.
- JS can execute in browser, server and any device which has the JavaScript engine.
 

__Various Engines__

- V8 in Chrome & Opera
- SpiderMonkey – in Firefox

## Points to note

__Usage__

2 ways

- Use a script tag in html.
- Include a JS file.

```html
  <script>
    alert( 'Hello, world!' );
  </script>
```

__Comments__

```javascript
// single line

/*
multi line
comments
*/
```

__Use Strict__
`"use strict"` switches the engine to the modern mode. Put this as the first line of the script and its recommended to always have the strict mode enabled.


## Variables and Data types

- Variables Are case sensitive.
- Use `let` and `const`.

### Numbers

- numbers are both integer and floating point numbers.
- you can use `typeof(a)` to check the type of a variable.

```js
let billion = 1e9;  // declaring large numbers with e notation.
```

__Converting to Strings__

```js
let num = 255;
alert( num.toString(16) );  // ff
```

__Rounding__

- Math.floor
- Math.ceil
- Math.round
- Math.trunc

__Fixed decimal places__

```js
let num = 12.34;
alert( num.toFixed(1) ); // "12.3"
```

__parse from a string__

```js
alert( parseInt('100px') ); // 100
alert( parseFloat('12.5em') ); // 12.5
```

---

### Strings

- enclosed by single or double quotes.
- using backticks you can use `${<var>}` to embed value in the string. You can also put expression inside this e.g. `${1 + 2}`
- Check length using `strVariale.length`
- Access specific character using `strVariable[index]`
- Strings are immutable
- `'Interface'.toUpperCase() // INTERFACE`
- Getting index of a substring staring from given `pos` -> `str.indexOf(substr, pos)`
- str.lastIndexOf(substr, position)
- `includes()` `endsWith()` `startsWith()`



Iterating over characters
```js
for (let char of "Hello") {
  alert(char); // H,e,l,l,o (char becomes "H", then "e", then "l" etc)
}
```

__SubStrings__

```js
str.slice(0, 5)
```


__Conversions__

_String to Number_

```javascript
let str = '123'
let num = Number(str)
```

_Boolean_

`Boolean(1)` is true
`Boolean(0)` is false

## Comparison

```javascript
alert( '' == false ); // true
alert( 0 === false ); // false, because the types are different
```

## Prompt / Alert / Confirm

_prompt_

Its used to input value.

```javascript
result = prompt('Message', default);
```

e.g.
```javascript
result = prompt('Enter your age', 100);
```

_confirm_

Its used to confirm a user action. It returns `true` or `false` depending on user clicks OK or Cancel.

```javascript
result = confirm('Do you wish to continue ?');
```

## Conditionals

### If condition

```js
if (condition) {

} else if(condition) {

} else {

}
```

```js
let accessAllowed = (age > 18) ? true : false;
```

- `||` - Or
- `&&` - And
- `!` - Not

### Switch statement

```js
let a = 2 + 2;

switch (a) {
  case 3:
    alert( 'Too small' );
    break;
  case 4:
    alert( 'Exactly!' );
    break;
  case 5:
    alert( 'Too large' );
    break;
  default:
    alert( "I don't know such values" );
}
```

## Loops

### While

```js
while (condition) {

}
```

```js
do {
  // loop body
} while (condition);
```

### `for` loop

```js
for (begin; condition; step) {
  // ... loop body ...
}
```

_`break` and `continue`_

`break` statement is used to break the loop and `continue` is use to continue to next iteration of the loop.


_break with label name_

```js
outer: for (let i = 0; i < 3; i++) {

  for (let j = 0; j < 3; j++) {

    let input = prompt(`Value at coords (${i},${j})`, '');

    // if an empty string or canceled, then break out of both loops
    if (!input) break outer; // (*)

    // do something with the value...
  }
}
alert('Done!');
```

## Arrays

Two ways to create Arrays.

```js
let arr = new Array();
let arr = [];
```

Common attributes and functions.
- `Array.length`
- From begining of array - `shift` `unshift`
- End of array - `pop` `push`


__Looping on the array__

```js
let fruits = ["Apple", "Orange", "Plum"];

// iterates over array elements
for (let fruit of fruits) {
  alert( fruit );
}


// or the 'in' style

let arr = ["Apple", "Orange", "Pear"];

for (let key in arr) {
  alert( arr[key] ); // Apple, Orange, Pear
}

```

__slice__

It returns a new array copying to it all items from index start to end (not including end). Both start and end can be negative, in that case position from array end is assumed.


```js
arr.slice([start], [end])
```

__concat__

The method arr.concat creates a new array that includes values from other arrays and additional items.

```js
arr.concat(arg1, arg2...)
```

__Iterate : forEach__

```js
arr.forEach(function(item, index, array) {
  // ... do something with item
});
```

e.g.

```js
["Bilbo", "Gandalf", "Nazgul"].forEach((item, index, array) => {
  alert(`${item} is at index ${index} in ${array}`);
});
```

e.g.

```js
a = [10,20,30,40,50,60,70,80,90,100]
a.forEach(function(item, index, array){
    console.log(item*2)})
```

__Searching in Array__

- IndexOf()
- lastIndexOf()
- includes()

__find and ifndIndex__

Imagine we have an array of objects. How do we find an object with the specific condition?

```js
let users = [
  {id: 1, name: "John"},
  {id: 2, name: "Pete"},
  {id: 3, name: "Mary"}
];

let user = users.find(item => item.id == 1);

alert(user.name); // John
```

__filter__

Filter does what find does but checks for multiple objects in the array e.g.

```js
let users = [
  {id: 1, name: "John"},
  {id: 2, name: "Pete"},
  {id: 3, name: "Mary"}
];

// returns array of the first two users
let someUsers = users.filter(item => item.id < 3);

alert(someUsers.length); // 2
```
