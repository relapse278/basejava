package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {
    private static final Comparator<Resume> FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName); // #1
//  private static final Comparator<Resume> FULL_NAME_COMPARATOR = (r1, r2) -> r1.getFullName().compareTo(r2.getFullName()); // #2
    private static final Comparator<Resume> UUID_COMPARATOR = Comparator.comparing(Resume::getUuid); // #1
//  private static final Comparator<Resume> UUID_COMPARATOR = (r1, r2) -> r1.getUuid().compareTo(r2.getUuid()); // #2
    protected abstract boolean isExisting(Object key);
    protected abstract Object getKey(String uuid);
//    protected abstract Object getKey(Resume resume);
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

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(Arrays.asList(getAll()));
        list.sort(FULL_NAME_COMPARATOR.thenComparing(UUID_COMPARATOR)); // #1
//        list.sort(UUID_COMPARATOR); // #1
//      list.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid)); // #2, here no need for the static field.
        return list;
    }
}