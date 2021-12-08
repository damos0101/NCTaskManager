package ua.edu.sumdu.j2se.moskvin.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList{
    private Task[] tasks = new Task[size];

    public void add(Task task) {
        if(task == null) throw new IllegalArgumentException();
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
            if (tasks[i].equals(task)) {
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

    public Task getTask(int index) {
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        return tasks[index];
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int current;
            private int removed;

            @Override
            public boolean hasNext() {
                return current < size && tasks[current] != null;
            }

            @Override
            public Task next() {
                if (hasNext()) {
                    removed = current;
                    return tasks[current++];
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                if (current == 0) {
                    throw new IllegalStateException();
                }
                ArrayTaskList.this.remove(tasks[removed]);
                --current;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return Arrays.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tasks);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
