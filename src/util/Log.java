package util;

import java.io.File;
import java.io.FileWriter;

public class Log {

    private Class<?> classes;

    private String path = "./";

    private File file = new File(path + DateUtil.getDate() + ".log");

    /*
    0 output to console
    1 output to file
    2 output to remote file(in plan)
     */
    private int mode = 0;

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
        this.mode = mode;
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
        print("ERROR", mode, obj);
    }

    public void log(Object obj) {
        print("NORMAL", mode, obj);
    }

    public void warning(Object obj) {
        print("WARNING", mode, obj);
    }

    private void print(String level, int mode, Object obj) {
        String output = level + ": [" + classes.getName() + "] " + obj;
        switch(mode) {
            case 0:
                System.out.println(output);
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

}
