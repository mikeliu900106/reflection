package org.example;

public class config {
    @MyAnnotation
    public Customer customer(){
        return new Customer("DS","HELLO@email");
    }
    @MyAnnotation
    public Address address(){
        return new Address("致遠一路",112);
    }
    public Massage massage(){
        return  new Massage("創建成功");
    }
}
