package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

public interface Storage {
    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    List<Resume> getAllSorted();

    int size();
}