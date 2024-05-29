package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    abstract void removeResume(int index);
    abstract void insertResume(Resume resume);
    protected abstract Integer getKey(String uuid);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExisting(Object index) {
        if (index instanceof Integer) {
            return (Integer) index >= 0;
        } else {
            throw new ExistStorageException("An argument with a type different from Integer has been passed " +
                                            "to the exists() method during the runtime!");
        }
    }

    @Override
    protected Resume doGet(Object index) {
        if (index instanceof Integer) {
            return storage[(Integer) index];
        } else {
            throw new IllegalArgumentException("An argument with a type different from Integer has been passed " +
                                               "to the getImpl() method during the runtime!");
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getKey(uuid);

        if (index >= 0) {
            return storage[index];
        }

        throw new NotExistStorageException(uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(Arrays.asList(storage));
        Collections.sort(list); // #1
        Collections.sort(list, Comparator.comparing(resume -> (resume.getFullName() + resume.getUuid()))); // #2
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        if (index instanceof Integer) {
            if (size >= STORAGE_LIMIT) {
                throw new StorageException("Resume with the uuid '" + resume.getUuid() +
                        "' can't be saved, the storage is full!", resume.getUuid());
            } else {
                insertResume(resume);
            }

            size++;
        } else {
            throw new IllegalArgumentException("An argument with a type different from Integer has been passed " +
                    "to the saveImpl() method during the runtime!");
        }
    }

    @Override
    protected void doDelete(Object index) {
        if (index instanceof Integer) {
            removeResume((Integer) index);
            storage[--size] = null;
        } else {
            throw new IllegalArgumentException("The argument index with a type different from Integer " +
                    "has been passed to the deleteImpl() method during the runtime!");
        }
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        if (index instanceof Integer) {
            storage[(Integer) index] = resume;
        } else {
            throw new IllegalArgumentException("The argument index with a type different from Integer " +
                                               "has been passed to the updateImpl() method during the runtime!");
        }
    }
}