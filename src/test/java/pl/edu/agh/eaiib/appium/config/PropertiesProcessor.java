package pl.edu.agh.eaiib.appium.config;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public interface PropertiesProcessor {
    Properties process(Properties properties);
}

class PropertiesProcessorImpl implements PropertiesProcessor {
    private static final PropertiesProcessor[] PROCESSORS = new PropertiesProcessor[]{
            new ClasspathResourcesProcessor()
    };

    @Override
    public Properties process(Properties properties) {
        Properties toProcess = properties;
        for (PropertiesProcessor processor : PROCESSORS) {
            toProcess = processor.process(toProcess);
        }

        return toProcess;
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

