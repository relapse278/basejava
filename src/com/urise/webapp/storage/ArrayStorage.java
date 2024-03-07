package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            return;
        }

        System.out.println("Resume with the uuid '" + resume.getUuid() + "' not found!");
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Resume with the uuid '" + r.getUuid() +
                    "' can't be saved, the storage is full!");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume with the uuid '" + r.getUuid() +
                    "' already exists!");
        } else {
            storage[size++] = r;
        }
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

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }
}