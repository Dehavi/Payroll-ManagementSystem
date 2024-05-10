package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String propertyFileName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertyFileName));
            String host = properties.getProperty("db.host");
            String port = properties.getProperty("db.port");
            String dbName = properties.getProperty("db.name");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            
            // Construct connection string
            return "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + username + "&password=" + password;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
