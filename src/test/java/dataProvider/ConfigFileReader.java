package dataProvider;

import enums.DriverType;
import enums.EnvironmentType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath= "configs//Configuration.properties";


    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

        public String getProperty(String keyWord){

        return properties.getProperty(keyWord);
    }


    public long getImplicitlyWait() {
            String implicitlyWait = properties.getProperty("implicitlyWait");
            if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
            else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
        }

        public String getApplicationUrl() {
            String url = properties.getProperty("url");
            if(url != null) return url;
            else throw new RuntimeException("url not specified in the Configuration.properties file.");
        }

    public String getShadowDomUrl() {
        String shadowDomUrl = properties.getProperty("shadowDomUrl");
        if(shadowDomUrl != null) return shadowDomUrl;
        else throw new RuntimeException("shadowDomUrl not specified in the Configuration.properties file.");
    }

    public String getShadowDomTwoUrl() {
        String shadowDomTwoUrl = properties.getProperty("shadowDomTwoUrl");
        if(shadowDomTwoUrl != null) return shadowDomTwoUrl;
        else throw new RuntimeException("shadowDomTwoUrl not specified in the Configuration.properties file.");
    }

    public String getGoogleUrl() {
        String GoogleUrl = properties.getProperty("GoogleUrl");
        if(GoogleUrl != null) return GoogleUrl;
        else throw new RuntimeException("GoogleUrl not specified in the Configuration.properties file.");
    }


    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
        else if(browserName.equals("firefox")) return DriverType.FIREFOX;
        else if(browserName.equals("edge")) return DriverType.EDGE;
        else if(browserName.equals("remote_chrome")) return DriverType.REMOTECHROME;
        else if(browserName.equals("remote_firefox")) return DriverType.REMOTEFIREFOX;
        else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }

    public EnvironmentType getEnvironment() {
        String environmentName = properties.getProperty("environment");
        if(environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
        else if(environmentName.equals("remote")) return EnvironmentType.REMOTE;
        else if(environmentName.equals("browserstack")) return EnvironmentType.BROWSERSTACK;
        else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
    }

    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if(windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }

    public String getRemoteUrl() {
        String remoteUrl = properties.getProperty("remoteUrl");
        if (remoteUrl != null) return remoteUrl;
        else throw new RuntimeException("remoteUrl not specified in the Configuration.properties file.");
    }


}

