package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    void saveAuxiliary(Resume resume) {
        storage[size] = resume;
    }

    @Override
    void deleteAuxiliary(int index) {
        storage[index] = storage[size - 1];
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