package com.angelozero.java.record.builder.service;

import com.angelozero.java.record.builder.dto.NewPersonRecord;
import com.angelozero.java.record.builder.dto.NewPersonRecordBuilder;
import com.angelozero.java.record.builder.dto.PersonRecordBuilder;

public class PersonService {

    public static void foo() {
        var angelo = PersonRecordBuilder.builder().id(1).name("Angelo").build();
        var angeloWithNewId = PersonRecordBuilder.builder(angelo).id(2).build();

        System.out.println("Name: " + angelo.name());
        System.out.println("Old id: " + angelo.id());
        System.out.println("New id: " + angeloWithNewId.id());
        System.out.println();
    }

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

        System.out.println("P1 - name: " + p1.name() + " id: " + p1.id());
        System.out.println("P2 - name: " + p2.name() + " id: " + p2.id());
        System.out.println("P3 - name: " + p3.name() + " id: " + p3.id());
        System.out.println("P4 - name: " + p4.name() + " id: " + p4.id());
        System.out.println("P5 - name: " + p5.name() + " id: " + p5.id());
        System.out.println("P6 - name: " + p6.name() + " id: " + p6.id());
        System.out.println("P7 - name: " + p7.name() + " id: " + p7.id());
    }
}
