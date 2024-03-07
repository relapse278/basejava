package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            //delete(resume.getUuid());
            save(resume);
            return;
        }

        System.out.println("Resume with the uuid '" + resume.getUuid() + "' not found!");
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (size >= STORAGE_LIMIT) {
            System.out.println("Resume with the uuid '" + resume.getUuid() + "' can't be saved, the storage is full!");
        } else if (index >= 0) {
            System.out.println("Resume with the uuid '" + resume.getUuid() + "' already exists!");
        } else {
            if (size > 0) {
                index = - index - 1;
                System.arraycopy(storage, index, storage, index + 1, size - index);
                storage[index] = resume;
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
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[--size] = null;
            return;
        }

        System.out.println("Resume with the uuid '" + uuid + "' not found!");
    }
}
