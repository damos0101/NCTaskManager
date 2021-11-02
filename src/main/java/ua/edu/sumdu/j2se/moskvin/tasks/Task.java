package ua.edu.sumdu.j2se.moskvin.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean isRepeated;


    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        isRepeated = false;
        active = false;
    }

    public Task(String title, int start, int end, int interval) {
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

    public int getTime() {
        return (isRepeated ? start : time);
    }

    public void setTime(int time) {
        isRepeated = false;
        this.time = time;
    }

    public int getStartTime() {
        return (isRepeated ? start : time);
    }

    public int getEndTime() {
        return (isRepeated ? end : time);
    }

    public int getRepeatInterval() {
        return (isRepeated ? interval : 0);
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setTime(int start, int end, int interval) {
        isRepeated = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public int nextTimeAfter(int current) {
        if (isActive()) {
            if(!isRepeated){
                return (time > current ? time : -1);
            }
            if (current > end) {
                return -1;
            } else if (start > current) {
                return start;
            }else {
                int temp = start;
                while (current >= temp) {
                    temp += interval;
                }
                if (temp >= end) {
                    return -1;
                }
                return temp;
            }
        }
        else {
            return -1;
        }
    }
}
