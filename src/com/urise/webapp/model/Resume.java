package com.urise.webapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private String fullName;
    private Map<ResumeItems, > info = new ;

//    public Resume() {
//        this(UUID.randomUUID().toString());
////        this.fullName = "dummy";
//    }

//    public Resume(String uuid) {
//        this.uuid = uuid;
//    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName, Info info) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.info = info;
    }

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
//    public int compareTo(Resume o) {
//        return (this.fullName + this.uuid).compareTo(o.fullName + o.uuid);
//    }
    public int compareTo(Resume o) {
        return this.uuid.compareTo(o.uuid);
    }

    class Info {
        private Map<ResumeItems, Object> info = new HashMap<>();

        public void addData(ResumeItems resumeItem, Object data) {
            switch (resumeItem) {
                case CONTACTS: {
                    info.put(resumeItem, (Contact) data);
                }

                case ACHIEVEMENTS, QUALIFICATIONS, PERSONAL_QUALITIES: {
                    info.put(resumeItem, (List<String>) data);
                }

                case POSITION: {
                    info.put(resumeItem, (String) data);
                }

                case WORK: {
                    info.put(resumeItem, (List<WorkItem>)data);
                }

                case EDUCATION: {
                    info.put(resumeItem, (List<EducationItem>)data);
                }
            }
        }
    }
}