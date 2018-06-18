package controller.task;

import intf.DefaultConstant;
import intf.task.Task;
import intf.task.Tasker;
import util.Log;

import java.util.LinkedList;
import java.util.List;

public class TaskManager {

    private static Log log = Log.getInstance(TaskManager.class);

    private List<Tasker> list = new LinkedList<>();

    //private ArrayList<Integer> closed = new ArrayList<>();

    private static TaskManager tm;

    //private static double capity = 0.35;

    private TaskManager() { }

    public static TaskManager getInstance() {
        if(tm == null) tm = new TaskManager();
        return tm;
    }

    //default runtime = 1000/60
    public <T> int addTask(Task<T> task, String name) {
        return addTask(task, DefaultConstant.FRAME_PER_SECOND, name);
    }

    public <T> int addTask(Task<T> task, int runtime, String name) {
        return addTask(task, null, runtime, name);
    }

    public <T> int addTask(Task<T> task, int runtime) {
        return addTask(task, null, runtime, "DEFAULT");
    }

    public <T> int addTask(Task<T> task, T t, int runtime, String name) {
        Timer<T> timer = new Timer<>(name);
        timer.setTask(task);
        timer.setRuntime(runtime);
        timer.setObject(t);
        timer.start();
        list.add(timer);
        int id = list.indexOf(timer);
        timer.setId(id);
        return id;
    }

    public <T> int addTimedTask(Task<T> task, int runtime) {
        return addTimedTask(task, runtime, "DEFAULT");
    }

    public <T> int addTimedTask(Task<T> task, int runtime, String name) {
        return addTimedTask(task, null, runtime, name);
    }

    public <T> int addTimedTask(Task<T> task, T t, int runtime, String name) {
        TimedTimer<T> timer = new TimedTimer<>(name);
        timer.setTask(task);
        timer.setRuntime(runtime);
        timer.setObject(t);
        timer.start();
        list.add(timer);
        int id = list.indexOf(timer);
        timer.setId(id);
        return id;
    }

    public void close(int id) {
        log.log("closing " + id + " task...");
        list.get(id).close();
        list.remove(id);
    }

    public void closeAll() {
        log.log("closing all tasks...");
        for(Tasker timer : list) {
            timer.close();
        }
        list.clear();
    }

    /*
    private void clear() {
        log.log("clearing stopped task...");
        for(Tasker timer : list) {
            if(!timer.isRunning() && !closed.contains(timer.getID())) {
                closed.add(timer.getID());
            }
        }
        double prec = (double)closed.size() / (double)list.size();
        if(prec > capity) {
            for(int i : closed) {
                list.remove(list.get(i));
            }
        }
    }
    */

    public void printAll() {
        StringBuilder stringBuilder = new StringBuilder("\nTaskList: ");
        stringBuilder.append("\n").append("ID").append(" ").append("NAME").append(" ").append("STATUS").append("\n");
        for(Tasker timer : list) {
            stringBuilder.append(timer).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        log.log(stringBuilder.toString());
    }

    private class Timer<T> extends Thread implements Tasker {

        private int id;
        private String name;
        private T t;
        private Task<T> task;
        private boolean flag = true;
        private int runtime = DefaultConstant.FRAME_PER_SECOND;
        private boolean running = true;

        public boolean isRunning() {
            return running;
        }

        public Timer<T> setRunning(boolean running) {
            this.running = running;
            return this;
        }

        public Timer(String name) {
            this.name = name;
        }

        public Timer<T> setId(int id) {
            this.id = id;
            return this;
        }

        public int getID() {
            return id;
        }

        public Timer setRuntime(int runtime) {
            this.runtime = runtime;
            return this;
        }

        public void close() {
            flag = false;
            setRunning(false);
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
            return id + " " + name + " " + (isRunning() ? "RUNNING" : "STOP");
        }

    }

    private class TimedTimer<T> extends Thread implements Tasker  {

        private int id;
        private String name;
        private T t;
        private Task<T> task;
        private int runtime = DefaultConstant.FRAME_PER_SECOND;
        private boolean running = true;

        public boolean isRunning() {
            return running;
        }

        public TimedTimer<T> setRunning(boolean running) {
            this.running = running;
            return this;
        }

        public TimedTimer(String name) {
            this.name = name;
        }

        public TimedTimer<T> setId(int id) {
            this.id = id;
            return this;
        }

        public int getID() {
            return id;
        }

        public void close() {
            setRunning(false);
        }

        public TimedTimer setRuntime(int runtime) {
            this.runtime = runtime;
            return this;
        }

        public TimedTimer setTask(Task<T> task) {
            this.task = task;
            return this;
        }

        public Task<T> getTask() {
            return task;
        }

        public TimedTimer setObject(T t) {
            this.t = t;
            return this;
        }

        public T getObject() {
            return t;
        }

        public void run() {
            try {
                Thread.sleep(runtime);
                task.action(t);
                close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String toString() {
            return id + " " + name + " " + (isRunning() ? "RUNNING" : "STOP");
        }

    }

}
