package pl.edu.agh.eaiib.appium.junit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestErrorHandler {
    private final File screenshotsDir;
    private AppiumDriver androidDriver;

    public TestErrorHandler(File screenshotsDir) throws IOException {
        this.screenshotsDir = screenshotsDir;
        if (!screenshotsDir.exists()) {
            Files.createDirectories(screenshotsDir.toPath());
        }
    }

    public void handleTestError(ExtensionContext context, Throwable throwable) throws Throwable {
        if (androidDriver == null) {
            System.err.println("Cannot make screenshot, android driver is not available");
            throw throwable;
        }

        File screenshotFile = new File(screenshotsDir, context.getDisplayName() + ".png");
        if (screenshotFile.exists()) {
            screenshotFile.delete();
        }
        screenshotFile.createNewFile();

        File srcFiler = androidDriver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFiler, screenshotFile);

        throw throwable;
    }

    void setAndroidDriver(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }
}
