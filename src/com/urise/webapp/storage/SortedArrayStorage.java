package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
     void saveAuxiliary(Resume resume) {
        if (size > 0) {
            int index = getIndex(resume.getUuid());
            index = - index - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
        } else {
            storage[0] = resume;
        }
    }

    @Override
    void deleteAuxiliary(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}