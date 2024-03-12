package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            return storage[index];
        }

        System.out.println("Resume with the uuid '" + uuid + "' not found!");
        return null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    public abstract void update(Resume resume);

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Resume with the uuid '" + resume.getUuid() +
                    "' can't be saved, the storage is full!");
            return;
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume with the uuid '" + resume.getUuid() +
                    "' already exists!");
            return;
        } else {
            saveAuxiliary(resume);
        }
        size++;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            deleteAuxiliary(index);//System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[--size] = null;
            return;
        }

        System.out.println("Resume with the uuid '" + uuid + "' not found!");
    }

    abstract void deleteAuxiliary(int index);

    abstract void saveAuxiliary(Resume resume);

    //public abstract void delete(String uuid);
}
