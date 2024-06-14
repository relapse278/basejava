package com.urise.webapp.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    // Unique identifier
    private String uuid;
    private String fullName;

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

//    public Resume(String fullName) {
//        this(UUID.randomUUID().toString(), fullName);
//    }

//    public Resume(String uuid, String fullName) {
//        this.uuid = uuid;
//        this.fullName = fullName;
//    }

    public String getUuid() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", name='" + fullName + '\'' +
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

        return this.uuid.equals(resume.uuid) && this.fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int compareTo(Resume o) {
        return (this.fullName + this.uuid).compareTo(o.fullName + o.uuid);
    }
}