package com.example.davidebelvedere.spesaapp;

import java.util.List;

/**
 * Created by corsista on 09/04/2018.
 */

class UserList {
    private String name;
    private List<String> elementi;

    public UserList(String name) {
        this.name = name;
    }

    public void addElement(String element){
        elementi.add(element);
    }

    public String getName() {
        return name;
    }
}
