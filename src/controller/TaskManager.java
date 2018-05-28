package controller;

import constant.DefaultConstant;
import intf.task.Task;

import java.util.LinkedList;
import java.util.List;

public class TaskManager implements DefaultConstant {

    private List<Timer> list = new LinkedList<>();

    private static TaskManager tm;

    private TaskManager() {}

    public static TaskManager getInstance() {
        if(tm == null) tm = new TaskManager();
        return tm;
    }

    public <T> int addTask(Task<T> task) {
        Timer<T> timer = new Timer<>();
        timer.setTask(task);
        timer.start();
        list.add(timer);
        return list.indexOf(timer);
    }

    public <T> int addTask(Task<T> task, T t) {
        Timer<T> timer = new Timer<>();
        timer.setTask(task);
        timer.setObject(t);
        timer.start();
        list.add(timer);
        return list.indexOf(timer);
    }

    public void close(int id) {
        list.get(id).close();
    }

    public void closeAll() {
        for(Timer timer : list) {
            timer.close();
        }
    }

    private class Timer<T> extends Thread {

        private T t;
        private Task<T> task;
        private boolean flag = true;

        public void close() {
            flag = false;
        }

        public Timer setTask(Task<T> task) {
            this.task = task;
            return this;
        }

        public Task<T> getTask() {
            return task;
        }

        public Timer setObject(T t) {
            this.t = t;
            return this;
        }

        public T getObject() {
            return t;
        }

        public void run() {
            while(flag) {
                try {
                    task.action(t);
                    Thread.sleep(1000/FRAME_PER_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
