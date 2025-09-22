package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop =new Properties();

    public static Properties initProperties(){

        try{
            FileInputStream fis=new FileInputStream("./src/test/resources/config.properties");
          prop.load(fis);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("config.properties not found or could not be loaded");
        }
        return prop;
    }

    public  static String getProperty(String key){
//mvn clean test -Dbrowser=chrome -Durl=https://www.automationexercise.com
        // 1. Check system property (from CLI/Jenkins)
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }

        initProperties();
        if(prop == null){
            initProperties();
        }
        return prop.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        prop.setProperty(key, value); // allow overrides from TestNG
    }
}
