package pl.edu.agh.eaiib.appium.utils;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

public class Utils {
    private Utils() {
    }

    public static void createScreenshot(AndroidDriver driver, File outputFile) throws IOException {
        if (driver == null) {
            System.err.println("Cannot make screenshot, android driver is not available");
            return;
        }

        if (outputFile.exists()) {
            outputFile.delete();
        }
        outputFile.createNewFile();

        File srcFiler = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFiler, outputFile);
    }
}
