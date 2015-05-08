package by.epam.hotel.model.command.configuration;

import java.util.ResourceBundle;

/**
 * Class extracts info from config.properties file
 * Messages are contained in config.properties file
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("resources.config.config");

    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
