package com.nc.edu.ta.aleksandrsurkin.pr4;

/**
 * This is class "Task". This class contains are methods, constructors and
 * fields for the program named "The Task".
 */
public class Task {

    private String title;
    private boolean active;
    private int time;
    private int start;
    private int end;
    private int repeat;

    public Task(String title, int time) {
        validateTitle(title);
        this.title = title;
        validateTime(time);
        this.time = time;
        this.start = time;
        this.end = time;
    }

    public Task(String title, int start, int end, int repeat) {
        validateTitle(title);
        this.title = title;
        validateTime(start);
        this.start = start;
        this.time = start;
        validateTime(end);
        this.end = end;
        validateTime(repeat);
        this.repeat = repeat;
    }

    /**
     * This method validate if the input value on is a positiveness or negative.
     * @param value - value is int or 0.
     */
    void validateTime(int value) {
        if(value < 0) {
            System.out.println("One or more of your argument(s) value(s) are not correct, " +
                    "please, try to enter new positive value(values) again");
        }
    }

    /**
     * This method validate if the input value is a string or null.
     * @param titleValue - value is a string or null.
     */
    void validateTitle(String titleValue) {
        if (titleValue == null) {
            System.out.println("The <title> must be a string type, please, try to enter new" +
                    "correct value.");
        }
    }

    /**
     * This method is return "title" name of a task.
     * @return title - title of a task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method is set a value in the "title" field.
     * @param title name of a title.
     */
    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    /**
     * This method is return a boolean value of a task activity.
     * @return active boolean value of a task activity.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method is set a boolean value of a task activity.
     * @param active boolean value of a task activity.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * This method is set a time of an one-time task.
     * @param time task alert time.
     */
    public void setTime(int time) {
        validateTime(time);
        this.time = time;
        this.start = time;
        this.end = time;
        this.repeat = 0;
    }

    /**
     * This method is set parameters of a recurring task.
     * @param start start time of task notification.
     * @param end time to stop task alert.
     * @param repeat time interval after which it is necessary to repeat the task notification.
     */
    public void setTime(int start, int end, int repeat) {
        validateTime(start);
        this.time = start;
        this.start = start;
        validateTime(end);
        this.end = end;
        validateTime(repeat);
        this.repeat = repeat;
    }

    /**
     * This method is return a task alert time.
     * @return time - returns a one-time task notification.
     */
    public int getTime() {
        return time;
    }

    /**
     * This method is return a task alert time.
     * @return start - returns a start time of a recurring task notification.
     */
    public int getStartTime() {
        return start;
    }

    /**
     * This method is return the end of an alert time.
     * @return end or time.
     */
    public int getEndTime() {
        return end;
    }

    /**
     * This method is return a time the time interval after which the
     * task notification must be repeated (for a recurring task) or
     * 0 (for a one-time task).
     * @return repeat.
     */
    public int getRepeatInterval() {
        return repeat;
    }

    /**
     * This method is return information about the task is repeated or not.
     * @return start, if start > 0.
     */
    public boolean isRepeated() {
        return repeat > 0;
    }

    /**
     * This method is return a description of the task.
     * @return string with title, values of arguments and statuses.
     */
    public String toString() {
        if (!active) {
            return "Task \""  + this.title + "\" is inactive";
        }
        if (repeat == 0) {
            return "Task \""  + this.title + "\" at " + this.time;
        }
        if (repeat > 0) {
            return "Task \"" + this.title + "\" from " + this.start + " to " +
                    this.end + " every " + this.repeat + " seconds";
        }
        return "";
    }

    /**
     * This method is return a time of next alert after specified
     * time(without "time").
     * @return result - time of the next alert or -1.
     */
    public int nextTimeAfter(int time) {
        validateTime(time);
        if (!this.active) {
            return -1;
        }
        if (time < this.start) {
            return this.start;
        }
        int timeNextAlert = this.repeat + this.start;

        while (timeNextAlert <= time && timeNextAlert < this.end) {
            timeNextAlert = this.repeat + timeNextAlert;
        }
        if (timeNextAlert > time && timeNextAlert < end) {
            return timeNextAlert;
        } else {
            return -1;
        }

    }

}
