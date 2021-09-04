package config;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class InitialConfig {
    private static Properties props;

    public InitialConfig() {
        Path resourceDir = Paths.get("src","test", "resources");
        String absPath = resourceDir.toFile().getAbsolutePath();
        File f = new File(absPath + "/application.properties");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        props = new Properties();
        try {
            props.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestRailConfig.initialize();
    }

    public static String getAddItemToCart(){
        return props.getProperty("addItemToCart");
    }
    public static String getAddLastMobToCart(){
        return props.getProperty("addLastMobToCart");
    }
    public static String getAddNewPayMethod(){
        return props.getProperty("addNewPayMethod");
    }
    public static String getAddNewAddress(){
        return props.getProperty("addNewAddress");
    }
    public static String getSeeLastYearOrders(){
        return props.getProperty("seeLastYearOrders");
    }
    public static String getUserNameTR(){
        return props.getProperty("usernameTestRail");
    }
    public static String getDomainTestRail(){
        return props.getProperty("domainTestRail");
    }
    public static String getPasswordTR(){
        return props.getProperty("passwordTestRail");
    }

    public static String getBaseURL(){
        return props.getProperty("baseUrl");
    }

    public static String getEmail() {
        return props.getProperty("email");
    }
    public static String getPassword(){
        return props.getProperty("password");
    }
}
