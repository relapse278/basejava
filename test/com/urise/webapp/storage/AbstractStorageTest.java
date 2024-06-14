package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";

//    private static final Resume resume1 = new Resume(UUID_1, "dummy1");
//    private static final Resume resume2 = new Resume(UUID_2, "dummy2");
//    private static final Resume resume3 = new Resume(UUID_3, "dummy3");
//    private static final Resume resume4 = new Resume(UUID_4, "dummy4");

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
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
//        Resume resume = new Resume(UUID_2, "dummy");
        storage.update(resume);
        Assert.assertSame(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistingResume() throws Exception {
//        storage.update(new Resume("dummy", "dummy"));
        storage.update(new Resume("dummy"));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(expected, storage.getAll());
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3, RESUME_4};
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() {
        storage.save(RESUME_1);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistingResume() throws Exception {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistingResume() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }
}