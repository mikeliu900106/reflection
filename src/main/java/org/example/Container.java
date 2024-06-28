package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Container {
    private Map<Class<?>, Method> methods;
    private Map<Class<?>, Object> services;
    private Object config;
    public void init() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.methods = new HashMap<>();
        this.services = new HashMap<>();
        Class<?> clazz = Class.forName("org.example.config");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods){
            if(m.getDeclaredAnnotation(MyAnnotation.class)!=null){
                this.methods.put(m.getReturnType(),m);
                System.out.println(m.getName());
            }

        }
        this.config = clazz.getConstructor().newInstance();
    }

    public Object getServiceInstanceByClass(Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        if(this.services.containsKey(clazz)){
            return this.services.get(clazz);
        }else{
            if(this.methods.containsKey(clazz)){
                Method method = this.methods.get(clazz);
                Object object = method.invoke(this.config);
                this.services.put(clazz,object);
                return object;
            }
        }

        return null;
    }
    public  Object createInstance(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors){
            if(constructor.getAnnotation(MyAnnotation.class)!= null){
                Class<?>[] paramType = constructor.getParameterTypes();
                Object[] arguments = new Object[paramType.length];
                for (int i = 0; i < paramType.length ; i++) {
                    arguments[i] = getServiceInstanceByClass(paramType[i]);
                }
                return constructor.newInstance(paramType);
            }
            return clazz.getConstructor().newInstance();
        }
        return null;
    }
}
