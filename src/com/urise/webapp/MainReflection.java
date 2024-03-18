package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);
        // TODO : invoke r.toString via reflection
        try {
            System.out.println(r.getClass().getMethods()[5].invoke(r));
            System.out.println(r.toString());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
