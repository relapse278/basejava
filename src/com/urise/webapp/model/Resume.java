package com.urise.webapp.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    // Unique identifier
    private final String uuid;
    private String name;

    public String getUuid() {
        return this.uuid;
    }

//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resume resume = (Resume) o;

        return this.uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return this.uuid.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Resume o) {
        return this.uuid.compareTo(o.uuid);
    }
}