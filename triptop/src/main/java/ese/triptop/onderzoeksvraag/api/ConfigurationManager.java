package ese.triptop.onderzoeksvraag.api;

/**
 * Interface for managing configuration values.
 * Provides a way to access configuration values for API endpoints, credentials, etc.
 */
public interface ConfigurationManager {
    
    /**
     * Gets a configuration value by key.
     * 
     * @param key The configuration key
     * @return The configuration value, or null if not found
     */
    String getConfigValue(String key);
    
    /**
     * Gets a configuration value by key with a default value.
     * 
     * @param key The configuration key
     * @param defaultValue The default value to return if the key is not found
     * @return The configuration value or the default value
     */
    String getConfigValue(String key, String defaultValue);
    
    /**
     * Reloads configuration from the source.
     * This can be used to refresh configuration values without restarting the application.
     */
    void reloadConfiguration();
}
