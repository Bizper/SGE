package intf.task;

@FunctionalInterface
public interface Task<T> {

    void action(T t);

}
