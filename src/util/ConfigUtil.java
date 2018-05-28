package util;

import constant.DefaultConstant;
import intf.Concept;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil implements DefaultConstant {

    static Properties prop = new Properties();

    static InputStream inputStream;

    static {
        try {
            inputStream = ConfigUtil.class.getResourceAsStream("/config.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Class getDefaultModel() {
        String classUrl = prop.getProperty("default.model");
        Class<?> cls = DEFAULT_CLASS;//default model
        try {
            cls = ClassLoader.getSystemClassLoader().loadClass(classUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cls;
    }

    public static String getValue(String key) {
        return prop.getProperty(key);
    }

    public static int getValueAsInt(String key) {
        String value = prop.getProperty(key);
        if(value == null || value.isEmpty()) return 0;
        else return Integer.parseInt(value);
    }

}
