package org.example;

public class Order {
    private Customer customer;
    private Address address;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @MyAnnotation
    public Order(Customer customer, Address address) {
        this.customer = customer;
        this.address = address;
    }
    @MyAnnotation
    public Order() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
