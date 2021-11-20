package ua.edu.sumdu.j2se.moskvin.tasks;

public class LinkedTaskList {
    private int size = 0;
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
            if (current.task.getTitle() == task.getTitle()) {
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

    public int size() {
        return size;
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

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList newTaskList = new LinkedTaskList();
        TaskNode current = head;
        for (int i = 0; i < size; ++i) {
            if (current.task.getStartTime() > from && current.task.getEndTime() < to && current.task.isActive()) {
                newTaskList.add(current.task);
            }
            current = current.next;
        }
        return newTaskList;
    }

}
