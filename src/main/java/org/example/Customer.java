package org.example;

public class Customer {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public Customer(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @MyAnnotation
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
