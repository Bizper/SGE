package util;

public class Log {

    private Class<?> classes;

    /*
    0 output to console
    1 output to file
    2 output to remote file(in plan)
     */
    private int mode = 0;

    Log(Class<?> cls) {
        this.classes = cls;
    }

    public static Log getInstance(Class<?> cls) {
        return new Log(cls);
    }

    public void setOutputMode(int mode) {
        this.mode = mode;
    }

    public void error(Object obj) {
        print("ERROR", mode, obj);
    }

    public void log(Object obj) {
        print("NORMAL", mode, obj);
    }

    public void warning(Object obj) {
        print("WARNING", mode, obj);
    }

    private void print(String level, int mode, Object obj) {
        switch(mode) {
            case 0:
                System.out.println(level + ": [" + classes.getName() + "] " + obj);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                print(level, 0, obj);
                break;
        }
    }

}
