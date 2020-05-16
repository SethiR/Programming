# Keywords

- auto
- else
- long
- switch
- break
- enum
- register
- typedef
- case
- extern
- return
- union
- char
- float
- short
- unsigned
- const
- for
- signed
- void
- continue
- goto
- sizeof
- volatile
- default
- if
- static
- while
- do
- int
- struct
- _Packed
- double

# Types

- char
    - char
    - signed char
    - unsigned char
- int
    - int
    - unsigned int
    - short
    - unsigned short
    - long
    - unsigned long
- float
    - float
    - double
    - long double

# Structure

```c
typedef struct {
    float x;
    float y;    
} point;
```

__Program__

- `size_t` - size_t can store the maximum size of a theoretically possible object of any type. E.g. for 64 bit computers it will store 64 bit.
- `++i` is a little bit faster becase it just increments where as `i++` copies and then increments.

```C
#include <stdio.h>

int main(){

    for(size_t i=0; i<=5; ++i){
        printf("%zu\n",i);
    }

}
```

__Program__

As `size_t` is non negative this program will terminate once i reaches 0, else would have been like an endless loop.

```C
#include <stdio.h>

int main(){
    for(size_t i = 10; i<=10; --i){
        printf("%zu\n",i);
    }
}
```

__Program__

Printing floats with precesion.

```C
#include <stdio.h>

int main(){
    double eps = 1E-9;

    printf("%.12f", eps);  // 0.000000001000

}
```

__Program__

```C
for (;;) {  // its equivalent to while(true)
    double prod = a * x ;
    if ( fabs (1.0 - prod ) < eps ) {
        break ;
    }
    x *= (2.0 - prod ) ;
}
```