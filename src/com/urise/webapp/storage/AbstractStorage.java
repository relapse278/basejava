package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);

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

    public void save(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (isExisting(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, searchKey);
        }
        size++;
    }

    protected abstract void doSave(Resume resume, Object searchKey);

    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid;

        if (isExisting(searchKey)) {
            doDelete(searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void doDelete(Object searchKey);

    public void update(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());

        if (isExisting(searchKey)) {
            doUpdate(resume, searchKey);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExisting(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    abstract void removeResume(int index);

    abstract void insertResume(Resume resume);

    abstract int getIndex(String uuid);
}