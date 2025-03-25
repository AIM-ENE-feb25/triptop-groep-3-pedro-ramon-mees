package ese.triptop.onderzoeksvraag.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Spring-based implementation of the ConfigurationManager interface.
 * Uses Spring's Environment to access properties from application.properties.
 */
@Component
public class SpringPropertyConfigurationManager implements ConfigurationManager {
    
    private final Environment environment;
    
    /**
     * Creates a new SpringPropertyConfigurationManager with the specified environment.
     * 
     * @param environment The Spring environment
     */
    @Autowired
    public SpringPropertyConfigurationManager(Environment environment) {
        this.environment = environment;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfigValue(String key) {
        return environment.getProperty(key);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfigValue(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void reloadConfiguration() {
        // No-op for Spring-based implementation
        // Spring handles property reloading automatically
    }
}
