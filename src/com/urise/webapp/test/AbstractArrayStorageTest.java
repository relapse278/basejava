package com.urise.webapp.test;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    protected static final int STORAGE_LIMIT = 3;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception { // is called before each test newly
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void update() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(3, storage.getAll().length);
    }

//    @Test(expected = ExistStorageException.class)
    @Test(expected = StorageException.class)
    public void save() throws Exception {
        storage.save(new Resume("dummy"));
        Assert.assertEquals(4, storage.getAll().length);
        storage.save(new Resume("dummy"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.getAll().length);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void get() throws Exception {
        Assert.assertEquals(storage.get(UUID_1), new Resume(UUID_1));
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void setExisted() throws Exception {
        storage.save(new Resume(UUID_1));
    }
}