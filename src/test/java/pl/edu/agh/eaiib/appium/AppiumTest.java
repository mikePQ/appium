package pl.edu.agh.eaiib.appium;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.eaiib.appium.config.AppiumConfig;
import pl.edu.agh.eaiib.appium.config.emulator.AndroidEmulator;

public class AppiumTest {
    private static AppiumDriver appiumDriver;
    private static AndroidEmulator androidEmulator;

    @BeforeAll
    static void setUpClass() throws Exception {
        androidEmulator = AndroidEmulator.create();
        androidEmulator.startEmulator();
        appiumDriver = AppiumConfig.getInstance().getDriver();
    }

    @AfterAll
    static void tearDownClass() {
        appiumDriver.quit();
        androidEmulator.stopEmulator();
    }

    @Test
    void firstTest() {
        System.out.println("aaaaa");
    }

}
