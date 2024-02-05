/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    String uuid;

    public Resume(String dummy) {
        uuid = dummy;
    }

    public Resume() {}

    @Override
    public String toString() {
        return uuid;
    }
}
