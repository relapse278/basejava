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
        if (size >= MAX_SIZE) {
            System.out.println("Resume with the uuid '" + r.getUuid() +
                    "' can't be saved, the storage is full!");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume with the uuid '" + r.getUuid() +
                    "' already exists!");
        } else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }

        System.out.println("Resume with the uuid '" + uuid + "' not found!");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[--size] = null;
            return;
        }

        System.out.println("Resume with the uuid '" + uuid + "' not found!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes;
        resumes = Arrays.copyOfRange(storage, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            return;
        }

        System.out.println("Resume with the uuid '" + resume.getUuid() + "' not found!");
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}