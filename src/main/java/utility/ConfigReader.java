package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties prop =new Properties();

  static{

        try{
            FileInputStream fis=new FileInputStream("./src/test/resources/config.properties");
          prop.load(fis);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("config.properties not found or could not be loaded");
        }
    }
    // Prevent instantiation
    private ConfigReader() {}

    public  static String getProperty(String key){
        return prop.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        prop.setProperty(key, value); // allow overrides from TestNG
    }
    // New method with default value
    public static String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }
    // Unified config lookup (System → XML → Config → Default)
    public static String getFinalProperty(String key, String defaultValue) {
        // 1. Check JVM/System property (like -Dbrowser=edge from Jenkins)
        String sysVal = System.getProperty(key);
        if (sysVal != null && !sysVal.trim().isEmpty()) {
            return sysVal;
        }

        // 2. Check config.properties
        String fileVal = prop.getProperty(key);
        if (fileVal != null && !fileVal.trim().isEmpty()) {
            return fileVal;
        }

        // 3. Fallback
        return defaultValue;
    }
}
