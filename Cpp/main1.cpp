#include <iostream>

using namespace std;

int main() {

    // Declaring simple variables
    double x = 2.1;
    double y = {2.2};

    // Printing 
    cout<<"Hello world" << endl;
    cout << y;

    // You can also use auto to declare variables.
    auto b = true;
    auto a = 10;
    auto c = 12.2;

    // Const and Constexpr
    const auto d = 10;
    constexpr auto e = 12*2;

    // Array and pointers
    char v[6];
    char* p;  // pointer to character.
    p = &v[3];
    // prefix '&' means adddress of and prefix '*' means contents of 

    // For loop
    int v1[] = {1,2,3,4,5,6,7,8,9,10};

    for (auto x: v1){ // For each x in v
        cout<<x<<endl;
    }

    // Unary suffix '&' means reference to. A reference is similar to pointer but that we dont need to put * to get its value.
    for (auto& x : v1){
        ++x; // increase every element in v1 by 1.
    }
    for (auto x: v1){
        cout<<x<<endl;
    }


    // Null Pointer
    // When pointer does not point to anything its called null pointer. nullptr
    double* nptr = nullptr;
    // Before working with a pointer you should check if it is not a nullptr.



}