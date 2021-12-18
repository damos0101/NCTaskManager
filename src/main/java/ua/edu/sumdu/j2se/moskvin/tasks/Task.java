package ua.edu.sumdu.j2se.moskvin.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable {
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean isRepeated;


    public Task(String title, LocalDateTime time) {
        if (time == null) throw new IllegalArgumentException();
        this.title = title;
        this.time = time;
        isRepeated = false;
        active = false;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null || interval < 0) throw new IllegalArgumentException();
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        isRepeated = true;
        active = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        return (isRepeated ? start : time);
    }

    public void setTime(LocalDateTime time) {
        isRepeated = false;
        this.time = time;
    }

    public LocalDateTime getStartTime() {
        return (isRepeated ? start : time);
    }

    public LocalDateTime getEndTime() {
        return (isRepeated ? end : time);
    }

    public int getRepeatInterval() {
        return (isRepeated ? interval : 0);
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        isRepeated = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (isActive()) {
            if (!isRepeated) {
                return (time.compareTo(current) == 1 ? time : null);
            }
            if (current.compareTo(end) == 1) {
                return null;
            } else if (start.compareTo(current) == 1) {
                return start;
            } else {
                LocalDateTime temp = start;
                while (current.compareTo(temp) >= 0) {
                    temp = temp.plusSeconds(interval);
                }
                if (temp.compareTo(end) > 0) {
                    return null;
                }
                return temp;
            }
        } else {
            return null;
        }
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task cloned = (Task) super.clone();
        cloned.title = this.title;
        cloned.time = this.time;
        cloned.start = this.start;
        cloned.end = this.end;
        cloned.interval = this.interval;
        cloned.active = this.active;
        cloned.isRepeated = this.isRepeated;
        return cloned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time && start == task.start
                && end == task.end && interval == task.interval
                && active == task.active && isRepeated == task.isRepeated
                && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, active, isRepeated);
    }
}
