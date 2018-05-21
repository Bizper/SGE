package util;

import impl.Unit;
import intf.Concept;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

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

    public static <T extends Concept> Class<T> getDefaultModel() {
        String classUrl = prop.getProperty("default.model");
        return (Class<T>) Unit.class;
    }

}
