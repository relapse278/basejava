package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, resume);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            return;
        }

        System.out.println("Resume with the uuid '" + resume.getUuid() + "' not found!");
    }

    @Override
    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Resume with the uuid '" + resume.getUuid() +
                    "' can't be saved, the storage is full!");
        } else if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Resume with the uuid '" + resume.getUuid() +
                    "' already exists!");
        } else {
            storage[size++] = resume;
            Arrays.sort(storage, 0, size);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[--size] = null;
            Arrays.sort(storage, 0, size);
            return;
        }

        System.out.println("Resume with the uuid '" + uuid + "' not found!");
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
}
