package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected boolean isExisting(Object key) {
        return map.containsKey((String) key);
    }

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume doGet(Object key) {
        return map.get((String)key);
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        map.put((String) key, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        map.put((String) key, resume);
    }

    @Override
    protected void doDelete(Object key) {
        map.remove((String) key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
