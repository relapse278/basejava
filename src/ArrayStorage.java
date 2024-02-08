import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final static int MAX_SIZE = 10000;
    Resume[] storage = new Resume[MAX_SIZE];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (size <= MAX_SIZE) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }

        return new Resume("No resume with uuid " + uuid + " found!");
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[--size] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] temp = new Resume[size];
        System.arraycopy(storage, 0, temp, 0, size);
        return temp;
    }

    int size() {
        return size;
    }
}
