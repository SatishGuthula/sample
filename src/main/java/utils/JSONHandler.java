package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JSONHandler {
    private final ObjectMapper objectMapper;

    // ==================== Common Methods ====================

    public String stringify(JsonNode node) {
        if (node == null) {
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(node);
        } catch (Exception e) {
            return node.isArray() ? "[]" : "{}";
        }
    }

    public String stringifyPretty(JsonNode node) {
        return stringifyPretty(node, false);
    }

    public String stringifyPretty(JsonNode node, boolean sortKeys) {
        if (node == null) {
            return "{}";
        }
        try {
            ObjectMapper writer = sortKeys ?
                    objectMapper.copy().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true) :
                    objectMapper;
            return writer.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (Exception e) {
            return node.isArray() ? "[]" : "{}";
        }
    }

    public int size(JsonNode node) {
        return node != null ? node.size() : 0;
    }

    public boolean isEmpty(JsonNode node) {
        return node == null || node.isEmpty();
    }

    public boolean isObject(JsonNode node) {
        return node != null && node.isObject();
    }

    public boolean isArray(JsonNode node) {
        return node != null && node.isArray();
    }

    public boolean isTextual(JsonNode node) {
        return node != null && node.isTextual();
    }

    public boolean isNumber(JsonNode node) {
        return node != null && node.isNumber();
    }

    public boolean isBoolean(JsonNode node) {
        return node != null && node.isBoolean();
    }

    public boolean isNull(JsonNode node) {
        return node == null || node.isNull();
    }

    // ==================== Creation and Parse Methods ====================

    public ObjectNode createObject() {
        return objectMapper.createObjectNode();
    }

    public ArrayNode createArray() {
        return objectMapper.createArrayNode();
    }

    public JsonNode parse(String content) {
        try {
            return objectMapper.readTree(content);
        } catch (Exception e) {
            return null;
        }
    }

    public JsonNode parse(byte[] content) {
        try {
            return objectMapper.readTree(content);
        } catch (Exception e) {
            return null;
        }
    }

    public JsonNode parse(File file) {
        try {
            return objectMapper.readTree(file);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T parse(String value, Class<T> valueType) {
        try {
            return objectMapper.readValue(value, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T parse(byte[] content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T parse(File file, Class<T> valueType) {
        try {
            return objectMapper.readValue(file, valueType);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> ObjectNode parse(T pojo) {
        try {
            return objectMapper.valueToTree(pojo);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isValidJson(String json) {
        return parse(json) != null;
    }

    public ObjectNode createObjectWithString(String json) {
        JsonNode node = parse(json);
        return node != null && node.isObject() ? (ObjectNode) node.deepCopy() : createObject();
    }

    public ArrayNode createArrayWithString(String json) {
        JsonNode node = parse(json);
        return node != null && node.isArray() ? (ArrayNode) node.deepCopy() : createArray();
    }

    // ==================== Put Methods ====================

    // Put Array Methods - ObjectNode
    public void put(ObjectNode node, String key, int[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (int value : values) {
                arrayNode.add(value);
            }
        }
        node.set(key, arrayNode);
    }

    public void put(ObjectNode node, String key, long[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (long value : values) {
                arrayNode.add(value);
            }
        }
        node.set(key, arrayNode);
    }

    public void put(ObjectNode node, String key, double[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (double value : values) {
                arrayNode.add(value);
            }
        }
        node.set(key, arrayNode);
    }

    public void put(ObjectNode node, String key, float[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (float value : values) {
                arrayNode.add(value);
            }
        }
        node.set(key, arrayNode);
    }

    public void put(ObjectNode node, String key, boolean[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (boolean value : values) {
                arrayNode.add(value);
            }
        }
        node.set(key, arrayNode);
    }

    // Put Array Methods - ArrayNode
    public void put(ArrayNode node, int index, String[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (String value : values) {
                arrayNode.add(value != null ? value : "");
            }
        }
        ensureArrayCapacity(node, index);
        node.set(index, arrayNode);
    }

    public void put(ArrayNode node, int index, int[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (int value : values) {
                arrayNode.add(value);
            }
        }
        ensureArrayCapacity(node, index);
        node.set(index, arrayNode);
    }

    public void put(ArrayNode node, int index, long[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (long value : values) {
                arrayNode.add(value);
            }
        }
        ensureArrayCapacity(node, index);
        node.set(index, arrayNode);
    }

    public void put(ArrayNode node, int index, double[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (double value : values) {
                arrayNode.add(value);
            }
        }
        ensureArrayCapacity(node, index);
        node.set(index, arrayNode);
    }

    public void put(ArrayNode node, int index, float[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (float value : values) {
                arrayNode.add(value);
            }
        }
        ensureArrayCapacity(node, index);
        node.set(index, arrayNode);
    }

    public void put(ArrayNode node, int index, boolean[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (boolean value : values) {
                arrayNode.add(value);
            }
        }
        ensureArrayCapacity(node, index);
        node.set(index, arrayNode);
    }

    public void put(ObjectNode node, String key, String value) {
        node.put(key, value != null ? value : "");
    }

    public void put(ObjectNode node, String key, String[] values) {
        ArrayNode arrayNode = createArray();
        if (values != null) {
            for (String value : values) {
                arrayNode.add(value != null ? value : "");
            }
        }
        node.set(key, arrayNode);
    }

    public void put(ObjectNode node, String key, int value) {
        node.put(key, value);
    }

    public void put(ObjectNode node, String key, long value) {
        node.put(key, value);
    }

    public void put(ObjectNode node, String key, double value) {
        node.put(key, value);
    }

    public void put(ObjectNode node, String key, float value) {
        node.put(key, value);
    }

    public void put(ObjectNode node, String key, boolean value) {
        node.put(key, value);
    }

    public void put(ObjectNode node, String key, JsonNode value) {
        if (value == null) {
            value = value instanceof ArrayNode ? createArray() : createObject();
        }
        node.set(key, value.deepCopy());
    }

    public <T> void put(ObjectNode node, String key, T value) {
        if (node != null) {
            JsonNode jsonNode = value != null ? objectMapper.valueToTree(value) : createNull();
            node.set(key, jsonNode);
        }
    }

    public void putNull(ObjectNode node, String key) {
        node.putNull(key);
    }

    // ==================== PutIfAbsent Methods ====================

    public void putIfAbsent(ObjectNode node, String key, String value) {
        if (!node.has(key)) {
            node.put(key, value != null ? value : "");
        }
    }

    public void putIfAbsent(ObjectNode node, String key, int value) {
        if (!node.has(key)) {
            node.put(key, value);
        }
    }

    public void putIfAbsent(ObjectNode node, String key, long value) {
        if (!node.has(key)) {
            node.put(key, value);
        }
    }

    public void putIfAbsent(ObjectNode node, String key, double value) {
        if (!node.has(key)) {
            node.put(key, value);
        }
    }

    public void putIfAbsent(ObjectNode node, String key, float value) {
        if (!node.has(key)) {
            node.put(key, value);
        }
    }

    public void putIfAbsent(ObjectNode node, String key, boolean value) {
        if (!node.has(key)) {
            node.put(key, value);
        }
    }

    public void putIfAbsent(ObjectNode node, String key, JsonNode value) {
        if (!node.has(key)) {
            if (value == null) {
                value = value instanceof ArrayNode ? createArray() : createObject();
            }
            node.set(key, value.deepCopy());
        }
    }

    public <T> void putIfAbsent(ObjectNode node, String key, T value) {
        if (node != null && !node.has(key)) {
            JsonNode jsonNode = value != null ? objectMapper.valueToTree(value) : createNull();
            node.set(key, jsonNode);
        }
    }

    // Put Methods - ArrayNode
    public void put(ArrayNode node, int index, String value) {
        ensureArrayCapacity(node, index);
        node.set(index, value != null ? value : "");
    }

    public void put(ArrayNode node, int index, int value) {
        ensureArrayCapacity(node, index);
        node.set(index, value);
    }

    public void put(ArrayNode node, int index, long value) {
        ensureArrayCapacity(node, index);
        node.set(index, value);
    }

    public void put(ArrayNode node, int index, double value) {
        ensureArrayCapacity(node, index);
        node.set(index, value);
    }

    public void put(ArrayNode node, int index, float value) {
        ensureArrayCapacity(node, index);
        node.set(index, value);
    }

    public void put(ArrayNode node, int index, boolean value) {
        ensureArrayCapacity(node, index);
        node.set(index, value);
    }

    public void put(ArrayNode node, int index, JsonNode value) {
        ensureArrayCapacity(node, index);
        if (value == null) {
            value = value instanceof ArrayNode ? createArray() : createObject();
        }
        node.set(index, value.deepCopy());
    }

    public <T> void put(ArrayNode node, int index, T value) {
        if (node != null) {
            JsonNode jsonNode = value != null ? objectMapper.valueToTree(value) : createNull();
            ensureArrayCapacity(node, index);
            node.set(index, jsonNode);
        }
    }

    public void putNull(ArrayNode node, int index) {
        node.setNull(index);
    }

    // ==================== Insert Methods (ArrayNode only) ====================

    public void insert(ArrayNode node, int index, String value) {
        node.insert(index, value != null ? value : "");
    }

    public void insert(ArrayNode node, int index, int value) {
        node.insert(index, value);
    }

    public void insert(ArrayNode node, int index, long value) {
        node.insert(index, value);
    }

    public void insert(ArrayNode node, int index, double value) {
        node.insert(index, value);
    }

    public void insert(ArrayNode node, int index, float value) {
        node.insert(index, value);
    }

    public void insert(ArrayNode node, int index, boolean value) {
        node.insert(index, value);
    }

    public void insert(ArrayNode node, int index, JsonNode value) {
        if (value == null) {
            node.insert(index, createNull());
        } else {
            node.insert(index, value.deepCopy());
        }
    }

    public <T> void insert(ArrayNode node, int index, T value) {
        if (node != null) {
            JsonNode jsonNode = value != null ? objectMapper.valueToTree(value) : createNull();
            node.insert(index, jsonNode);
        }
    }

    // ==================== Add Methods (ArrayNode only) ====================

    public void add(ArrayNode node, String value) {
        node.add(value != null ? value : "");
    }

    public void add(ArrayNode node, int value) {
        node.add(value);
    }

    public void add(ArrayNode node, long value) {
        node.add(value);
    }

    public void add(ArrayNode node, double value) {
        node.add(value);
    }

    public void add(ArrayNode node, float value) {
        node.add(value);
    }

    public void add(ArrayNode node, boolean value) {
        node.add(value);
    }

    public void add(ArrayNode node, JsonNode value) {
        if (value == null) {
            node.addNull();
        } else {
            node.add(value.deepCopy());
        }
    }

    public <T> void add(ArrayNode node, T value) {
        if (node != null) {
            JsonNode jsonNode = value != null ? objectMapper.valueToTree(value) : createNull();
            node.add(jsonNode);
        }
    }

    // ==================== Get Methods ====================

    // Base get methods
    public JsonNode get(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.deepCopy() : null;
    }

    public JsonNode get(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? value.deepCopy() : null;
    }

    public JsonNode get(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.deepCopy() : null;
    }

    public JsonNode get(JsonNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? value.deepCopy() : null;
    }

    // String get methods
    public String getString(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.asText() : "";
    }

    public String getString(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? value.asText() : "";
    }

    public String getString(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.asText() : "";
    }

    // Integer get methods
    public int getInt(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.asInt() : 0;
    }

    public int getInt(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? value.asInt() : 0;
    }

    public int getInt(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.asInt() : 0;
    }

    // Long get methods
    public long getLong(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.asLong() : 0L;
    }

    public long getLong(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? value.asLong() : 0L;
    }

    public long getLong(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.asLong() : 0L;
    }

    // Double get methods
    public double getDouble(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.asDouble() : 0.0;
    }

    public double getDouble(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? value.asDouble() : 0.0;
    }

    public double getDouble(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? value.asDouble() : 0.0;
    }

    // Float get methods
    public float getFloat(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? (float) value.asDouble() : 0.0f;
    }

    public float getFloat(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? (float) value.asDouble() : 0.0f;
    }

    public float getFloat(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? (float) value.asDouble() : 0.0f;
    }

    // Boolean get methods
    public boolean getBoolean(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null && value.asBoolean();
    }

    public boolean getBoolean(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null && value.asBoolean();
    }

    public boolean getBoolean(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null && value.asBoolean();
    }

    // Object get methods
    public ObjectNode getObject(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? (ObjectNode) value.deepCopy() : createObject();
    }

    public ObjectNode getObject(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? (ObjectNode) value.deepCopy() : createObject();
    }

    public ObjectNode getObject(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? (ObjectNode) value.deepCopy() : createObject();
    }

    // Array get methods
    public ArrayNode getArray(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? (ArrayNode) value.deepCopy() : createArray();
    }

    public ArrayNode getArray(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return value != null ? (ArrayNode) value.deepCopy() : createArray();
    }

    public ArrayNode getArray(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null ? (ArrayNode) value.deepCopy() : createArray();
    }

    // ==================== Get with Default Methods ====================

    // String get with default methods
    public String getStringOrDefault(ObjectNode node, String key, String defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isTextual()) ? value.asText() : defaultValue;
    }

    public String getStringOrDefault(ArrayNode node, int index, String defaultValue) {
        JsonNode value = node.get(index);
        return (value != null && !value.isNull()) ? value.asText() : defaultValue;
    }

    public String getStringOrDefault(JsonNode node, String key, String defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isTextual()) ? value.asText() : defaultValue;
    }

    // Integer get with default methods
    public int getIntOrDefault(ObjectNode node, String key, int defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isInt()) ? value.asInt() : defaultValue;
    }

    public int getIntOrDefault(ArrayNode node, int index, int defaultValue) {
        JsonNode value = node.get(index);
        return (value != null && !value.isNull()) ? value.asInt() : defaultValue;
    }

    public int getIntOrDefault(JsonNode node, String key, int defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isInt()) ? value.asInt() : defaultValue;
    }

    // Long get with default methods
    public long getLongOrDefault(ObjectNode node, String key, long defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isNumber()) ? value.asLong() : defaultValue;
    }

    public long getLongOrDefault(ArrayNode node, int index, long defaultValue) {
        JsonNode value = node.get(index);
        return (value != null && !value.isNull()) ? value.asLong() : defaultValue;
    }

    public long getLongOrDefault(JsonNode node, String key, long defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isNumber()) ? value.asLong() : defaultValue;
    }

    // Double get with default methods
    public double getDoubleOrDefault(ObjectNode node, String key, double defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isNumber()) ? value.asDouble() : defaultValue;
    }

    public double getDoubleOrDefault(ArrayNode node, int index, double defaultValue) {
        JsonNode value = node.get(index);
        return (value != null && !value.isNull()) ? value.asDouble() : defaultValue;
    }

    public double getDoubleOrDefault(JsonNode node, String key, double defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isNumber()) ? value.asDouble() : defaultValue;
    }

    // Float get with default methods
    public float getFloatOrDefault(ObjectNode node, String key, float defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isNumber()) ? (float) value.asDouble() : defaultValue;
    }

    public float getFloatOrDefault(ArrayNode node, int index, float defaultValue) {
        JsonNode value = node.get(index);
        return (value != null && !value.isNull()) ? (float) value.asDouble() : defaultValue;
    }

    public float getFloatOrDefault(JsonNode node, String key, float defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isNumber()) ? (float) value.asDouble() : defaultValue;
    }

    // Boolean get with default methods
    public boolean getBooleanOrDefault(ObjectNode node, String key, boolean defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isBoolean()) ? value.asBoolean() : defaultValue;
    }

    public boolean getBooleanOrDefault(ArrayNode node, int index, boolean defaultValue) {
        JsonNode value = node.get(index);
        return (value != null && !value.isNull()) ? value.asBoolean() : defaultValue;
    }

    public boolean getBooleanOrDefault(JsonNode node, String key, boolean defaultValue) {
        JsonNode value = node.get(key);
        return (value != null && value.isBoolean()) ? value.asBoolean() : defaultValue;
    }

    // Object get with default methods
    public ObjectNode getObjectOrDefault(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return (value != null && value.isObject()) ? ((ObjectNode) value).deepCopy() : createObject();
    }

    public ObjectNode getObjectOrDefault(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return (value != null && value.isObject()) ? ((ObjectNode) value).deepCopy() : createObject();
    }

    public ObjectNode getObjectOrDefault(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return (value != null && value.isObject()) ? ((ObjectNode) value).deepCopy() : createObject();
    }

    // Array get with default methods
    public ArrayNode getArrayOrDefault(ObjectNode node, String key) {
        JsonNode value = node.get(key);
        return (value != null && value.isArray()) ? ((ArrayNode) value).deepCopy() : createArray();
    }

    public ArrayNode getArrayOrDefault(ArrayNode node, int index) {
        JsonNode value = node.get(index);
        return (value != null && value.isArray()) ? ((ArrayNode) value).deepCopy() : createArray();
    }

    public ArrayNode getArrayOrDefault(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return (value != null && value.isArray()) ? ((ArrayNode) value).deepCopy() : createArray();
    }

    // ==================== Set All Method ====================

    public void setAll(ObjectNode targetNode, ObjectNode sourceNode) {
        if (targetNode != null && sourceNode != null) {
            targetNode.setAll(sourceNode.deepCopy());
        }
    }

    // ==================== Utility Methods ====================

    // Remove methods
    public void remove(ObjectNode node, String key) {
        if (node != null) {
            node.remove(key);
        }
    }

    public void remove(ArrayNode node, int index) {
        if (node != null && index < node.size()) {
            node.remove(index);
        }
    }

    // Clear methods
    public void clear(ObjectNode node) {
        if (node != null) {
            node.removeAll();
        }
    }

    public void clear(ArrayNode node) {
        if (node != null) {
            node.removeAll();
        }
    }

    // Has methods
    public boolean has(ObjectNode node, String key) {
        return node != null && node.has(key);
    }

    public boolean has(ArrayNode node, int index) {
        return node != null && index >= 0 && index < node.size();
    }

    public boolean has(JsonNode node, String key) {
        return node != null && node.has(key);
    }

    public boolean hasNonNull(ObjectNode node, String key) {
        return node != null && node.hasNonNull(key);
    }

    public boolean hasNonNull(ArrayNode node, int index) {
        return has(node, index) && !node.get(index).isNull();
    }

    public boolean hasNonNull(JsonNode node, String key) {
        return node != null && node.hasNonNull(key);
    }

    // Field names
    public List<String> fieldNames(ObjectNode node) {
        List<String> fields = new ArrayList<>();
        if (node != null) {
            node.fieldNames().forEachRemaining(fields::add);
        }
        return fields;
    }

    // Merge methods
    public void merge(ObjectNode targetNode, ObjectNode sourceNode) {
        if (targetNode != null && sourceNode != null) {
            targetNode.setAll(sourceNode.deepCopy());
        }
    }

    // ==================== Conversion Methods ====================

    public Map<String, Object> toMap(ObjectNode node) {
        try {
            return objectMapper.convertValue(node, Map.class);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public List<Object> toList(ArrayNode node) {
        try {
            return objectMapper.convertValue(node, List.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public <T> T toObject(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    // ==================== Deep Copy Methods ====================

    public ObjectNode deepCopy(ObjectNode node) {
        return node != null ? node.deepCopy() : createObject();
    }

    public ArrayNode deepCopy(ArrayNode node) {
        return node != null ? node.deepCopy() : createArray();
    }

    public JsonNode deepCopy(JsonNode node) {
        return node != null ? node.deepCopy() : null;
    }

    // ==================== Path Methods ====================

    public JsonNode at(JsonNode node, String jsonPointer) {
        try {
            JsonNode value = node.at(jsonPointer);
            return !value.isMissingNode() ? value.deepCopy() : null;
        } catch (Exception e) {
            return null;
        }
    }

    // ==================== Private Helper Methods ====================

    private void ensureArrayCapacity(ArrayNode node, int index) {
        while (node.size() <= index) {
            node.addNull();
        }
    }

    private JsonNode createNull() {
        return objectMapper.nullNode();
    }

}