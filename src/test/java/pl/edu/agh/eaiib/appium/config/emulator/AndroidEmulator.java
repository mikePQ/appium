package pl.edu.agh.eaiib.appium.config.emulator;

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
            String emulatorPath = System.getProperty(EMULATOR_PATH_KEY);
            String adbPath = System.getProperty(ADB_PATH_KEY);
            String avdName = System.getProperty(AVD_NAME_KEY);

            return new EmulatorConfig(emulatorPath, adbPath, avdName);
        }

        private static final String EMULATOR_PATH_KEY = "emulator.path";
        private static final String ADB_PATH_KEY = "adb.path";
        private static final String AVD_NAME_KEY = "avd.name";
    }
}
