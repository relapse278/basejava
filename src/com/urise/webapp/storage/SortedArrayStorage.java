package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected Integer getKey(String uuid) {
        Resume resume = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
     protected void insertResume(Resume resume) {
            int index = this.getKey(resume.getUuid());
            index = - index - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
    }

    @Override
    protected void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}