package com.usmile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ProblemOne {
    public static void main(String[] args) {
        List<Task> allTasks = initTasks();

        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String[] inputTaskNames = inputString.split(",");


        List<Task> inputTasks = new ArrayList<>();

        for (String taskName : inputTaskNames) {
            allTasks.forEach((task) -> {
                if (task.getTaskName().equalsIgnoreCase(taskName.trim())) {
                    inputTasks.add(task);
                }
            });
        }

        finishedTasks(inputTasks);
    }

    // check the order to finish the tasks
    private static void finishedTasks(List<Task> tasks) {
        int size = tasks.size();
        while (size > 0) {
            Iterator<Task> iterator = tasks.iterator();
            while(iterator.hasNext()) {
                Task next = iterator.next();
                boolean hasUpperTask = false;
                if (next.getUpperTasks().size() != 0) {
                    List<Task> upperTasks = next.getUpperTasks();
                    for (Task upperTask : upperTasks) {
                        if (tasks.contains(upperTask)) {
                            hasUpperTask = true;
                            break;
                        }
                    }
                }
                if (!hasUpperTask) {
                    System.out.println(next.getTaskName());
                    iterator.remove();
                }
            }
            size = tasks.size();
        }

    }


    // init A~h tasks and the relationship
    private static List<Task> initTasks() {
        List<Task> allTaskTypes = new ArrayList<>();
        Task taskA = new Task("Task A");
        Task taskB = new Task("Task B");
        Task taskC = new Task("Task C");
        Task taskD = new Task("Task D");
        Task taskE = new Task("Task E");
        Task taskF = new Task("Task F");
        Task taskG = new Task("Task G");
        Task taskH = new Task("Task H");

        taskA.addUpperTask(taskC).addUpperTask(taskG);
        taskC.addUpperTask(taskH);
        taskD.addUpperTask(taskA);
        taskE.addUpperTask(taskB).addUpperTask(taskG);
        taskF.addUpperTask(taskD).addUpperTask(taskE);
        taskG.addUpperTask(taskB);
        allTaskTypes.add(taskA);
        allTaskTypes.add(taskB);
        allTaskTypes.add(taskC);
        allTaskTypes.add(taskD);
        allTaskTypes.add(taskE);
        allTaskTypes.add(taskF);
        allTaskTypes.add(taskG);
        allTaskTypes.add(taskH);

        return allTaskTypes;
    }


}

class Task {
    private String taskName;

    private List<Task> upperTasks = new ArrayList<>();

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<Task> getUpperTasks() {
        return upperTasks;
    }

    public void setUpperTasks(List<Task> upperTasks) {
        this.upperTasks = upperTasks;
    }

    public Task addUpperTask(Task upperTask) {
        this.upperTasks.add(upperTask);
        return this;
    }

}