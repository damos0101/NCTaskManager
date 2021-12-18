package ua.edu.sumdu.j2se.moskvin.tasks;

import java.util.*;
import java.time.LocalDateTime;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws ClassNotFoundException, NullPointerException {
        if (start == null || end == null) {
            throw new NullPointerException();
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Invalid parameters specified");
        }

        LinkedTaskList list = new LinkedTaskList();
        for (Task task : tasks) {
            if (task.nextTimeAfter(start) != null && task.nextTimeAfter(start).compareTo(end) <= 0) {
                list.add(task);
            }
        }
        return list;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws ClassNotFoundException {
        SortedMap<LocalDateTime, Set<Task>> list = new TreeMap<>();
        LocalDateTime next;
        for (Task task : tasks) {
            next = start;
            while (true) {
                next = task.nextTimeAfter(next);
                if (next.isAfter(end)) {
                    break;
                }
                if (list.containsKey(next)) {
                    list.get(next).add(task);
                } else {
                    Set<Task> temp = new HashSet<>();
                    temp.add(task);
                    list.put(next, temp);
                }
            }
        }
        return list;
    }
}
