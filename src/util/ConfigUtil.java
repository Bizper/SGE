package util;

import constant.DefaultConstant;
import constant.StatusType;
import intf.Concept;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

public class ConfigUtil implements DefaultConstant {

    private static Properties prop;

    private static InputStream inputStream;

    private static String outputFilePath = "./src/config.properties";

    private static FileWriter fw;

    private static Log log = Log.getInstance(ConfigUtil.class);

    public static void init() {
        try {
            prop = new Properties();
            log.log("loading properties...");
            inputStream = ConfigUtil.class.getResourceAsStream("/config.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * 将constant内的常量保存到文件
     */
    public static void saveConstant() {
        log.log("save configs...");
        prop = new Properties();
        Field fields[] = DefaultConstant.class.getFields();
        try {
            fw = new FileWriter(new File(outputFilePath));
            for(Field field : fields) {
                prop.setProperty(field.getName(), field.get(DefaultConstant.class).toString());
            }
            prop.store(fw, "CONFIG FILES");
            log.log("save complete!");
        } catch(Exception e) {
            log.error(e);
        }
    }

    public static String getValue(String key) {
        if(prop == null) init();
        return prop.getProperty(key);
    }

    public static int getValueAsInt(String key) {
        if(prop == null) init();
        String value = prop.getProperty(key);
        if(value == null || value.isEmpty()) return 0;
        else return Integer.parseInt(value);
    }

    public static double getValueAsDouble(String key) {
        if(prop == null) init();
        String value = prop.getProperty(key);
        if(value == null || value.isEmpty()) return 0.0;
        else return Double.parseDouble(value);
    }

}
