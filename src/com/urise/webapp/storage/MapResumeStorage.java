package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected boolean isExisting(Object resume) {
        return resume != null;
    }

    @Override
    protected Object getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected Resume doGet(Object key) {
        return (Resume) key;
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        map.put(((Resume) key).getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        map.put(((Resume) key).getUuid(), resume);
    }

    @Override
    protected void doDelete(Object resume) {
        if (isExisting(resume)) {
            map.remove(((Resume) resume).getUuid());
        }
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


