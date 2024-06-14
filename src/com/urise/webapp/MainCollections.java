package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        final String UUID_1 = "uuid1";
        final String UUID_2 = "uuid2";
        final String UUID_3 = "uuid3";
        final String UUID_4 = "uuid4";

//        final Resume resume1 = new Resume(UUID_1, "Tom");
//        final Resume resume2 = new Resume(UUID_2, "Kurt");
//        final Resume resume3 = new Resume(UUID_3, "Mario");
//        final Resume resume4 = new Resume(UUID_4, "Sarah");

        final Resume resume1 = new Resume(UUID_1);
        final Resume resume2 = new Resume(UUID_2);
        final Resume resume3 = new Resume(UUID_3);
        final Resume resume4 = new Resume(UUID_4);

        collection.add(resume1);
        collection.add(resume2);
        collection.add(resume3);
        collection.add(resume4);

        System.out.println(collection);

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next().getUuid(), UUID_2)) {
                iterator.remove();
                System.out.println("A resume has been removed!");
            }
        }

        System.out.println(collection);

        System.out.println("A new List with 3 identical resumes will be created!");
        List<Resume> list = new ArrayList<>();
        list.add(resume1);
        list.add(resume1);
        list.add(resume1);

        System.out.println(list);

        System.out.println("A new Set with 3 identical resumes from the list will be created!");
        Collection<Resume> set  = new HashSet<>();
        set.addAll(list);

        System.out.println(set);
    }
}
