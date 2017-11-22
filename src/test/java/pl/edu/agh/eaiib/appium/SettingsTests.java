package pl.edu.agh.eaiib.appium;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.edu.agh.eaiib.appium.junit.AppiumExtension;
import pl.edu.agh.eaiib.appium.utils.Screenshot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AppiumExtension.class)
public class SettingsTests {

    @Test
    @DisplayName("User can select/deselect nightly mode")
    void testSettings1(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(NAVIGATION_BUTTON_ID)));

        screenshot.createScreenshot();

        WebElement navigationButton = androidDriver.findElementByAccessibilityId(NAVIGATION_BUTTON_ID);
        navigationButton.click();

        wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SETTINGS_OPTION_XPATH)));

        screenshot.createScreenshot();

        WebElement settingsOption = androidDriver.findElementByXPath(SETTINGS_OPTION_XPATH);
        settingsOption.click();

        wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NIGHTLY_MODE_CHECKBOX_XPATH)));

        screenshot.createScreenshot();

        WebElement nightlyModeCheckbox = androidDriver.findElementByXPath(NIGHTLY_MODE_CHECKBOX_XPATH);
        nightlyModeCheckbox.click();

        wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TITLE_LABEL_XPATH)));

        screenshot.createScreenshot();

        WebElement titleLabel = androidDriver.findElementByXPath(TITLE_LABEL_XPATH);
        assertNotNull(titleLabel);
        assertTrue(titleLabel.isEnabled());

        wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NIGHTLY_MODE_CHECKBOX_XPATH)));

        screenshot.createScreenshot();

        nightlyModeCheckbox = androidDriver.findElementByXPath(NIGHTLY_MODE_CHECKBOX_XPATH);
        nightlyModeCheckbox.click();

        wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TITLE_LABEL_XPATH)));

        screenshot.createScreenshot();

        titleLabel = androidDriver.findElementByXPath(TITLE_LABEL_XPATH);
        assertNotNull(titleLabel);
        assertTrue(titleLabel.isEnabled());
    }

    private static final String NAVIGATION_BUTTON_ID = "Nawigacja otwarta";
    private static final String SETTINGS_OPTION_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[7]";
    private static final String NIGHTLY_MODE_CHECKBOX_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.CheckBox";
    private static final String TITLE_LABEL_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout";
}
