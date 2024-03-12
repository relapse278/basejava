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