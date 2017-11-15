package pl.edu.agh.eaiib.appium.junit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.extension.*;
import pl.edu.agh.eaiib.appium.config.AppiumConfig;
import pl.edu.agh.eaiib.appium.config.emulator.AndroidEmulator;

import java.io.IOException;

public class AppiumExtension implements BeforeAllCallback, AfterAllCallback, AfterEachCallback, ParameterResolver, TestExecutionExceptionHandler {

    private final AndroidEmulator androidEmulator;
    private final TestErrorHandler errorHandler;
    private AndroidDriver androidDriver;

    public AppiumExtension() throws IOException {
        androidEmulator = AndroidEmulator.create();
        androidEmulator.startEmulator();
        addCloseEmulatorCallback(androidEmulator);

        errorHandler = new TestErrorHandler(AppiumConfig.getInstance().getScreenshotsDir());
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        androidDriver = AppiumConfig.getInstance().getDriver();
        errorHandler.setAndroidDriver(androidDriver);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (androidDriver != null) {
            androidDriver.resetApp();
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return (parameterContext.getParameter().getType() == AndroidDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return androidDriver;
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        errorHandler.handleTestError(context, throwable);
    }

    private static void addCloseEmulatorCallback(AndroidEmulator androidEmulator) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            androidEmulator.stopEmulator();
        }));
    }
}
