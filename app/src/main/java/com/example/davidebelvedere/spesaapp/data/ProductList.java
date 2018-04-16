package com.example.davidebelvedere.spesaapp.data;

import java.util.List;

/**
 * Created by corsista on 09/04/2018.
 */

public class ProductList {
    private int id;
    private String name;
    private List<String> prodotto;

    public ProductList(String name,int id) {
        this.name = name;
    }

    public void addElement(String element){
        prodotto.add(element);
    }

    public String getName() {
        return name;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }
}
