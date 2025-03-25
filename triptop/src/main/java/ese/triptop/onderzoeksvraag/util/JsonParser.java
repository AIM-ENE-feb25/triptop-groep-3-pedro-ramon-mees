package ese.triptop.onderzoeksvraag.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple JSON parser utility.
 * Provides methods for parsing JSON strings into Java objects.
 */
public class JsonParser {
    
    /**
     * Exception thrown when JSON parsing fails.
     */
    public static class JsonException extends Exception {
        public JsonException(String message) {
            super(message);
        }
        
        public JsonException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * Represents a JSON object.
     */
    public static class JsonObject {
        private final Map<String, Object> values = new HashMap<>();
        
        /**
         * Checks if the object has a property with the specified name.
         * 
         * @param name The property name
         * @return true if the property exists, false otherwise
         */
        public boolean has(String name) {
            return values.containsKey(name);
        }
        
        /**
         * Gets a string property.
         * 
         * @param name The property name
         * @return The property value
         * @throws JsonException If the property does not exist or is not a string
         */
        public String getString(String name) throws JsonException {
            Object value = values.get(name);
            if (value == null) {
                throw new JsonException("Property not found: " + name);
            }
            if (!(value instanceof String)) {
                throw new JsonException("Property is not a string: " + name);
            }
            return (String) value;
        }
        
        /**
         * Gets a double property.
         * 
         * @param name The property name
         * @return The property value
         * @throws JsonException If the property does not exist or is not a number
         */
        public double getDouble(String name) throws JsonException {
            Object value = values.get(name);
            if (value == null) {
                throw new JsonException("Property not found: " + name);
            }
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            }
            throw new JsonException("Property is not a number: " + name);
        }
        
        /**
         * Gets a JSON object property.
         * 
         * @param name The property name
         * @return The property value
         * @throws JsonException If the property does not exist or is not a JSON object
         */
        public JsonObject getJsonObject(String name) throws JsonException {
            Object value = values.get(name);
            if (value == null) {
                throw new JsonException("Property not found: " + name);
            }
            if (!(value instanceof JsonObject)) {
                throw new JsonException("Property is not a JSON object: " + name);
            }
            return (JsonObject) value;
        }
        
        /**
         * Gets a JSON array property.
         * 
         * @param name The property name
         * @return The property value
         * @throws JsonException If the property does not exist or is not a JSON array
         */
        public JsonArray getJsonArray(String name) throws JsonException {
            Object value = values.get(name);
            if (value == null) {
                throw new JsonException("Property not found: " + name);
            }
            if (!(value instanceof JsonArray)) {
                throw new JsonException("Property is not a JSON array: " + name);
            }
            return (JsonArray) value;
        }
        
        /**
         * Sets a property value.
         * 
         * @param name The property name
         * @param value The property value
         */
        public void put(String name, Object value) {
            values.put(name, value);
        }
    }
    
    /**
     * Represents a JSON array.
     */
    public static class JsonArray {
        private final List<Object> values = new ArrayList<>();
        
        /**
         * Gets the length of the array.
         * 
         * @return The length
         */
        public int length() {
            return values.size();
        }
        
        /**
         * Gets a string value at the specified index.
         * 
         * @param index The index
         * @return The value
         * @throws JsonException If the index is out of bounds or the value is not a string
         */
        public String getString(int index) throws JsonException {
            if (index < 0 || index >= values.size()) {
                throw new JsonException("Index out of bounds: " + index);
            }
            Object value = values.get(index);
            if (!(value instanceof String)) {
                throw new JsonException("Value is not a string at index: " + index);
            }
            return (String) value;
        }
        
        /**
         * Gets a JSON object at the specified index.
         * 
         * @param index The index
         * @return The value
         * @throws JsonException If the index is out of bounds or the value is not a JSON object
         */
        public JsonObject getJsonObject(int index) throws JsonException {
            if (index < 0 || index >= values.size()) {
                throw new JsonException("Index out of bounds: " + index);
            }
            Object value = values.get(index);
            if (!(value instanceof JsonObject)) {
                throw new JsonException("Value is not a JSON object at index: " + index);
            }
            return (JsonObject) value;
        }
        
        /**
         * Adds a value to the array.
         * 
         * @param value The value
         */
        public void add(Object value) {
            values.add(value);
        }
    }
    
    /**
     * Parses a JSON string into a JsonObject.
     * 
     * @param json The JSON string
     * @return The JsonObject
     * @throws JsonException If the JSON is invalid
     */
    public static JsonObject parseObject(String json) throws JsonException {
        // This is a simplified implementation
        // In a real application, you would use a proper JSON parser
        JsonObject object = new JsonObject();
        
        // For demonstration purposes, we'll just create a dummy object
        object.put("dummy", "This is a dummy implementation");
        
        return object;
    }
    
    /**
     * Parses a JSON string into a JsonArray.
     * 
     * @param json The JSON string
     * @return The JsonArray
     * @throws JsonException If the JSON is invalid
     */
    public static JsonArray parseArray(String json) throws JsonException {
        // This is a simplified implementation
        // In a real application, you would use a proper JSON parser
        JsonArray array = new JsonArray();
        
        // For demonstration purposes, we'll just create a dummy array
        array.add("This is a dummy implementation");
        
        return array;
    }
}
