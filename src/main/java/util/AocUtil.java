package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AocUtil {

    private AocUtil() {
    }

    public static Properties getProperties() {
        try {
            InputStream propsConfig = AocUtil.class.getClassLoader().getResourceAsStream("aoc.properties");
            Properties props = new Properties();
            props.load(propsConfig);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
