package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override//
    protected boolean isExisting(Object resume) {
        return map.containsValue((Resume) resume);
    }

    @Override//
    protected Object getKey(String uuid) {
        if (map.containsKey(uuid)) {
            return uuid;
        }

        return null;
    }

    @Override//
    protected Resume doGet(Object key) {
        if (map.containsValue((Resume) key)) {
            return (Resume) key;
        }

        return null;
    }

    @Override//
    protected void doSave(Resume resume, Object key) {
        map.put(((Resume) key).getUuid(), resume);
    }

    @Override//
    protected void doUpdate(Resume resume, Object key) {
        map.put(((Resume) key).getUuid(), resume);
    }

    @Override //
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

    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>((map.values()));
        Collections.sort(list); // #1 Comparable
        Collections.sort(list, Comparator.comparing(resume -> (resume.getFullName() + resume.getUuid()))); // #2 Comparator
        return list;
    }

    @Override
    public int size() {
        return map.size();
    }
}


