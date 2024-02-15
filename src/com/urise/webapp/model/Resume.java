package com.urise.webapp.model;

/**
 * Initial resume class
 */
public class Resume {

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // Unique identifier
    private String uuid;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}