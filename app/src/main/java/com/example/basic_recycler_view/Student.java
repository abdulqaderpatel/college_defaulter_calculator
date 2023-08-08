package com.example.basic_recycler_view;

public class Student {
    String id,name;
    double present,absent;

    public Student()
    {

    }

    public Student(String id, String name, double present, double absent) {
        this.id = id;
        this.name = name;
        this.present = present;
        this.absent = absent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPresent() {
        return present;
    }

    public void setPresent(double present) {
        this.present = present;
    }

    public double getAbsent() {
        return absent;
    }

    public void setAbsent(double absent) {
        this.absent = absent;
    }
}
