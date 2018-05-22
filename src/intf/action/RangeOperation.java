package intf.action;

@FunctionalInterface
public interface RangeOperation<E, T> {

    void action(E e, T t1, T t2, T t3);

}
