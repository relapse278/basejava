package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    private final List<Resume> list = new ArrayList<>();

    @Override
    protected boolean isExisting(Object key) {
        return key != null;
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
    protected Resume doGet(Object key) {
        return list.get((Integer) key);
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        list.add(resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        list.set((Integer) key, resume);
    }

    @Override
    protected void doDelete(Object key) {
        list.remove((int) key);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(this.list);
        Collections.sort(list); // #1
        Collections.sort(list, Comparator.comparing(resume -> (resume.getFullName() + resume.getUuid()))); // #2
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}