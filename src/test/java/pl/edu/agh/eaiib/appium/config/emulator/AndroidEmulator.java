package pl.edu.agh.eaiib.appium.config.emulator;

import pl.edu.agh.eaiib.appium.config.PropertiesProcessor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

public class AndroidEmulator {
    private final EmulatorConfig emulatorConfig;

    public AndroidEmulator(EmulatorConfig emulatorConfig) {
        this.emulatorConfig = emulatorConfig;
    }

    public static AndroidEmulator create() {
        return new AndroidEmulator(EmulatorConfig.create());
    }

    public void startEmulator() {
        executeCommand(emulatorConfig.emulatorPath, "-avd", emulatorConfig.avdName);
        wait(30000);
    }

    public void stopEmulator() {
        executeCommand(emulatorConfig.adbPath, "emu", "kill");
    }

    private Process executeCommand(String... parameters) {
        try {
            return Runtime.getRuntime().exec(parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final class EmulatorConfig {
        private final String emulatorPath;
        private final String adbPath;
        private final String avdName;

        public EmulatorConfig(String emulatorPath, String adbPath, String avdName) {
            this.emulatorPath = emulatorPath;
            this.adbPath = adbPath;
            this.avdName = avdName;
        }

        private static EmulatorConfig create() {
            ClassLoader classLoader = AndroidEmulator.class.getClassLoader();
            File file = new File(classLoader.getResource("emulator.properties").getFile());
            Properties properties = new Properties();
            try (FileReader reader = new FileReader(file)) {
                properties.load(reader);
                PropertiesProcessor propertiesProcessor = PropertiesProcessor.createInstance();
                properties = propertiesProcessor.process(properties);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

            String emulatorPath = properties.getProperty(EMULATOR_PATH_KEY);
            String adbPath = properties.getProperty(ADB_PATH_KEY);
            String avdName = properties.getProperty(AVD_NAME_KEY);

            return new EmulatorConfig(emulatorPath, adbPath, avdName);
        }

        private static final String EMULATOR_PATH_KEY = "emulator.path";
        private static final String ADB_PATH_KEY = "adb.path";
        private static final String AVD_NAME_KEY = "avd.name";
    }
}
