package pl.edu.agh.eaiib.appium.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

public class AppiumConfig {
    private static AppiumConfig INSTANCE;

    private final ConfigProperties configProperties;
    private final File screenshotsDir;

    public static synchronized AppiumConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppiumConfig();
        }

        return INSTANCE;
    }

    private AppiumConfig() {
        configProperties = createConfigProperties();
        screenshotsDir = new File(configProperties.getProperty(SCREENSHOTS_DIR), LocalDateTime.now().toString());
    }

    public AndroidDriver getDriver() throws IOException {
        String appiumServer = configProperties.getProperty(APPIUM_SERVER_ARGUMENT);
        if (appiumServer == null) {
            throw new IllegalStateException("Appium server not configured");
        }

        return new AndroidDriver(new URL(appiumServer), toDesiredCapabilities(configProperties));
    }

    private static DesiredCapabilities toDesiredCapabilities(ConfigProperties configProperties) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, configProperties.getProperty(PLATFORM_NAME));
        desiredCapabilities.setCapability(DEVICE, configProperties.getProperty(DEVICE));
        desiredCapabilities.setCapability(DEVICE_NAME, configProperties.getProperty(DEVICE_NAME));
        desiredCapabilities.setCapability(APP, configProperties.getProperty(APP));
        desiredCapabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");

        return desiredCapabilities;
    }

    public File getScreenshotsDir() {
        return screenshotsDir;
    }

    private static ConfigProperties createConfigProperties() {
        try {
            return new ConfigProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
    private static String SCREENSHOTS_DIR = "screenshots.dir";
}
