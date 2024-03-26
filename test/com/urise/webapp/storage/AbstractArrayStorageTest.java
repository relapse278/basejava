package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);
    private static final Resume resume4 = new Resume(UUID_4);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);
        Assert.assertSame(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistingResume() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expected = { resume1, resume2, resume3 };
        Assert.assertArrayEquals(expected, storage.getAll());
        Assert.assertEquals(3, storage.getAll().length);

    }

    @Test
    public void save() throws Exception {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(4);
        Resume[] expected = { resume1, resume2, resume3, resume4 };
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() {
        storage.save(resume1);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistingResume() throws Exception {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() throws Exception {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistingResume() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() throws Exception {
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        storage.save(new Resume());
    }
}
