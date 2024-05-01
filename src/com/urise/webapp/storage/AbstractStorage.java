package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract boolean exists(Object key);
    protected abstract Object getKey(String uuid);
    protected abstract Resume getImpl(Object key);
    protected abstract void saveImpl(Resume resume, Object key);
    protected abstract void updateImpl(Resume resume, Object key);
    protected abstract void deleteImpl(Object key);

    @Override
    public Resume get(String uuid) {
        return getImpl(getExistingKey(uuid));
    }

    public void save(Resume resume) {
        saveImpl(resume, getNotExistingKey(resume.getUuid()));
    }

    public void delete(String uuid) {
        deleteImpl(getExistingKey(uuid));
    }

    public void update(Resume resume) {
        updateImpl(resume, getExistingKey(resume.getUuid()));
    }

    private Object getExistingKey(String uuid) {
        Object key = getKey(uuid);

        if (!exists(key)) {
            throw new NotExistStorageException(uuid);
        }

        return key;
    }

    private Object getNotExistingKey(String uuid) {
        Object key = getKey(uuid);

        if (exists(key)) {
            throw new ExistStorageException(uuid);
        }

        return key;
    }
}