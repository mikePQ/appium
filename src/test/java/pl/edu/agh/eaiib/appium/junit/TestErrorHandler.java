package pl.edu.agh.eaiib.appium.junit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.extension.ExtensionContext;
import pl.edu.agh.eaiib.appium.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestErrorHandler {
    private final File screenshotsDir;
    private AndroidDriver androidDriver;

    public TestErrorHandler(File screenshotsDir) throws IOException {
        this.screenshotsDir = screenshotsDir;
        if (!screenshotsDir.exists()) {
            Files.createDirectories(screenshotsDir.toPath());
        }
    }

    public void handleTestError(ExtensionContext context, Throwable throwable) throws Throwable {
        try {
            File screenshotFile = new File(screenshotsDir, context.getDisplayName() + ".png");
            Utils.createScreenshot(androidDriver, screenshotFile);
        } finally {
            throw throwable;
        }
    }

    void setAndroidDriver(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }
}
