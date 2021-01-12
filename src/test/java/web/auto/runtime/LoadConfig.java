package web.auto.runtime;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {

    private static final String propPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "properties" + File.separator + System.getProperty("market") + File.separator + System.getenv("env") + ".properties";

    public static String load(String key) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(propPath);
            prop.load(is);
        } catch (Exception e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return prop.getProperty(key);
    }
}
