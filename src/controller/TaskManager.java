package controller;

import constant.DefaultConstant;
import intf.task.Task;
import util.Log;

import java.util.LinkedList;
import java.util.List;

public class TaskManager {

    private static Log log = Log.getInstance(TaskManager.class);

    private List<Timer> list = new LinkedList<>();

    private static TaskManager tm;

    private TaskManager() {}

    public static TaskManager getInstance() {
        if(tm == null) tm = new TaskManager();
        return tm;
    }

    //default runtime = 1000/60
    public <T> int addTask(Task<T> task, String name) {
        Timer<T> timer = new Timer<>(name);
        timer.setTask(task);
        timer.start();
        list.add(timer);
        return list.indexOf(timer);
    }

    public <T> int addTask(Task<T> task, int runtime, String name) {
        Timer<T> timer = new Timer<>(name);
        timer.setTask(task);
        timer.setRuntime(runtime);
        timer.start();
        list.add(timer);
        return list.indexOf(timer);
    }

    public <T> int addTask(Task<T> task, T t, int runtime, String name) {
        Timer<T> timer = new Timer<>(name);
        timer.setTask(task);
        timer.setRuntime(runtime);
        timer.setObject(t);
        timer.start();
        list.add(timer);
        return list.indexOf(timer);
    }

    public void close(int id) {
        log.log("closing " + id + " task...");
        list.get(id).close();
    }

    public void closeAll() {
        log.log("closing all tasks...");
        for(Timer timer : list) {
            timer.close();
        }
    }

    public void printAll() {
        StringBuilder stringBuilder = new StringBuilder("TaskList: ");
        stringBuilder.append("\n").append("ID").append(" ").append("NAME").append("\n");
        for(Timer timer : list) {
            stringBuilder.append(list.indexOf(timer)).append(" ").append(timer).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        log.log(stringBuilder.toString());
    }

    private class Timer<T> extends Thread {

        private String name;
        private T t;
        private Task<T> task;
        private boolean flag = true;
        private int runtime = DefaultConstant.FRAME_PER_SECOND;

        public Timer(String name) {
            this.name = name;
        }

        public Timer setRuntime(int runtime) {
            this.runtime = runtime;
            return this;
        }

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
                    Thread.sleep(runtime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public String toString() {
            return name;
        }

    }

}
