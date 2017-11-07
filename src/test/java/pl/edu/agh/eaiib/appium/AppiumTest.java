package pl.edu.agh.eaiib.appium;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pl.edu.agh.eaiib.appium.config.AppiumConfig;

public class AppiumTest {
    private static AppiumDriver appiumDriver;

    @BeforeAll
    public static void setUpClass() throws Exception {
        appiumDriver = AppiumConfig.getInstance().getDriver();
    }

    @AfterAll
    public static void tearDownClass() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }
}
