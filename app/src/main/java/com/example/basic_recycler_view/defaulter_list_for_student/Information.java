package com.example.basic_recycler_view.defaulter_list_for_student;

public class Information {
    String name;
    String id;

    public Information()
    {

    }
    public Information(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
