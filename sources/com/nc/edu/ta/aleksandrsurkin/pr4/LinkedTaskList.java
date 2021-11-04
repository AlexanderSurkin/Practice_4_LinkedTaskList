package com.nc.edu.ta.aleksandrsurkin.pr4;

/**
 * This class "LinkedTaskList" extends class "AbstractTaskList" and
 * realise methods of abstract class "AbstractTaskList".
 */
public class LinkedTaskList extends AbstractTaskList {

    private Node head;
    final String THEME_OF_TITLE_LINKED_LIST = "[EDUCTR][TA]";
    static private int numberOfLinkedTasksListsCounter;

    /**
     * This method is set values for new linked tasks list.
     */
    public LinkedTaskList() {
        head = null;
        numberOfLinkedTasksListsCounter++;
    }

    /**
     * This class is create fields for create linked tasks list.
     */
    public class Node {
        public Task task;
        public Node next;

        /**
         * This method is set input value into field "task" and set "null" into next node.
         * @param task set value into field "task"
         */
        public Node(Task task) {
            this.task = task;
            next = null;
        }
    }

    /**
     * This method is validate that incoming tasks is not "null".
     * @param object incoming task.
     */
    void validateTask(Task object) {
        if (object == null) {
            System.out.println("You can't use an empty task");
        }
    }

    /**
     * This method is validate that index of incoming task is not negative and
     * index is not more or equal size of tasks list.
     * @param value index of incoming task.
     */
    void validateIndex(int value) {
        if (value < 0 && value <= size()) {
            System.out.println("You can't use a negative number and number exceeding\n" +
                    "the number of tasks in the current list.");
        }
    }

    /**
     * This method is validate that arguments values are not negative.
     * @param fromValue value of "from" alert time.
     * @param toValue value of "to"alert time.
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
     * This method is realise actions of method "abstract void add(Task task)" from class "LinkedTaskList",
     * adding theme to tasks title and counts the number of incoming tasks.
     * @param task input of task.
     */
    @Override
    public void add(Task task) {

        validateTask(task);

        Node newNode = new Node(task);
        Node currentNode = head;

        String taskTitle = task.getTitle();
        task.setTitle(THEME_OF_TITLE_LINKED_LIST + taskTitle);

        if (head == null) {
            head = newNode;

        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        sizeCounter++;
    }

    /**
     * This method is realise actions of method "abstract void remove(Task task)" from class "LinkedTaskList".
     * @param task input of task.
     */
    @Override
    public void remove(Task task) {

        validateTask(task);

        Node currentNode = head;
        Node previousNode = null;

        while (currentNode.next != null) {

            if (currentNode.task == task) {
                if (currentNode == head) {
                    head = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
            }

            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        sizeCounter--;
    }

    /**
     * This method is realise actions of method "abstract Task getTask(int index)" from
     * class "LinkedTaskList".
     * @param index index of task from tasks list.
     * @return task or null.
     */
    @Override
    public Task getTask(int index) {

        validateIndex(index);

        if (index >= 0 && index < size()) {
            Node currentNode = head;
            for (int i = 0; i < size(); i++) {
                if (i == index) {
                    break;
                }
                currentNode = currentNode.next;
            }
            return currentNode.task;
        } else {
            return null;
        }
    }

    /**
     * This method returns a list with tasks which time is into "from" and "to".
     * @param from start time of the time range.
     * @param to end time of the time range.
     * @return list with found tasks.
     */
    @Override
    public Task[] incoming(int from, int to) {

        validateTimeIncoming(from, to);

        Task[] incTasks = new Task [sizeCounter];
        Task[] emptyMassive = new Task [0];
        int incTaskCounter = 0;
        Node currentNode = head;
        for (int i = 0; i < sizeCounter; i++) {
            if (currentNode.task.nextTimeAfter(from) > from && currentNode.task.nextTimeAfter(from) <= to) {
                 if (incTaskCounter == 0) {
                     incTasks[0] = currentNode.task;
                     incTaskCounter = 1;
                     currentNode = currentNode.next;
                 } else {
                     incTasks[incTaskCounter++] = currentNode.task;
                     currentNode = currentNode.next;
                 }
            }
            else {
                currentNode = currentNode.next;
            }
        }
        if (incTaskCounter == 0) {
            incTasks = emptyMassive;
            return incTasks;
        } else {
            Task[] countedMassive = new Task [incTaskCounter];
            for (int i = 0; i < countedMassive.length; i++) {
                if (incTasks[i] != null) {
                    countedMassive[i] = incTasks[i];
                }
            }
            return countedMassive;
        }
    }
}
