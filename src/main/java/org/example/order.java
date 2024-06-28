package org.example;

public class order {
    private Customer customer;
    private Address address;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public order(Customer customer, Address address) {
        this.customer = customer;
        this.address = address;
    }

    public order() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
