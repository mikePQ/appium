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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AppiumExtension.class)
public class NavigationTests {

    @Test
    @DisplayName("User can search posts and links")
    void navigationTest1(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(NAVIGATION_BUTTON_ID)));
        screenshot.createScreenshot();

        WebElement navigationButton = androidDriver.findElementByAccessibilityId(NAVIGATION_BUTTON_ID);
        navigationButton.click();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_NAV_LINK_XPATH)));

        screenshot.createScreenshot();

        WebElement searchNavigationLink = androidDriver.findElementByXPath(SEARCH_NAV_LINK_XPATH);
        searchNavigationLink.click();

        screenshot.createScreenshot();

        WebElement titleLabel = androidDriver.findElementByXPath(TITLE_LABEL_XPATH);
        assertEquals("Szukaj", titleLabel.getText());
    }

    @Test
    @DisplayName("User can navigate to shared links view")
    void navigationTest2(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(NAVIGATION_BUTTON_ID)));
        screenshot.createScreenshot();

        WebElement navigationButton = androidDriver.findElementByAccessibilityId(NAVIGATION_BUTTON_ID);
        navigationButton.click();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SHARED_LINKS_NAV_LINK_XPATH)));

        screenshot.createScreenshot();

        WebElement sharedLinksNavigationLink = androidDriver.findElementByXPath(SHARED_LINKS_NAV_LINK_XPATH);
        sharedLinksNavigationLink.click();

        screenshot.createScreenshot();

        WebElement titleLabel = androidDriver.findElementByXPath(TITLE_LABEL_XPATH);
        assertEquals("Wykopalisko", titleLabel.getText());
    }

    private static final String NAVIGATION_BUTTON_ID = "Nawigacja otwarta";
    private static final String SEARCH_NAV_LINK_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.TextView[1]";
    private static final String SHARED_LINKS_NAV_LINK_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.TextView[1]";
    private static final String TITLE_LABEL_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView";
}
