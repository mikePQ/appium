package pl.edu.agh.eaiib.appium;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.edu.agh.eaiib.appium.junit.AppiumExtension;
import pl.edu.agh.eaiib.appium.utils.Screenshot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(AppiumExtension.class)
public class UserLoginTests {

    @Test
    @DisplayName("Cannot login with incorrect username and password")
    void testLogin1(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(MENU_BUTTON_ID)));

        screenshot.createScreenshot();

        WebElement menuButton = androidDriver.findElement(By.id(MENU_BUTTON_ID));
        assertNotNull(menuButton);
        menuButton.click();

        wait = new WebDriverWait(androidDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .xpath(LOGIN_BUTTON_XPATH)));

        screenshot.createScreenshot();

        WebElement loginButton = androidDriver.findElement(By.xpath(LOGIN_BUTTON_XPATH));
        loginButton.click();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(USERNAME_TEXTFIELD_XPATH)));

        screenshot.createScreenshot();

        WebElement usernameField = androidDriver.findElement(By.xpath(USERNAME_TEXTFIELD_XPATH));
        usernameField.sendKeys("test@test.test");
        androidDriver.hideKeyboard();

        screenshot.createScreenshot();

        WebElement passwordField = androidDriver.findElement(By.xpath(PASSWORD_TEXTFIELD_XPATH));
        passwordField.sendKeys("test");
        androidDriver.hideKeyboard();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_LOGIN_FORM_BUTTON_XPATH)));

        screenshot.createScreenshot();

        WebElement submitLoginFormButton = androidDriver.findElement(By.xpath(SUBMIT_LOGIN_FORM_BUTTON_XPATH));
        submitLoginFormButton.click();

        screenshot.createScreenshot();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(WRONG_LOGIN_DATA_LABEL_ID)));
    }

    @Test
    @DisplayName("User can login with correct username and password")
    void testLogin2(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(MENU_BUTTON_ID)));

        screenshot.createScreenshot();

        WebElement menuButton = androidDriver.findElement(By.id(MENU_BUTTON_ID));
        assertNotNull(menuButton);
        menuButton.click();

        wait = new WebDriverWait(androidDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .xpath(LOGIN_BUTTON_XPATH)));

        screenshot.createScreenshot();

        WebElement loginButton = androidDriver.findElement(By.xpath(LOGIN_BUTTON_XPATH));
        loginButton.click();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(USERNAME_TEXTFIELD_XPATH)));

        screenshot.createScreenshot();

        WebElement usernameField = androidDriver.findElement(By.xpath(USERNAME_TEXTFIELD_XPATH));
        usernameField.sendKeys("bgtzxc");
        androidDriver.hideKeyboard();

        screenshot.createScreenshot();

        WebElement passwordField = androidDriver.findElement(By.xpath(PASSWORD_TEXTFIELD_XPATH));
        passwordField.sendKeys("qwerty");
        androidDriver.hideKeyboard();

        wait = new WebDriverWait(androidDriver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_LOGIN_FORM_BUTTON_XPATH)));

        screenshot.createScreenshot();

        WebElement submitLoginFormButton = androidDriver.findElement(By.xpath(SUBMIT_LOGIN_FORM_BUTTON_XPATH));
        submitLoginFormButton.click();

        assertThrows(NoSuchElementException.class, () -> androidDriver.findElement(By.id(WRONG_LOGIN_DATA_LABEL_ID)));
    }


    private static final String MENU_BUTTON_ID = "Nawigacja otwarta";
    private static final String LOGIN_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.CheckedTextView";
    private static final String USERNAME_TEXTFIELD_XPATH = "//android.webkit.WebView[@content-desc=\"Zaloguj\"]/android.view.View[3]/android.view.View[1]/android.widget.EditText[1]";
    private static final String PASSWORD_TEXTFIELD_XPATH = "//android.webkit.WebView[@content-desc=\"Zaloguj\"]/android.view.View[3]/android.view.View[1]/android.widget.EditText[2]";
    private static final String SUBMIT_LOGIN_FORM_BUTTON_XPATH = "//android.widget.Button[@content-desc=\"Zaloguj\"]";
    private static final String WRONG_LOGIN_DATA_LABEL_ID = "Niepoprawne dane logowania";
}
