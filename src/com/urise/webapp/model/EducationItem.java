package com.urise.webapp.model;

import java.util.Objects;

public class EducationItem extends ResumeItem {
    private final String institution;
    private final String period;
    private final String course;

    public EducationItem(String institution, String period, String course) {
        Objects.requireNonNull(institution, "Institution shouldn't be null!");
        Objects.requireNonNull(period, "Period shouldn't be null!");
        Objects.requireNonNull(course, "Course shouldn't be null!");
        this.institution = institution;
        this.period = period;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EducationItem that = (EducationItem) o;

        if (!institution.equals(that.institution)) return false;
        if (!period.equals(that.period)) return false;
        return course.equals(that.course);
    }

    @Override
    public int hashCode() {
        int result = institution.hashCode();
        result = 31 * result + period.hashCode();
        result = 31 * result + course.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EducationItem{" +
                "institution='" + institution + '\'' +
                ", period='" + period + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
