package com.nc.edu.ta.aleksandrsurkin.pr4;

/**
 * This class "ArrayTaskList" extends class "AbstractTaskList" and
 * realise methods of abstract class "AbstractTaskList".
 */
public class ArrayTaskList extends AbstractTaskList {

    Task [] tasks = new Task[10];
    private int arrayCount;
    final int ARRAY_BUFFER_INC = 10;
    static private int numberOfTasksListsCounter;
    final String THEME_OF_TITLE = "[EDUCTR][TA]";

    /**
     * This method is set values for new tasks list.
     */
    public ArrayTaskList() {
        this.arrayCount = 0;
        numberOfTasksListsCounter++;
    }

    /**
     * This method is validate that incoming tasks is not "null".
     * @param object - incoming task.
     */
    void validateTask(Task object) {
        if (object == null) {
            System.out.println("You can't use an empty task");
        }
    }

    /**
     * This method is validate that index of incoming task is not negative and
     * index is not more or equal size of tasks list.
     * @param value - index of incoming task.
     */
    void validateIndex(int value) {
        if (value < 0 && value <= size()) {
            System.out.println("You can't use a negative number and number exceeding\n" +
                    "the number of tasks in the current list.");
        }
    }

    /**
     * This method is validate that arguments values are not negative.
     * @param fromValue - value of "from" alert time.
     * @param toValue - value of "to"alert time.
     */
    void validateTimeIncoming(int fromValue, int toValue) {
        if(fromValue < 0) {
            System.out.println("You can't use a negative number");
        }
        if(toValue < 0) {
            System.out.println("You can't use a negative number");
        }
    }


    /**
     * This method is realise actions of method "abstract void add(Task task)" from class "AbstractTaskList",
     * adding theme to tasks title and counts the number of incoming tasks.
     * @param task - input of task.
     */
    @Override
    public void add(Task task) {

        validateTask(task);

        if (arrayCount >= tasks.length) {
            Task[] middleMassive = new Task[tasks.length + ARRAY_BUFFER_INC];
            for (int i = 0; i < tasks.length; i++) {
                middleMassive[i] = tasks[i];
            }
            tasks = middleMassive;
        }

        String taskTitle = task.getTitle();
        task.setTitle(THEME_OF_TITLE + taskTitle);

        tasks[arrayCount++] = task;

        sizeCounter++;
    }

    /**
     * This method is realise actions of method "abstract void remove(Task task)" from class "AbstractTaskList".
     * @param task - input of task.
     */
    @Override
    public void remove(Task task) {

        validateTask(task);

        int localRemove = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == task) {
                localRemove = i;
                break;
            }
        }
        for (int i = localRemove; i < tasks.length - 1; ++i) {
            tasks[i] = tasks[i + 1];
        }
        sizeCounter--;
    }

    /**
     * This method is realise actions of method "abstract Task getTask(int index)" from
     * class "AbstractTaskList".
     * @param index - index of task from tasks list.
     * @return task or null.
     */
    @Override
    public Task getTask(int index) {

        validateIndex(index);

        if (index < 0 && index > size()) {
            return null;
        } else {
            return tasks[index];
        }
    }

    /**
     * This method return a list with tasks which time is into "from" and "to".
     * @param from - start time of the time range.
     * @param to - end time of the time range.
     * @return list with found tasks.
     */
    public Task[] incoming(int from, int to) {

        validateTimeIncoming(from, to);

        Task [] incTasks = new Task [arrayCount];
        Task [] emptyMassive = new Task [0];
        int incTaskCounter = 0;
        for (Task task : tasks) {
            if (task != null) {
                if (task.nextTimeAfter(from) > from && task.nextTimeAfter(from) <= to) {
                    if (task.isActive()) {
                        incTasks[incTaskCounter++] = task;
                    }
                }
            }
        }
        if (incTaskCounter == 0) {
            incTasks = emptyMassive;
            return incTasks;
        } else {
            Task [] countedMassive = new Task [incTaskCounter];
            for (int i = 0; i < countedMassive.length; i++) {
                if (incTasks[i] != null) {
                    countedMassive[i] = incTasks[i];
                }
            }
            return countedMassive;
        }
    }
}
