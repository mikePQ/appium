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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AppiumExtension.class)
public class UnauthenticatedUserTests {

    @Test
    @DisplayName("Button for adding new posts should not be available for unauthenticated users")
    void testUnauthenticatedUser1(AndroidDriver androidDriver, Screenshot screenshot) {
        screenshot.createScreenshot();
        assertThrows(NoSuchElementException.class, () -> androidDriver.findElement(By.id(ADD_NEW_POST_BUTTON_ID)));
    }

    @Test
    @DisplayName("Unauthenticated user cannot add comment")
    void testUnauthenticatedUser2(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(COMMENT_POST_BUTTON_XPATH)));

        screenshot.createScreenshot();

        WebElement commentPostButton = androidDriver.findElement(By.xpath(COMMENT_POST_BUTTON_XPATH));
        assertNotNull(commentPostButton);
        commentPostButton.click();

        wait = new WebDriverWait(androidDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .id(SINGLE_POST_VIEW_ID)));

        screenshot.createScreenshot();

        WebElement singlePostView = androidDriver.findElement(By.id(SINGLE_POST_VIEW_ID));
        assertNotNull(singlePostView);

        assertThrows(NoSuchElementException.class, () -> androidDriver.findElement(By.id(ADD_COMMENT_TOOLBAR_ID)));
    }

    @Test
    @DisplayName("Unauthenticated user cannot vote for post")
    void testUnauthenticatedUser3(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(VOTE_FOR_POST_BUTTON_ID)));

        screenshot.createScreenshot();

        WebElement voteForPostButton = androidDriver.findElement(By.id(VOTE_FOR_POST_BUTTON_ID));
        assertNotNull(voteForPostButton);
        voteForPostButton.click();

        screenshot.createScreenshot();

        WebElement errorMessageDialog = androidDriver.findElement(By.id(ERROR_MESSAGE_DIALOG_ID));
        assertNotNull(errorMessageDialog);
        assertTrue(errorMessageDialog.isDisplayed());
        assertTrue(errorMessageDialog.isEnabled());
    }

    @Test
    @DisplayName("Unauthenticated user cannot vote for post in single post view")
    void testUnauthenticatedUser4(AndroidDriver androidDriver, Screenshot screenshot) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(COMMENT_POST_BUTTON_XPATH)));

        screenshot.createScreenshot();

        WebElement commentPostButton = androidDriver.findElement(By.xpath(COMMENT_POST_BUTTON_XPATH));
        assertNotNull(commentPostButton);
        commentPostButton.click();

        wait = new WebDriverWait(androidDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .id(SINGLE_POST_VIEW_ID)));

        screenshot.createScreenshot();

        WebElement singlePostView = androidDriver.findElement(By.id(SINGLE_POST_VIEW_ID));
        assertNotNull(singlePostView);

        WebElement voteForPostButton = androidDriver.findElement(By.id(VOTE_FOR_POST_BUTTON_ID));
        assertNotNull(voteForPostButton);
        voteForPostButton.click();

        WebElement errorMessageDialog = androidDriver.findElement(By.id(ERROR_MESSAGE_DIALOG_ID));
        assertNotNull(errorMessageDialog);
        assertTrue(errorMessageDialog.isDisplayed());
        assertTrue(errorMessageDialog.isEnabled());
    }

    private static final String ADD_NEW_POST_BUTTON_ID = "io.github.feelfreelinux.wykopmobilny:id/fab";
    private static final String VOTE_FOR_POST_BUTTON_ID = "io.github.feelfreelinux.wykopmobilny:id/voteButton";
    private static final String ADD_COMMENT_TOOLBAR_ID = "io.github.feelfreelinux.wykopmobilny:id/inputToolbar";
    private static final String SINGLE_POST_VIEW_ID = "io.github.feelfreelinux.wykopmobilny:id/entry";
    private static final String ERROR_MESSAGE_DIALOG_ID = "android:id/contentPanel";

    private static final String COMMENT_POST_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/" +
            "android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/" +
            "android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[2]";
}
