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
        //把class從用反射拿出來
        Class<?> clazz = Class.forName("org.example.config");
        System.out.println(clazz);
        //拿出這個class的所有方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods){
            //假如有我預設的註解
            if(m.getDeclaredAnnotation(MyAnnotation.class)!=null){
                //把方法版回的值當key記下來把方法放到value
                this.methods.put(m.getReturnType(),m);
                System.out.println(m.getName());
            }
        }
        //把一個映射的物件放進去
        this.config = clazz.getConstructor().newInstance();
    }

    public Object getServiceInstanceByClass(Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        //假如輸入進來的class跟我原本管理的service map 中class依樣把他的物件拿出來
        if(this.services.containsKey(clazz)){
            return this.services.get(clazz);
        }else{
            //
            if(this.methods.containsKey(clazz)){
                //this.methods.get(clazz); 回傳的是 config中的方法
                Method method = this.methods.get(clazz);
                //Method只知道是個方法不知道是誰執行的要給他指定config執行,回傳一個物件
                Object object = method.invoke(this.config);
                //把它放進service庫裡
                this.services.put(clazz,object);
                return object;
            }
        }
        return null;
    }

    public  Object createInstance(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //拿出輸入近來class的建構式
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors){
            //假如有我預設的註解
            if(constructor.getAnnotation(MyAnnotation.class)!= null){
                //因為建構是可能回傳任何類型所以回傳class
                Class<?>[] paramType = constructor.getParameterTypes();
                //依照參數得長度變成object
                Object[] arguments = new Object[paramType.length];
                for (int i = 0; i < paramType.length ; i++) {
                    //依照參術去get找出有沒有被登記在service或者methods李
                    arguments[i] = getServiceInstanceByClass(paramType[i]);
                }
                return constructor.newInstance(arguments);
            }
            return clazz.getConstructor().newInstance();
        }
        return null;
    }
}
