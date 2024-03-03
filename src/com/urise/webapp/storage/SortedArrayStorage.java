package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
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
        int position = getIndex(resume.getUuid());

        if (size >= STORAGE_LIMIT) {
            System.out.println("Resume with the uuid '" + resume.getUuid() +
                    "' can't be saved, the storage is full!");
        } else if (position >= 0) {
            System.out.println("Resume with the uuid '" + resume.getUuid() +
                    "' already exists!");
        } else {
            if (size > 0) {
                position = - position - 1;
                System.arraycopy(storage, position, storage, position + 1, size - position);
                storage[position] = resume;
            } else {
                storage[0] = resume;
            }
            size++;
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
