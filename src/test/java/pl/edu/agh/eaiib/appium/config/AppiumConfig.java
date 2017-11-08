package pl.edu.agh.eaiib.appium.config;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class AppiumConfig {
    private static AppiumConfig INSTANCE = new AppiumConfig();

    public static AppiumConfig getInstance() {
        return INSTANCE;
    }

    private AppiumConfig() {
    }

    public AppiumDriver getDriver() throws IOException {
        ConfigProperties config = new ConfigProperties();
        String appiumServer = config.getProperty(APPIUM_SERVER_ARGUMENT);
        if (appiumServer == null) {
            throw new IllegalStateException("Appium server not configured");
        }

        return new AppiumDriver(new URL(appiumServer), toDesiredCapabilities(config));
    }

    private static DesiredCapabilities toDesiredCapabilities(ConfigProperties configProperties) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, configProperties.getProperty(PLATFORM_NAME));
        desiredCapabilities.setCapability(DEVICE, configProperties.getProperty(DEVICE));
        desiredCapabilities.setCapability(DEVICE_NAME, configProperties.getProperty(DEVICE_NAME));
        desiredCapabilities.setCapability(APP, configProperties.getProperty(APP));

        return desiredCapabilities;
    }

    private static final class ConfigProperties {
        private final Properties configProperties;
        private final PropertiesProcessor propertiesProcessor = new PropertiesProcessorImpl();

        private ConfigProperties() throws IOException {
            Properties tempProperties = new Properties();
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("configuration.properties").getFile());
            try (FileReader reader = new FileReader(file)) {
                tempProperties.load(reader);
                configProperties = propertiesProcessor.process(tempProperties);
            }
        }

        String getProperty(String key) {
            return configProperties.getProperty(key);
        }
    }

    private static String PLATFORM_NAME = "platformName";
    private static String DEVICE = "device";
    private static String DEVICE_NAME = "deviceName";
    private static String APP = "app";
    private static String APPIUM_SERVER_ARGUMENT = "appium.server";
}
