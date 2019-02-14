## Streams

- Streams in java are ordered input and output data
- It provides a common input and output model
- Streams are unidirectional
- Abstracts away the underline source of destination of the data (abstracts storange or where data is coming from)
- You can have input stream
- you can have output stream
- stream can be either a binary stream or character stream

---

- Reading streams
    - Binary (The base class for this is InputStream)
    - Text (The base class for this is Reader)

---

There are various methods which are used when reading stream data

- read() --> reads 1 byte at a time
- read(byte[] buff)  or read(char[] buff)  --> read the data which can fit into buff

If you see the below examples it may look like we are creating instances of InputStream, OutputStream, Reader or Writer. These are however abstract classes and cannot be instanciated. There are various subclasses of these which will be inherited based on the type of stream e.g. CharArrayWriter, StringWriter, PipedWriter, InputStreamReader, OutputStreamWriter etc...

```Java
// Reading one byte(binary) at a time.
InputStream input = // create input stream
int intVal

while((intVal = input.read()) >= 0){  // end of stream is indicated by .read() method returning -1
    byte byteVal = (byte)intVal;
    // now you can do something with byte value.
}
```

```Java
// Reading one character at a time.
Reader input = // create input stream
int intVal

while((intVal = input.read()) >= 0){  // end of stream is indicated by .read() method returning -1
    byte byteVal = (chat)intVal;
    // now you can do something with chat value.
}
```

```Java
// Reading multiple byte(binary) at a time.
InputStream input = // create input stream
int length;
byte[] byteBuff = new byte[10];

while((length = input.read()) >= 0){  // end of stream is indicated by .read() method returning -1
    for (int i=0; i<length; i++){  // now we have array of byte, so loop on array to get single byte and do something with it.
        byte byteVal = byteBuff[i];
        // do something with byteval.
    }
}
```

---

*Writing values*

```Java
OutputSteam output = // create stream
byte value = 100;

output.write(value);

byte[] byteBuff = {0, 63, 127};
output.write(byteBuff);
```

```Java
Writer output = //create stream

char a = 'A';  // can write single char
output.write(a);

char[] b = {'a', 'b'};  // can write array of char
output.write(b);

String c = "HEllo"; // can write a string
output.write(c);

```

Usually when you are dealing with streams you will be using try and catch or try with resources and then closing down the stream as shown below.

```Java
try{
    reader = // open reader;
}catch (IOException e){

}finally(){
    if (reader != null){
        try{
            reader.close();
        }catch(){}
    }
}
```

---

**Use try with resources**

The above close gets complicated (its still simplified on top) so use try with resources. Try with resources handles the following exceptions

- Automate cleans ups of 1 or more resources
- Handle try body
- Also handles close method calls and exceptions

```Java
try(
    reader = //open reader
){}
catch{}
```

---

**DEMO**

Check stream --> slides and demos.

The interface AutoCloseable has a method close. The try and catch block will auto call this method close() even if its not called explicitly so the resources are freed up. Also when you use try with resources you may get only 1 exception however there may be other exceptions which are not printed out. So in that case use method `getSuppressed` and you can loop on this and print out all the suppressed exceptions.
---


---

**Chaining Streams**

You can chain streams together.

Java provdes FilterReader, FilterWriter, FilterInputSteam and FilterOutputStream abstract classes which provide easier customization for chaining streams.


---

**Interacting with Files**

java.io classes are depricated use java.nio.file for file handling.

- Use Paths class to locate something in the file system or directory.
- Use Files static method to interacting with files.
    - Create, copy delete etc...
    - Open file streams like newBufferedReader, newBufferedWriter, newInputStream, newOutputStream
    - Read/write file contents using readAllLines, write

(If you see in the pluralSight course this below example was not accepted by Java, so modified it a little and it works)

```Java
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFiles {
    public static void main(String[] args) throws IOException {

        try(
            BufferedReader br = Files.newBufferedReader(Paths.get("file.txt"));
        ){
            String data;
            while ((data = br.readLine()) != null){
                System.out.println(data);
            }

        }

    }
}

```

---

**Working with File Systems**

When we work with file systems we usually work with computers default file system. java also supports specialized file systems like Zip file system.

So we use the File System Type which represents the file system. Class --> FileSystem

Check slide and programs for additional references. (Code demos are avaiable there)


## Strings

Basic concatenation and Stringbuilder are not powerful enough to create complicated strings. so we will be looking at more powerful ways.

- StringJoinr --> Simplifies joining a sequence of values.
- String formatting --> used for desired appearance.

**String Joiner**

```Java
import java.util.StringJoiner;

public class StringJoinerDemo {
    public static void main(String[] args) {

        StringJoiner sj = new StringJoiner(", ");  // specify the delimiter

        sj.add("alpha");
        sj.add("beta");
        sj.add("gama");

        System.out.println(sj.toString());  // output --> alpha, beta, gama

        sj.add("abc").add("def");  // chaining methods --> return type of add is StringJoiner
        System.out.println(sj.toString()); // output --> alpha, beta, gama, abc, def

        StringJoiner sj1 = new StringJoiner("], [", "[", "]");
        sj1.add("alpha");
        sj1.add("beta");
        System.out.println(sj1.toString()); // [alpha], [beta]
    }
}
```

---

**Format Specifiers**

StringJoiner is pretty powerful however we sometimes need more power :)

- Focus is on how things look
- Not concerned with how.
- Use methods like --> String.format or System.out.printf(), Formatter.format

Parts of a format specifier --> `%[argument index][flags][width][precesion]conversoin`

*Common Format Conversions*

- d - decimal
- o - octal
- x = Hex
- f - float
- e - Scientific Notation
- s - String (you can also pass objects not just strings here, if the class has Formattable then it will be used else will go with toString)
- ....

*Format Flags*
- `#`- Include radix
- 0 - 0 pad value
- `-` - left justify
- `,` - Include grouping character
- `space` - Leave space for + or - sign for positive numbers
- `+` - always show signs


You can also write formatted text to a stream not just on output screen. There is a class called the `Formatter` class which provides formatting capabilities. It can help us write formatted content to any class which implements the `Appendable` interface. The writer stream class implements the `Appendable` interface.

e.g.

```Java

BufferedWriter writer = new Files.newBufferedWriter(Paths.get("data.txt"));  // it will close when formatter is closed.
    try(Formatter f = new Formatter(writer)){ // creating a formatter over the writer stream
        f.format("Hello %d", 10);
    }

```

---

**Regular Expressions**

Just basics here --> check other places for detials

```Java
String s1 = "apple, apple and orange please";
String s2 = s1.replaceAll("ple\\b", "ricot");
```


```Java
String s1 = "apple, apple and orange please";
String[] parts = s1.split("//b");

for(String part : parts){
    if (part.matches("\\w+")){
        System.out.println(part);
    }
}
```

Compilation of regular expression can be very processing intensive, so if you are doing this in a loop or over and over again its better to precompile the regular expression and apply.

The `Pattern` class allows us to precompile the regex and then apply. Then the `Matcher` class can apply the compiled regex to an expression/string.

```Java
String s1 = "apple, apple and orange please";
Pattern pattern = Pattern.compile("\\w+");

Matcher matcher = pattern.matcher(value1);

while(matcher.find()){
    System.out.println(matcher.group());
}
```

---

## Controlling App Execution

**Command Line Arguments**

Arguments are passed as String arrays. Each argument is a seperate element. (seperated by space or if space put in quotes). In intellij you can pass arguemnts when you `edit configurations`

 ```Java
 package com.sethirajat.cli;

public class CLIargsDemo {
    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println(arg);
        }

    }
}
```

---

**Persistable Key Value Pairs**

Apps often need persistable key value pairs for app config or initial load or other things like state or preferences. (Hashmap will only store when we are running program and its in memory). Use the `java.util.Properties` class for this.


Properties Class --> Inherits from Hash Table.

- Keys and values are string.
- properties can be written to and from streams
- can optionally include comments
- supports 2 formats --> text and xml
- key and value are separated by : or , or first white space
- `# `or `!` start comment line


```Java
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertiesDemo {

    public static void main(String[] args) {
//        properties_reader();
//        properties_writer_xml();
        properties_reader_xml();
    }

    public static void properties_writer() {
        Properties props = new Properties();

        props.setProperty("key1", "value1");
        System.out.println(props.getProperty("key1"));

        try (Writer writer = Files.newBufferedWriter(Paths.get("abc.properties"))) {
            props.store(writer, "Sample properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void properties_reader(){
        Properties props = new Properties();
        try(Reader reader = Files.newBufferedReader(Paths.get("abc.properties"))){
            props.load(reader);

            System.out.println(props.getProperty("key1"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // xml will work with output streams, so the output stream has to be stored as xml.
    static void properties_writer_xml(){
        Properties props = new Properties();

        try(OutputStream out = Files.newOutputStream(Paths.get("abc.xml"))){

            props.setProperty("key1", "value1");
            props.storeToXML(out, "sample properties");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void properties_reader_xml(){

        Properties props = new Properties();

        try(InputStream in = Files.newInputStream(Paths.get("abc.xml"))){
            props.loadFromXML(in);
            System.out.println(props.getProperty("key1"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

---

**Default Properties**

Properties can also be created with default.

```Java
   static void propertiesWithDefault(){
        Properties defaults = new Properties();
        defaults.setProperty("os", "Windows");
        Properties props = new Properties(defaults); // created with defaults
    }
```

Usually a application will store default properties. For that when you are launching an aplication you can include .properties file in that application.

---

**Default Class Loading**

- Classes must be in .class files
- Must be under package directory


**Specifying class path**

- You can specify the class path. (if you specify path then current directory has to be specified )
- if doing via env variables use `CLASSPATH`
- classpath is set for all programs and projects not just for current project

**Class loading with -jar option**

- Class loading is controlled by jar file
- no other class loading source is used
- provides tight control over class loading

e.g. `java -jar ourapp.jar`


---

## Java Log System

Logs are used for various uses.

- errors
- usage info
- debug
- can be of different detail level


Log System

- its centrally managed
- there is 1 app-wide log manager
- manages log system config
- manages objects that do actual logging
- class `LogManager`. There will be 1 global instance of it.
- `LogManager.getLogManager`
- `Logger` class provides methods to do logging
- use `getLogger` method to get to the logger from the log manager.
- each logger instance is named
- there is also a global logger `GLOBAL_LOGGER_NAME`

Levels
- Each log entry is associated with a level
- Each logger has a capture level --> use `setLevel` method. The logger will ignore any entry below that level
-