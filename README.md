# Record Builder

![alt text](<record-builder.jpeg>)

## What is a Record?
Record is a special class wich acts as a transporter of immutable data. If you need a class only to hold values ( like a DTO or a coordinator pair ), the Record does this in a compact way, automatically generating methods `equals()`, `hashCode()`, `toString()` and the `getters`.

Was officially introduced as a stable functionality in Java 16. Came through [JEP 395](https://openjdk.org/jeps/395).

**Heritage**
- A Record can implement an interface but it can't extend other classes.

**Modifiers**
- A Record can be declared as final but it can't be extended by another class because are implicitly `final`.

**Attributes**
- A Record has static fields.

**Constructors**
- A Record creates compacts constructors for the purpose of validation but it is not possible to change the field state because they are final.

**Methods**
- A Record can add instance or static methods but is not a good idea to declare access methods with different names (ex: `getName()` instead of `name()`).

**Immutability**
- All the fields from a Record are `private final` by default.

**Reduced Boilerplate**
- You can define the body of the class in a single line: `public Record Person(Integer id, String name) {}`.

## A little bit too verbose don't you think?
In the old days to write/create a simple class with only two fields, you had to write something about 40 or 50 lines of code. It was necessary to declare the fields, constructor, getters, setters and manually `equals()`, `hashCode()` and `toString()`.

```java
public class Person {

    private Integer id;
    private String name;

    public Person() {
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters, Setters, equals, hashCode and toString... (more than 30 lines!)
}
```

## The Lombok Era
[Lombok](https://projectlombok.org/features/) has come to "hide" this all "unnecessary info". With a few annotations, the Lombok lib generates the code at compile time. Visualy, it was a really good solution but is necessary to add an external dependency into the project.

```java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Integer id;
    private String name;
    
}
```

## The Records Era
Java, with Record, came to natively understand that its purpose is just carry data. The code is reduced to in a single line. The intention is clear, the fields are immutable by default and you don't need external libs or even generate code in your editor.

```java
public record PersonRecord(Integer id, String name) { }
```

## How about Record Builder?
In this [record-builder project from Randgalt](https://github.com/Randgalt/record-builder) you will find a new feature showing *how to create a record from with a builder pattern*. Features in this project:
- a companion builder class for Java records
- an interface that adds "with" copy methods
- an annotation that generates a Java record from an Interface template

**POM dependecy** 
- First of all add this in pom file
```xml
<!-- currently using the version 51 -->
<dependencies>
    <dependency>
        <groupId>io.soabase.record-builder</groupId>
        <artifactId>record-builder-core</artifactId>
        <version>${record.builder.version}</version>
        <scope>provided</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <annotationProcessorPaths>
                    <path>
                        <groupId>io.soabase.record-builder</groupId>
                        <artifactId>record-builder-processor</artifactId>
                        <version>${record.builder.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

**Maven command after dependency**
```shell
mvn clean install
```

**RecordBuilder Example** 
```java
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record PersonRecord(Integer id, String name) { }
```

```java
public static void foo() {
    var angelo = PersonRecordBuilder.builder().id(1).name("Angelo").build();
    var angeloWithNewId = PersonRecordBuilder.builder(angelo).id(2).build();

    System.out.println("Name: " + angelo.name());
    System.out.println("Old id: " + angelo.id());
    System.out.println("New id: " + angeloWithNewId.id());
}
```

***Wihter* Example**
```java
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record NewPersonRecord(Integer id, String name) implements NewPersonRecordBuilder.With { }
```

```java
public static void bar() {
        var p1 = new NewPersonRecord(1, "Angelo");
        var p2 = p1.withName("Zero");
        var p3 = p2.withId(2);
        var p4 = p3.with().id(3).name("Jake").build();
        var p5 = p4.with(person -> person.id(4).name("Java"));
        var p6 = p5.with(person -> {
            if (person.name().equals("Java")){
                person.name(person.name() + " is cool!") ;
            }
        });
        var p7 = NewPersonRecordBuilder.from(p6).withId(25);

        // System.out.println("P1 - name: " + p1.name() + " id: " + p1.id());
        
    }
```

- The output will be
```shell
P1 - name: Angelo id: 1
P2 - name: Zero id: 1
P3 - name: Zero id: 2
P4 - name: Jake id: 3
P5 - name: Java id: 4
P6 - name: Java is cool! id: 4
P7 - name: Java is cool! id: 25
```
## Info
**Thanks for**
- [Java Records and Builder pattern For Record - by sunimal malkakulage](https://medium.com/@sskmal/java-records-6736f45a6aa7)
- [Spring Tips: Useful Annotation Processors - by SpringDeveloper](https://www.youtube.com/watch?v=wAW2OpBKpvw)
- [Github Randgalt - record-builder](https://github.com/Randgalt/record-builder)

**You can find all the code from this article here**
- [Github AngeloZero - java-record-builder](https://github.com/angelozero/java-record-builder)