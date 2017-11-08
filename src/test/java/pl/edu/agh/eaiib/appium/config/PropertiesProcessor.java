package pl.edu.agh.eaiib.appium.config;

import java.net.URL;
import java.util.Map;
import java.util.Properties;

public interface PropertiesProcessor {
    Properties process(Properties properties);
    static PropertiesProcessor createInstance() {
        return new PropertiesProcessorImpl();
    }
}

class PropertiesProcessorImpl implements PropertiesProcessor {
    private static final PropertiesProcessor[] PROCESSORS = new PropertiesProcessor[]{
            new ClasspathResourcesProcessor(),
            new EnvironmentVariablesProcessor()
    };

    @Override
    public Properties process(Properties properties) {
        Properties toProcess = properties;
        for (PropertiesProcessor processor : PROCESSORS) {
            toProcess = processor.process(toProcess);
        }

        return toProcess;
    }

    private static class EnvironmentVariablesProcessor implements PropertiesProcessor {

        @Override
        public Properties process(Properties properties) {
            properties.entrySet()
                    .stream()
                    .forEach(entry -> entry.setValue(expandEnvVars(entry
                            .getValue()
                            .toString())));

            return properties;
        }

        private static String expandEnvVars(String text) {
            Map<String, String> envMap = System.getenv();
            for (Map.Entry<String, String> entry : envMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                text = text.replaceAll("\\$\\{" + key + "\\}", value);
            }
            return text;
        }
    }


    private static class ClasspathResourcesProcessor implements PropertiesProcessor {
        @Override
        public Properties process(Properties properties) {
            properties.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue()
                            .toString()
                            .startsWith("classpath:"))
                    .forEach(entry -> entry.setValue(getResourceLocation(entry
                            .getValue()
                            .toString())));

            return properties;
        }

        private String getResourceLocation(String classpathResource) {
            String resourceName = classpathResource.substring(classpathResource.lastIndexOf("classpath:") + "classpath:".length());
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(resourceName);
            if (resource == null) {
                throw new IllegalArgumentException("Resource not found: " + resourceName);
            }
            return resource.getFile();
        }
    }
}

