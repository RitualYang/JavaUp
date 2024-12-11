package com.wty.network.ok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonStringConvertor {


    static final JsonStringConvertor INSTANCE = new JsonStringConvertor();

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final byte[] zeroByte = new byte[0];

    public JsonStringConvertor() {
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
    }

    public <T> String to(T input) {
        try {
            return this.objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException ex) {
            return "ERR";
        }
    }

    public <T> byte[] toBytes(T input) {
        try {
            return this.objectMapper.writeValueAsBytes(input);
        } catch (JsonProcessingException ex) {
            return zeroByte;
        }
    }

    public Object from(byte[] b, TypeReference type) throws IOException {
        return this.objectMapper.readValue(b, type);
    }

    public Object from(String b, TypeReference type) throws IOException {
        return this.objectMapper.readValue(b, type);
    }

    /**
     * List<MyClass> myObjects = mapper.readValue(jsonInput, new TypeReference<List<MyClass>>(){});
     *
     * @param src
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T from(String src, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(src, clazz);
        } catch (IOException ex) {
            return null;
        }
    }

    public <T> T from(InputStream ins, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(ins, clazz);
        } catch (IOException ex) {
            return null;
        }
    }

    public <T> T fromEx(InputStream ins, Class<T> clazz) throws IOException {
        return this.objectMapper.readValue(ins, clazz);
    }

    public <T> T from(byte[] bytes, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(bytes, clazz);
        } catch (IOException ex) {
            return null;
        }
    }

    public <T> T fromEx(byte[] bytes, Class<T> clazz) throws IOException {
        return this.objectMapper.readValue(bytes, clazz);
    }


    public String map2Json(Map<String, Object> obj) {
        try {
            return this.objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public <T> T map2Obj(Map<String, Object> objMap, Class<T> clazz) {
        return this.objectMapper.convertValue(objMap, clazz);
    }

    public <T> Map<String, Object> obj2Map(T obj) {
        return this.objectMapper.convertValue(obj, Map.class);
    }

    /**
     * mapper.getTypeFactory().constructCollectionType(List.class, MyClass.class)
     *
     * @param src
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> List<T> fromList(String src, Class<T> clazz) throws IOException {
        return this.objectMapper.readValue(src, this.objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public <T> List<T> fromList(InputStream ins, Class<T> clazz) throws IOException {
        return this.objectMapper.readValue(ins, this.objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static JsonStringConvertor instance() {
        return INSTANCE;
    }
}
