package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected boolean exists(Object key) {
//        if (key instanceof Integer) {
            return key != null;
//        } else {
//            throw new ExistStorageException("An argument with a type different from Integer has been passed " +
//                    "to the exists() method of the ListStorage class during the runtime!");
//        }
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected Resume getImpl(Object key) {
//        if (key instanceof Integer) {
            return list.get((Integer) key);
//        } else {
//            throw new ExistStorageException("An argument with a type different from Integer has been passed " +
//                    "to the getImpl() method of the ListStorage class during the runtime!");
//        }
    }

    @Override
    protected void saveImpl(Resume resume, Object key) {
//        if (key instanceof Integer) {
            list.add(resume);
//        } else {
//            throw new ExistStorageException("An argument with a type different from Integer has been passed " +
//                    "to the saveImpl() method of the ListStorage class during the runtime!");
//        }
    }

    @Override
    protected void updateImpl(Resume resume, Object key) {
//        if (key instanceof Integer) {
            list.set((Integer) key, resume);
//        } else {
//            throw new ExistStorageException("An argument with a type different from Integer has been passed " +
//                    "to the updateImpl() method of the ListStorage class during the runtime!");
//        }
    }

    @Override
    protected void deleteImpl(Object key) {
//        if (key instanceof Integer) {
            list.remove((Integer) key);
//        } else {
//            throw new ExistStorageException("An argument with a type different from Integer has been passed " +
//                    "to the deleteImpl() method of the ListStorage class during the runtime!");
//        }
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }
}