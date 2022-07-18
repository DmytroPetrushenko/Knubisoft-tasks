package com.knubisoft.base.reflection.model;

public class TestModel {
    public String returnIt = "Hello";

    public TestModel() {
    }

    public TestModel(String returnIt) {
        this.returnIt = returnIt;
    }

    public String returnString() {
        return this.returnIt;
    }

}

