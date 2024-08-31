package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AocUtil {

    private static Properties props;

    private AocUtil() {
    }

    public static Properties getProperties() {
        if (props != null) {
            return props;
        }

        try {
            InputStream propsConfig = AocUtil.class.getClassLoader().getResourceAsStream("aoc.properties");
            props = new Properties();
            props.load(propsConfig);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
