package pl.edu.agh.eaiib.appium;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pl.edu.agh.eaiib.appium.config.AppiumConfig;
import pl.edu.agh.eaiib.appium.config.emulator.AndroidEmulator;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
        androidEmulator.stopEmulator();
    }

    @Test
    @DisplayName("Should display navigation button and navigation panel should be shown on click")
    void navigationTest1() {
        WebElement button = appiumDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Nawigacja otwarta\"]"));
        assertTrue(button.isDisplayed());

        button.click();
        WebElement navigation = appiumDriver.findElement(By.id("io.github.feelfreelinux.wykopmobilny:id/contentView"));
        assertTrue(navigation.isDisplayed());
        assertTrue(navigation.isEnabled());
    }

}
