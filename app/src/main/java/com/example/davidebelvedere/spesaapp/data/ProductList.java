package com.example.davidebelvedere.spesaapp.data;

import java.util.List;

/**
 * Created by corsista on 09/04/2018.
 */

public class UserList {
    private String name;
    private List<String> prodotto;

    public UserList(String name) {
        this.name = name;
    }

    public void addElement(String element){
        prodotto.add(element);
    }

    public String getName() {
        return name;
    }
}
