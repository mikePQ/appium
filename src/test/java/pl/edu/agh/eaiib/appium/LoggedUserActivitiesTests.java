package pl.edu.agh.eaiib.appium;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.edu.agh.eaiib.appium.junit.AppiumExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AppiumExtension.class)
public class LoggedUserActivitiesTests {

    @Test
    @DisplayName("Logged user can add post")
    void testLoggedUserActivities1(AndroidDriver androidDriver) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ADD_NEW_POST_BUTTON_ID)));

        WebElement addNewPostButton = androidDriver.findElementById(ADD_NEW_POST_BUTTON_ID);
        addNewPostButton.click();

        wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ADD_NEW_POST_VIEW_LABEL_XPATH)));

        WebElement addNewPostViewLabel = androidDriver.findElementByXPath(ADD_NEW_POST_VIEW_LABEL_XPATH);
        assertNotNull(addNewPostViewLabel);
        assertTrue(addNewPostViewLabel.isDisplayed());
    }

    @Test
    @DisplayName("Logged user can add comment")
    void testLoggedUserActivities2(AndroidDriver androidDriver) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ADD_COMMENT_BUTTON_XPATH)));

        WebElement addCommentButton = androidDriver.findElementByXPath(ADD_COMMENT_BUTTON_XPATH);
        addCommentButton.click();

        wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ADD_COMMENT_FIELD_ID)));

        WebElement addCommentField = androidDriver.findElementById(ADD_COMMENT_FIELD_ID);
        assertNotNull(addCommentField);
        assertTrue(addCommentField.isDisplayed());
    }

    @Test
    @DisplayName("Logged user can rate post")
    void testLoggedUserActivities3(AndroidDriver androidDriver) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RATE_POST_BUTTON_XPATH)));

        WebElement ratePostButton = androidDriver.findElementByXPath(RATE_POST_BUTTON_XPATH);
        assertNotNull(ratePostButton);
        assertTrue(ratePostButton.isDisplayed());
        assertTrue(ratePostButton.isEnabled());
    }

    @BeforeEach
    void login(AndroidDriver androidDriver) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(MENU_BUTTON_ID)));
        WebElement menuButton = androidDriver.findElement(By.id(MENU_BUTTON_ID));
        assertNotNull(menuButton);
        menuButton.click();

        wait = new WebDriverWait(androidDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .xpath(LOGIN_BUTTON_XPATH)));

        WebElement loginButton = androidDriver.findElement(By.xpath(LOGIN_BUTTON_XPATH));
        loginButton.click();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(USERNAME_TEXTFIELD_XPATH)));

        WebElement usernameField = androidDriver.findElement(By.xpath(USERNAME_TEXTFIELD_XPATH));
        usernameField.sendKeys("bgtzxc");
        androidDriver.hideKeyboard();

        WebElement passwordField = androidDriver.findElement(By.xpath(PASSWORD_TEXTFIELD_XPATH));
        passwordField.sendKeys("qwerty");
        androidDriver.hideKeyboard();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_LOGIN_FORM_BUTTON_XPATH)));

        WebElement submitLoginFormButton = androidDriver.findElement(By.xpath(SUBMIT_LOGIN_FORM_BUTTON_XPATH));
        submitLoginFormButton.click();
    }

    private static final String MENU_BUTTON_ID = "Nawigacja otwarta";
    private static final String LOGIN_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.CheckedTextView";
    private static final String USERNAME_TEXTFIELD_XPATH = "//android.webkit.WebView[@content-desc=\"Zaloguj\"]/android.view.View[3]/android.view.View[1]/android.widget.EditText[1]";
    private static final String PASSWORD_TEXTFIELD_XPATH = "//android.webkit.WebView[@content-desc=\"Zaloguj\"]/android.view.View[3]/android.view.View[1]/android.widget.EditText[2]";
    private static final String SUBMIT_LOGIN_FORM_BUTTON_XPATH = "//android.widget.Button[@content-desc=\"Zaloguj\"]";

    private static final String ADD_NEW_POST_BUTTON_ID = "io.github.feelfreelinux.wykopmobilny:id/fab";
    private static final String ADD_NEW_POST_VIEW_LABEL_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView";
    private static final String ADD_COMMENT_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[2]";
    private static final String RATE_POST_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[3]";
    private static final String ADD_COMMENT_FIELD_ID = "io.github.feelfreelinux.wykopmobilny:id/body";
}
