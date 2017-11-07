package pl.edu.agh.eaiib.appium.config;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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
        desiredCapabilities.setCapability(API_KEY_ARGUMENT, configProperties.getProperty(API_KEY_ARGUMENT));
        desiredCapabilities.setCapability(PROJECT_ARGUMENT, configProperties.getProperty(PROJECT_ARGUMENT));
        desiredCapabilities.setCapability(APP_ID_ARGUMENT, configProperties.getProperty(APP_ID_ARGUMENT));
        desiredCapabilities.setCapability(DEVICE_ARGUMENT, configProperties.getProperty(DEVICE_ARGUMENT));

        return desiredCapabilities;
    }

    private static final class ConfigProperties {
        private final Properties configProperties;

        private ConfigProperties() throws IOException {
            configProperties = new Properties();
            configProperties.load(getClass().getResourceAsStream("configuration.properies"));
        }

        String getProperty(String key) {
            return configProperties.getProperty(key);
        }
    }

    private static String API_KEY_ARGUMENT = "testobject_api_key";
    private static String PROJECT_ARGUMENT = "testobject_project";
    private static String APP_ID_ARGUMENT = "testobject_app_id";
    private static String DEVICE_ARGUMENT = "testobject_device";
    private static String APPIUM_SERVER_ARGUMENT = "appium.server";
}
