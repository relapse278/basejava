package com.urise.webapp.model;

import java.util.Objects;

public class WorkItem extends ResumeItem {
    private final String firm;
    private final String period;
    private final String position;
    private final String task;

    public WorkItem(String firm, String period, String position, String task) {
        Objects.requireNonNull(firm, "Firm shouldn't be null!");
        Objects.requireNonNull(period, "Period shouldn't be null!");
        Objects.requireNonNull(position, "Position shouldn't be null!");
        Objects.requireNonNull(task, "Task shouldn't be null!");
        this.firm = firm;
        this.period = period;
        this.position = position;
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkItem workItem = (WorkItem) o;

        if (!firm.equals(workItem.firm)) return false;
        if (!period.equals(workItem.period)) return false;
        if (!position.equals(workItem.position)) return false;
        return task.equals(workItem.task);
    }

    @Override
    public int hashCode() {
        int result = firm.hashCode();
        result = 31 * result + period.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + task.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WorkItem{" +
                "firm='" + firm + '\'' +
                ", period='" + period + '\'' +
                ", position='" + position + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}

