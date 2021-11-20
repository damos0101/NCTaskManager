package ua.edu.sumdu.j2se.moskvin.tasks;

public class ArrayTaskList {
    private int size = 0;
    private Task[] tasks = new Task[size];

    public void add(Task task) {
        Task[] newTasks = new Task[size + 1];
        for (int i = 0; i < size; ++i) {
            newTasks[i] = tasks[i];
        }
        tasks = newTasks;
        ++size;
        tasks[size - 1] = task;
    }

    public boolean remove(Task task) {
        for (int i = 0; i < size; ++i) {
            if (task.getTitle() == tasks[i].getTitle()) {
                --size;
                tasks[i] = null;
                for (int j = i; j < size; ++j) {
                    tasks[j] = tasks[j + 1];
                }
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Task getTask(int index) {
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        return tasks[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList newTaskList = new ArrayTaskList();
        for (int i = 0; i < size; ++i) {
            if (tasks[i].getStartTime() > from && tasks[i].getEndTime() < to && tasks[i].isActive()) {
                newTaskList.add(tasks[i]);
            }
        }
        return newTaskList;
    }
}
