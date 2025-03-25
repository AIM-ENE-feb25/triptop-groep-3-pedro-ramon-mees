package ese.triptop.onderzoeksvraag.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implementation of ConfigurationManager that uses Java Properties.
 * Loads configuration from a properties file.
 */
public class PropertyConfigurationManager implements ConfigurationManager {
    
    private Properties properties;
    private final String configPath;
    
    /**
     * Creates a new PropertyConfigurationManager with the specified configuration file path.
     * 
     * @param configPath The path to the properties file
     */
    public PropertyConfigurationManager(String configPath) {
        this.configPath = configPath;
        this.properties = new Properties();
        reloadConfiguration();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfigValue(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfigValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void reloadConfiguration() {
        Properties newProperties = new Properties();
        
        try (InputStream input = new FileInputStream(configPath)) {
            newProperties.load(input);
            this.properties = newProperties;
        } catch (IOException e) {
            System.err.println("Failed to load configuration from " + configPath + ": " + e.getMessage());
            // Keep using the existing properties if loading fails
        }
    }
    
    /**
     * Alternative constructor that loads properties from the classpath.
     * 
     * @param resourcePath The path to the properties file in the classpath
     * @return A new PropertyConfigurationManager
     */
    public static PropertyConfigurationManager fromClasspath(String resourcePath) {
        PropertyConfigurationManager manager = new PropertyConfigurationManager(resourcePath);
        manager.loadFromClasspath(resourcePath);
        return manager;
    }
    
    /**
     * Loads properties from the classpath.
     * 
     * @param resourcePath The path to the properties file in the classpath
     */
    private void loadFromClasspath(String resourcePath) {
        Properties newProperties = new Properties();
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (input != null) {
                newProperties.load(input);
                this.properties = newProperties;
            } else {
                System.err.println("Could not find resource: " + resourcePath);
            }
        } catch (IOException e) {
            System.err.println("Failed to load configuration from classpath " + resourcePath + ": " + e.getMessage());
        }
    }
}
