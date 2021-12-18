package ua.edu.sumdu.j2se.moskvin.tasks;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task> {
    protected int size = 0;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size() {
        return size;
    }

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to){
        AbstractTaskList abstractTaskList = getListType();
        getStream().filter(a -> a.nextTimeAfter(to) != null
                && a.nextTimeAfter(from).isAfter(to)).forEach(abstractTaskList::add);
        return abstractTaskList;
    }

    private AbstractTaskList getListType() {
        return this.getClass().getSimpleName().equals("LinkedTaskList") ?
                TaskListFactory.createTaskList(ListTypes.types.LINKED) :
                TaskListFactory.createTaskList(ListTypes.types.ARRAY);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractTaskList clonedList = getListType();
        for (int i = 0; i < size; ++i) {
            clonedList.add(getTask(i));
        }
        return clonedList;
    }

    public Stream<Task> getStream() {
        Task[] tasks = new Task[this.size()];
        for (int i = 0; i < tasks.length; ++i) {
            tasks[i] = getTask(i);
        }
        return Arrays.stream(tasks);
    }
}
