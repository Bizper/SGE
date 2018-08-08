package util;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class Log {

    private Class<?> classes;

    private String path = "./";

    private File file;

    /*
    0 output to console
    1 output to file
    2 output to remote file(in plan)
     */
    private static int mode = 0;

    private static final String ERROR = "ERROR";

    private static final String NORMAL = "NORMAL";

    private static final String WARNING = "WARNING";

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
        file = new File(path + DateUtil.getDate() + ".log");
        return this;
    }

    public int getOutputMode() {
        return mode;
    }

    public String getOutputPath() {
        return path;
    }

    public void debug(Object obj) {
        print("DEBUG", mode, obj);
    }

    public void error(Object obj) {
        print(ERROR, mode, obj);
    }

    public void log(Object obj) {
        print(NORMAL, mode, obj);
    }

    public void warning(Object obj) {
        print(WARNING, mode, obj);
    }

    public void format(String format, Object... obj) {
        fmtPrint(format + "\n", obj);
    }

    private void print(String level, int mode, Object obj) {
        String output = "%-8s %-15s %-40s %s\n";
        switch(mode) {
            case 0:
                switch(level) {
                    case NORMAL:
                        norPrint(output, level, DateUtil.getHour(), classes.getName(), obj);
                        break;
                    default:
                        errPrint(output, level, DateUtil.getHour(), classes.getName(), obj);
                        break;
                }
                break;
            case 1:
                try {
                    if(!file.exists()) file.createNewFile();
                    FileWriter fw = new FileWriter(file, true);
                    output = String.format(output, level, DateUtil.getHour(), classes.getName(), obj);
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

    private void errPrint(String out, Object... args) {
        System.err.printf(out, args);
    }

    private void norPrint(String out, Object... args) {
        System.out.printf(out, args);
    }

    private void fmtPrint(String format, Object... args) {
        System.out.printf(format, args);
    }

}
