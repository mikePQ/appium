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

@ExtendWith(AppiumExtension.class)
public class ContentFilteringTests {

    @Test
    @DisplayName("View posts with highest rate")
    void testContentFiltering1(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(FILTER_SETTINGS_BUTTON_ID)));

        screenshot.createScreenshot();

        WebElement filterSettingsButton = androidDriver.findElementByAccessibilityId(FILTER_SETTINGS_BUTTON_ID);
        filterSettingsButton.click();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(HIGHEST_RATED_POSTS_OPTION_PATH)));

        screenshot.createScreenshot();

        WebElement highestRatedOption = androidDriver.findElementByXPath(HIGHEST_RATED_POSTS_OPTION_PATH);
        highestRatedOption.click();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(HIGHEST_RATED_POSTS_VIEW_LABEL_XPATH)));

        screenshot.createScreenshot();
    }

    @Test
    @DisplayName("View most recent posts")
    void testContentFiltering2(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(FILTER_SETTINGS_BUTTON_ID)));

        screenshot.createScreenshot();

        WebElement filterSettingsButton = androidDriver.findElementByAccessibilityId(FILTER_SETTINGS_BUTTON_ID);
        filterSettingsButton.click();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MOST_RECENT_POSTS_OPTION_XPATH)));

        screenshot.createScreenshot();

        WebElement mostRecentOption = androidDriver.findElementByXPath(MOST_RECENT_POSTS_OPTION_XPATH);
        mostRecentOption.click();

        wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MOST_RECENT_POSTS_VIEW_LABEL_XPATH)));

        screenshot.createScreenshot();
    }

    private static final String FILTER_SETTINGS_BUTTON_ID = "More options";
    private static final String MOST_RECENT_POSTS_OPTION_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[4]/android.widget.RelativeLayout/android.widget.TextView";
    private static final String HIGHEST_RATED_POSTS_OPTION_PATH = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView";
    private static final String MOST_RECENT_POSTS_VIEW_LABEL_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView";
    private static final String HIGHEST_RATED_POSTS_VIEW_LABEL_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView";
}
