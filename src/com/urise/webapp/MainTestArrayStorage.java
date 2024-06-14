package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();
//    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
//        Resume r1 = new Resume("uuid1", "Mario");
        Resume r1 = new Resume("uuid1");
//        r1.setUuid("uuid1");
        r1.setFullName("John");
//        Resume r2 = new Resume("uuid2", "Marlon");
        Resume r2 = new Resume("uuid2");
//        r2.setUuid("uuid2");
        r2.setFullName("Patrick");
//        Resume r3 = new Resume("uuid3", "Mary");
        Resume r3 = new Resume("uuid3");
//        r3.setUuid("uuid3");
        r3.setFullName("Sarah");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
//        Resume rUpdate = new Resume("uuid2", "Tom");
        Resume rUpdate = new Resume("uuid2");
//        rUpdate.setUuid("uuid2");
        rUpdate.setFullName("Tobias");
        ARRAY_STORAGE.update(rUpdate);
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
