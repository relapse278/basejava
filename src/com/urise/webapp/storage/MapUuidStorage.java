package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> map = new LinkedHashMap<>();

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
    public List<Resume> getAllSorted() {
        List<Resume> listCopy = new ArrayList<>(map.values());
        Collections.sort(listCopy); // #1 Comparable
        Collections.sort(listCopy, Comparator.comparing(resume -> (resume.getFullName() + resume.getUuid()))); // #2 Comparator + lambda expression
        return listCopy;
    }

    @Override
    public int size() {
        return map.size();
    }
}