package org.example;

public class Address {
    private String street;
    private int postCode;

    public Address(String street, int postCode) {
        this.street = street;
        this.postCode = postCode;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }
}
