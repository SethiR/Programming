---
tags: [Notebooks/JS]
title: JS-04-Arrays
created: '2019-10-11T19:06:29.968Z'
modified: '2019-10-11T19:06:33.805Z'
---

# JS-04-Arrays

## Array Functions

### Map

The arr.map method is one of the most useful and often used. It calls the function for each element of the array and returns the array of results.

```js
a = [10, 20, 30, 40, 50]

r = a.map(function(item){
        return item*2
    }
)

console.log(r);
```

### Sort
This does not sort numberically but converts to String and then sorts it. 

To sort numerically or by any means you can put in your own method like showen below.

```js
arr.sort( (a, b) => a - b );
```

### Split and Join

```js
let str = "test";
alert( str.split('') ); // t,e,s,t
```


```js
let arr = ['Bilbo', 'Gandalf', 'Nazgul'];
let str = arr.join(';'); // glue the array into a string using ;
alert( str ); // Bilbo;Gandalf;Nazgul
```


### Reduce and reduceRight

They are used to calculate a single value based on the array.

Syntax : - 

```js
let value = arr.reduce(function(previousValue, item, index, array) {
  // ...
}, [initial]);
```

Example : - 

```js
let arr = [1, 2, 3, 4, 5];

let value = arr.reduce(
    function(p, item){
        return p + item;
    }
)

console.log(value);
```