package pl.edu.agh.eaiib.appium;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pl.edu.agh.eaiib.appium.junit.AppiumExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AppiumExtension.class)
public class AppiumTest {

    @Test
    @DisplayName("Should display navigation button and navigation panel should be shown on click")
    void navigationTest1(AndroidDriver androidDriver) {
        WebElement button = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Nawigacja otwarta\"]"));
        assertTrue(button.isDisplayed());

        button.click();
        WebElement navigation = androidDriver.findElement(By.id("io.github.feelfreelinux.wykopmobilny:id/contentView"));
        assertTrue(navigation.isDisplayed());
        assertTrue(navigation.isEnabled());

        // uncomment for testing screenshots
        //assertTrue(false);
    }
}
