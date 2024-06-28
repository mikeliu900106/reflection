package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Integer> sharedList = new ArrayList<>();
    public String name;
    public int number;
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Container container = new Container();
        container.init();

        Class<?> clazz = Class.forName("org.example.Order");
        Object object = container.createInstance(clazz);
        Field field = clazz.getDeclaredField("customer");
       field.setAccessible(true);
         Object filedValue = field.get(object);
        System.out.println(filedValue);
//        Address address = new Address("致遠二路",113);
//        Customer customer = new Customer("mike","sb@email");
//        Class<?> c = Class.forName("org.example.TagInfoEntity");
//        Field[] fileds = c.getDeclaredFields();
//        System.out.println();
//        c.getDeclaredAnnotation(    )
//        for (Field f: fileds){
//            System.out.println(f);
//        }
    }

    static class AddElementTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                sharedList.add(i); // 并发修改操作
                System.out.println(Thread.currentThread().getName() + " added: " + i);
            }
        }
    }
}