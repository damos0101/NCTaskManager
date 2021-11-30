package ua.edu.sumdu.j2se.moskvin.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        if(type == ListTypes.types.ARRAY){
            ArrayTaskList arrayTaskList = new ArrayTaskList();
            return arrayTaskList;
        }
        else {
            LinkedTaskList linkedTaskList = new LinkedTaskList();
            return linkedTaskList;
        }
    }
}
