package intf.task;

public interface Tasker {

    String getTaskName();

    void close();

    int getID();

    boolean isRunning();

}
