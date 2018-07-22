package util;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class Log {

    private Class<?> classes;

    private String path = "./";

    private File file = new File(path + DateUtil.getDate() + ".log");

    /*
    0 output to console
    1 output to file
    2 output to remote file(in plan)
     */
    private static int mode = 0;

    private static HashMap<Integer, String> list = new HashMap<>();

    private static String ERROR = "ERROR";

    private static String NORMAL = "NORMAL";

    private static String WARNING = "WARNING";

    static {
        list.put(0, ERROR);
        list.put(1, NORMAL);
        list.put(2, WARNING);
    }

    private Log(Class<?> cls) {
        this.classes = cls;
    }

    public static Log getInstance(Class<?> cls) {
        return new Log(cls);
    }

    public static Log getInstance(Class<?> cls, int mode) {
        return new Log(cls).setOutputMode(mode);
    }

    public static Log getInstance(Class<?> cls, String path) {
        return new Log(cls).setOutputMode(1).setOutputPath(path);
    }

    public Log setOutputMode(int mode) {
        Log.mode = mode;
        return this;
    }

    public Log setOutputPath(String path) {
        this.path = path;
        return this;
    }

    public int getOutputMode() {
        return mode;
    }

    public String getOutputPath() {
        return path;
    }

    public void error(Object obj) {
        print(0, mode, obj);
    }

    public void log(Object obj) {
        print(1, mode, obj);
    }

    public void warning(Object obj) {
        print(2, mode, obj);
    }

    public void format(String format, Object... obj) {
        fmtPrint(format + "\n", obj);
    }

    private void print(int level, int mode, Object obj) {
        String output = "[" + list.get(level) + "][" + DateUtil.getHour() + "][" + classes.getName() + "] " + obj;
        switch(mode) {
            case 0:
                switch(level) {
                    case 1:
                        norPrint(output);
                        break;
                    case 0:
                        errPrint(output);
                        break;
                    case 2:
                        errPrint(output);
                        break;
                }
                break;
            case 1:
                try {
                    if(!file.exists()) file.createNewFile();
                    FileWriter fw = new FileWriter(file, true);
                    fw.write(output + "\n");
                    fw.close();
                } catch(Exception e) {
                    error(e);
                }
                break;
            case 2:
                break;
            default:
                print(level, 0, obj);
                break;
        }
    }

    private void errPrint(String out) {
        System.err.println(out);
    }

    private void norPrint(String out) {
        System.out.println(out);
    }

    private void fmtPrint(String format, Object... args) {
        System.out.printf(format, args);
    }

}
