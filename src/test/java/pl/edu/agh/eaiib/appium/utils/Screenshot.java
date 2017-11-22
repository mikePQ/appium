package pl.edu.agh.eaiib.appium.utils;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Screenshot {
    private final AndroidDriver androidDriver;
    private final File directory;
    private final AtomicInteger counter = new AtomicInteger(1);

    public Screenshot(AndroidDriver androidDriver, File directory) {
        this.androidDriver = androidDriver;
        this.directory = directory;
    }

    public void createScreenshot() {
        File screenshotFile = new File(directory, counter.getAndIncrement() + ".png");
        try {
            Utils.createScreenshot(androidDriver, screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
