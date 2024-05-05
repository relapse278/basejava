package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract boolean isExisting(Object key);
    protected abstract Object getKey(String uuid);
    protected abstract Resume doGet(Object key);
    protected abstract void doSave(Resume resume, Object key);
    protected abstract void doUpdate(Resume resume, Object key);
    protected abstract void doDelete(Object key);

    @Override
    public Resume get(String uuid) {
        return doGet(getExistingKey(uuid));
    }

    public void save(Resume resume) {
        doSave(resume, getNotExistingKey(resume.getUuid()));
    }

    public void delete(String uuid) {
        doDelete(getExistingKey(uuid));
    }

    public void update(Resume resume) {
        doUpdate(resume, getExistingKey(resume.getUuid()));
    }

    private Object getExistingKey(String uuid) {
        Object key = getKey(uuid);

        if (!isExisting(key)) {
            throw new NotExistStorageException(uuid);
        }

        return key;
    }

    private Object getNotExistingKey(String uuid) {
        Object key = getKey(uuid);

        if (isExisting(key)) {
            throw new ExistStorageException(uuid);
        }

        return key;
    }
}