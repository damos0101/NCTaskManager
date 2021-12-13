package ua.edu.sumdu.j2se.moskvin.tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedTaskList extends AbstractTaskList {
    private TaskNode head;

    public class TaskNode {
        private Task task;
        private TaskNode next;

        public TaskNode(Task task) {
            this.task = task;
            next = null;
        }
    }

    public void add(Task task) {
        if (task == null) throw new IllegalArgumentException();
        TaskNode taskNode = new TaskNode(task);
        TaskNode current = head;
        if (head == null) {
            head = taskNode;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = taskNode;
        }
        ++size;
    }

    public boolean remove(Task task) {
        TaskNode current = head;
        TaskNode prev = null;
        for (int i = 0; i < size; ++i) {
            if (current.task.equals(task)) {
                if (current == head) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                --size;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }


    public Task getTask(int index) {
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        TaskNode current = head;
        for (int i = 0; i < size; ++i) {
            if (i == index) {
                return current.task;
            }
            current = current.next;
        }
        return current.task;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int current;
            private int removed;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public Task next() {
                if (hasNext()) {
                    removed = current;
                    return getTask(current++);
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                if (current < 1) {
                    throw new IllegalStateException();
                }
                LinkedTaskList.this.remove(getTask(removed));
                --current;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList tasks = (LinkedTaskList) o;
        for (int i = 0; i < size; ++i) {
            if (!tasks.getTask(i).equals(getTask(i))) return false;
        }
        return size == tasks.size;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size; ++i) {
            if ((getTask(i) == null)) {
                hash = 29;
            } else {
                hash = 29 + getTask(i).hashCode();
            }
        }
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
