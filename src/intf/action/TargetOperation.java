package intf.action;

@FunctionalInterface
public interface TargetOperation<T> {

    void action(T t1, T t2);

}
