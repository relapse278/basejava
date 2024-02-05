import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final int MAX_SIZE = 10000;
    Resume[] storage = new Resume[MAX_SIZE];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    void save(Resume r) {
        if (size <= MAX_SIZE) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        return stream(storage)
                .filter(x -> x.toString().equals(uuid))
                .findFirst()
                .orElse(new Resume("No such resume found!"));
    }

    void delete(String uuid) {
        List<Resume> storageList = Arrays.asList(storage);

        int index = IntStream.range(0, size)
                .filter(i -> Objects.equals(storageList.get(i).toString(), uuid))
                .findFirst()
                .orElse(-1);

        if (index != -1) {
            var resumeStream = Stream.concat(Arrays.stream(storage).limit(index), Arrays.stream(storage).skip(index + 1));
            storage = resumeStream.toArray(Resume[]::new);
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).limit(size).toArray(Resume[]::new);
    }

    int size() {
        return size;
    }
}
