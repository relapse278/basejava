package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int MAX_SIZE = 10000;
    private final Resume[] storage = new Resume[MAX_SIZE];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < MAX_SIZE) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(r.getUuid())) {
                    System.out.println("Resume with the uuid " + r.getUuid() +
                            " already exists!");
                    return;
                }
            }
            storage[size++] = r;
        } else {
            System.out.println("Resume with the uuid " + r.getUuid() +
                    " can't be saved, the storage is full!");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }

        System.out.println("Resume with the uuid " + uuid+ " not found!");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[--size] = null;
                break;
            }
        }

        System.out.println("Resume with the uuid " + uuid + " not found!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
//        System.arraycopy(storage, 0, resumes, 0, size);
        resumes = Arrays.copyOfRange(storage, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(resume.getUuid())) {
                storage[i] = resume;
                break;
            }
        }

        System.out.println("Resume with the uuid " + resume.getUuid() + " not found!");
    }
}