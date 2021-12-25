package com.meishubao.redis.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.base.Throwables;
import com.meishubao.redis.exception.JsonHandleException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
public class JsonUtil {

    private static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        // 设置时区
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 设置时间格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        SimpleModule module = new SimpleModule("CustomSerializer", new Version(1, 0, 0, "", "", ""));
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(long.class, ToStringSerializer.instance);

        /** 注册模块 **/
        OBJECT_MAPPER.registerModule(new JavaTimeModule())
                .registerModule(module)
                .registerModule(new Jdk8Module())
                .registerModule(new ParameterNamesModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JsonUtil() {
    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * Object转换成json字符串，宽松的，建议在日志打印中使用
     * <pre>使用方法:<code>String json = JsonUtil.toJson(person);<code/></pre>
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return toJson(object, OBJECT_MAPPER);
    }

    /**
     * Object转换成json字符串，宽松的，建议在日志打印中使用
     * <pre>使用方法:<code>String json = JsonUtil.toJson(person, objectMapper);<code/></pre>
     *
     * @param object
     * @param objectMapper
     * @return
     */
    public static String toJson(Object object, ObjectMapper objectMapper) {
        try {
            if (ObjectUtils.isEmpty(object)) {
                return StringUtils.EMPTY;
            }
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Object转换Json异常，异常信息:{}", Throwables.getStackTraceAsString(e));
            return StringUtils.EMPTY;
        }
    }

    /**
     * Object转换成json字符串，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>String json = JsonUtil.safeToJson(person);<code/></pre>
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String safeToJson(Object object) throws JsonProcessingException {
        return safeToJson(object, OBJECT_MAPPER);
    }

    /**
     * Object转换成json字符串，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>String json = JsonUtil.safeToJson(person, objectMapper);<code/></pre>
     *
     * @param object
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static String safeToJson(Object object, ObjectMapper objectMapper) throws JsonProcessingException {
        if (ObjectUtils.isEmpty(object)) {
            throw new JsonHandleException("object is null, cannot be converted to JSON string");
        }
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Object转换成字节数组，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>byte[] bytes = JsonUtil.safeToBytes(person);<code/></pre>
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static byte[] safeToBytes(Object object) throws JsonProcessingException {
        return safeToBytes(object, OBJECT_MAPPER);
    }

    /**
     * Object转换成字节数组，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>byte[] bytes = JsonUtil.safeToBytes(person, objectMapper);<code/></pre>
     *
     * @param object
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static byte[] safeToBytes(Object object, ObjectMapper objectMapper) throws JsonProcessingException {
        if (ObjectUtils.isEmpty(object)) {
            throw new JsonHandleException("object is null, cannot be converted to byte array");
        }
        return objectMapper.writeValueAsBytes(object);
    }

    /**
     * 类型转换，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Person person = JsonUtil.safeToObject(map, Person.class);<code/></pre>
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T safeToObject(Object object, Class<T> clazz) throws JsonProcessingException {
        return safeToObject(object, clazz, OBJECT_MAPPER);
    }

    /**
     * 类型转换，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Person person = JsonUtil.safeToObject(map, Person.class, objectMapper);<code/></pre>
     *
     * @param object
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T safeToObject(Object object, Class<T> clazz, ObjectMapper objectMapper) throws JsonProcessingException {
        if (ObjectUtils.isEmpty(object)) {
            throw new JsonHandleException("object is null, cannot be converted to JSON target object");
        }
        return objectMapper.convertValue(object, clazz);
    }

    /**
     * 类型转换，支持自定义泛型，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Animal&lt;Monkey&gt; animal = JsonUtil.safeToObject(map, new TypeReference&lt;&gt;(){});<code/></pre>
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T safeToObject(Object object, TypeReference<T> typeReference) throws JsonProcessingException {
        return safeToObject(object, typeReference, OBJECT_MAPPER);
    }

    /**
     * 类型转换，支持自定义泛型，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Animal&lt;Monkey&gt; animal = JsonUtil.safeToObject(map, new TypeReference&lt;&gt;(){}, objectMapper);<code/></pre>
     *
     * @param object
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T safeToObject(Object object, TypeReference<T> typeReference, ObjectMapper objectMapper) throws JsonProcessingException {
        if (ObjectUtils.isEmpty(object)) {
            throw new JsonHandleException("object is null, cannot be converted to JSON target object");
        }
        return objectMapper.convertValue(object, typeReference);
    }

    /**
     * Object转换成json字符串，忽略空值，宽松的，建议在日志打印中使用
     * <pre>使用方法:<code>String json = JsonUtil.toJsonIgnoreNull(person);<code/></pre>
     *
     * @param object
     * @return
     */
    public static String toJsonIgnoreNull(Object object) {
        return toJsonIgnoreNull(object, getInstance().copy());
    }

    /**
     * Object转换成json字符串，忽略空值，宽松的，建议在日志打印中使用
     * <pre>使用方法:<code>String json = JsonUtil.toJsonIgnoreNull(person, objectMapper);<code/></pre>
     *
     * @param object
     * @param objectMapper
     * @return
     */
    public static String toJsonIgnoreNull(Object object, ObjectMapper objectMapper) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return toJson(object, objectMapper);
    }

    /**
     * Object转换成json字符串，忽略空值，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>String json = JsonUtil.safeToJsonIgnoreNull(person);<code/></pre>
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String safeToJsonIgnoreNull(Object object) throws JsonProcessingException {
        return safeToJsonIgnoreNull(object, getInstance().copy());
    }

    /**
     * Object转换成json字符串，忽略空值，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>String json = JsonUtil.safeToJsonIgnoreNull(person, objectMapper);<code/></pre>
     *
     * @param object
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static String safeToJsonIgnoreNull(Object object, ObjectMapper objectMapper) throws JsonProcessingException {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return safeToJson(object, objectMapper);
    }

    /**
     * json字符串转换成Object，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Person person = JsonUtil.safeRead(json, Person.class);<code/></pre>
     *
     * @param json
     * @param clazz
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T safeRead(String json, Class<T> clazz) throws JsonProcessingException {
        return safeRead(json, clazz, OBJECT_MAPPER);
    }

    /**
     * json字符串转换成Object，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Person person = JsonUtil.safeRead(json, Person.class, objectMapper);<code/></pre>
     *
     * @param json
     * @param clazz
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T safeRead(String json, Class<T> clazz, ObjectMapper objectMapper) throws JsonProcessingException {
        if (StringUtils.isBlank(json)) {
            throw new JsonHandleException("json string is blank, cannot be converted to object");
        }
        return objectMapper.readValue(json, clazz);
    }

    /**
     * json字符串转换成Object，支持自定义泛型，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Animal&lt;Monkey&gt; animal = JsonUtil.safeRead(json, new TypeReference&lt;&gt;(){});<code/></pre>
     *
     * @param json
     * @param typeReference
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T safeRead(String json, TypeReference<T> typeReference) throws JsonProcessingException {
        return safeRead(json, typeReference, OBJECT_MAPPER);
    }

    /**
     * json字符串转换成Object，支持自定义泛型，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Animal&lt;Monkey&gt; animal = JsonUtil.safeRead(json, new TypeReference&lt;&gt;(){}, objectMapper);<code/></pre>
     *
     * @param json
     * @param typeReference
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T safeRead(String json, TypeReference<T> typeReference, ObjectMapper objectMapper) throws JsonProcessingException {
        if (StringUtils.isBlank(json)) {
            throw new JsonHandleException("json string is blank, cannot be converted to object");
        }
        return objectMapper.readValue(json, typeReference);
    }

    /**
     * 字节数组转换成Object，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Person person = JsonUtil.safeRead(bytes, Person.class);<code/></pre>
     *
     * @param bytes
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T safeRead(byte[] bytes, Class<T> clazz) throws IOException {
        return safeRead(bytes, clazz, OBJECT_MAPPER);
    }

    /**
     * 字节数组转换成Object，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Person person = JsonUtil.safeRead(bytes, Person.class, objectMapper);<code/></pre>
     *
     * @param bytes
     * @param clazz
     * @param objectMapper
     * @return
     * @throws IOException
     */
    public static <T> T safeRead(byte[] bytes, Class<T> clazz, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(bytes, clazz);
    }

    /**
     * 字节数组转换成Object，支持自定义泛型，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Animal&lt;Monkey&gt; animal = JsonUtil.safeRead(bytes, new TypeReference&lt;&gt;(){});<code/></pre>
     *
     * @param bytes
     * @param typeReference
     * @return
     * @throws IOException
     */
    public static <T> T safeRead(byte[] bytes, TypeReference<T> typeReference) throws IOException {
        return safeRead(bytes, typeReference, OBJECT_MAPPER);
    }

    /**
     * 字节数组转换成Object，支持自定义泛型，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Animal&lt;Monkey&gt; animal = JsonUtil.safeRead(bytes, new TypeReference&lt;&gt;(){}, objectMapper);<code/></pre>
     *
     * @param bytes
     * @param typeReference
     * @param objectMapper
     * @return
     * @throws IOException
     */
    public static <T> T safeRead(byte[] bytes, TypeReference<T> typeReference, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(bytes, typeReference);
    }

    /**
     * 输入流转换成Object，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Person person = JsonUtil.safeRead(inputStream, Person.class);<code/></pre>
     *
     * @param inputStream
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T safeRead(InputStream inputStream, Class<T> clazz) throws IOException {
        return safeRead(inputStream, clazz, OBJECT_MAPPER);
    }

    /**
     * 输入流转换成Object，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Person person = JsonUtil.safeRead(inputStream, Person.class, objectMapper);<code/></pre>
     *
     * @param inputStream
     * @param clazz
     * @param objectMapper
     * @return
     * @throws IOException
     */
    public static <T> T safeRead(InputStream inputStream, Class<T> clazz, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(inputStream, clazz);
    }

    /**
     * 输入流转换成Object，支持自定义泛型，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Animal&lt;Monkey&gt; animal = JsonUtil.safeRead(inputStream, new TypeReference&lt;&gt;(){});<code/></pre>
     *
     * @param inputStream
     * @param typeReference
     * @return
     * @throws IOException
     */
    public static <T> T safeRead(InputStream inputStream, TypeReference<T> typeReference) throws IOException {
        return safeRead(inputStream, typeReference, OBJECT_MAPPER);
    }

    /**
     * 输入流转换成Object，支持自定义泛型，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Animal&lt;Monkey&gt; animal = JsonUtil.safeRead(inputStream, new TypeReference&lt;&gt;(){}, objectMapper);<code/></pre>
     *
     * @param inputStream
     * @param typeReference
     * @param objectMapper
     * @return
     * @throws IOException
     */
    public static <T> T safeRead(InputStream inputStream, TypeReference<T> typeReference, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(inputStream, typeReference);
    }

    /**
     * json转换成Map，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Map&lt;String, Object&gt; map = JsonUtil.safeToMap(json, String.class, Object.class);<code/></pre>
     *
     * @param json
     * @param keyType
     * @param valueType
     * @return
     * @throws JsonProcessingException
     */
    public static <K,V> Map<K, V> safeReadMap(String json, Class<K> keyType, Class<V> valueType) throws JsonProcessingException {
        return safeReadMap(json, keyType, valueType, OBJECT_MAPPER);
    }

    /**
     * json转换成Map，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Map&lt;String, Object&gt; map = JsonUtil.safeToMap(json, String.class, Object.class, objectMapper);<code/></pre>
     *
     * @param json
     * @param keyType
     * @param valueType
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static <K,V> Map<K, V> safeReadMap(String json, Class<K> keyType, Class<V> valueType, ObjectMapper objectMapper) throws JsonProcessingException {
        if (StringUtils.isBlank(json)) {
            throw new JsonHandleException("json string is blank, cannot be converted to map");
        }
        return objectMapper.readValue(json, TypeFactory.defaultInstance().constructMapType(Map.class, keyType, valueType));
    }

    /**
     * json转换成List，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>List&lt;String&gt; list = JsonUtil.safeToList(json, String.class);<code/></pre>
     *
     * @param json
     * @param valueType
     * @return
     * @throws JsonProcessingException
     */
    public static <T> List<T> safeReadList(String json, Class<T> valueType) throws JsonProcessingException {
        return safeReadList(json, valueType, OBJECT_MAPPER);
    }

    /**
     * json转换成List，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>List&lt;String&gt; list = JsonUtil.safeToList(json, String.class, objectMapper);<code/></pre>
     *
     * @param json
     * @param valueType
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static <T> List<T> safeReadList(String json, Class<T> valueType, ObjectMapper objectMapper) throws JsonProcessingException {
        if (StringUtils.isBlank(json)) {
            throw new JsonHandleException("json string is blank, cannot be converted to list");
        }
        return objectMapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, valueType));
    }

    /**
     * json转换成Set，自动去重，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Set&lt;String&gt; set = JsonUtil.safeReadSet(json, String.class);<code/></pre>
     *
     * @param json
     * @param valueType
     * @return
     * @throws JsonProcessingException
     */
    public static <T> Set<T> safeReadSet(String json, Class<T> valueType) throws JsonProcessingException {
        return safeReadSet(json, valueType, OBJECT_MAPPER);
    }

    /**
     * json转换成Set，自动去重，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Set&lt;String&gt; set = JsonUtil.safeReadSet(json, String.class, objectMapper);<code/></pre>
     *
     * @param json
     * @param valueType
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static <T> Set<T> safeReadSet(String json, Class<T> valueType, ObjectMapper objectMapper) throws JsonProcessingException {
        if (StringUtils.isBlank(json)) {
            throw new JsonHandleException("json string is blank, cannot be converted to set");
        }
        return objectMapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(Set.class, valueType));
    }

    /**
     * json转换成Collection，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Set&lt;String&gt; set = JsonUtil.safeToCollection(json, String.class);<code/></pre>
     *
     * @param json
     * @param collectionType
     * @param valueType
     * @return
     * @throws JsonProcessingException
     */
    public static <T extends Collection, V> T safeReadCollection(String json, Class<T> collectionType, Class<V> valueType) throws JsonProcessingException {
        return safeReadCollection(json, collectionType, valueType, OBJECT_MAPPER);
    }

    /**
     * json转换成Collection，严谨的，建议在业务逻辑中使用
     * <pre>使用方法:<code>Set&lt;String&gt; set = JsonUtil.safeToCollection(json, String.class, objectMapper);<code/></pre>
     *
     * @param json
     * @param collectionType
     * @param valueType
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public static <T extends Collection, V> T safeReadCollection(String json, Class<T> collectionType, Class<V> valueType, ObjectMapper objectMapper) throws JsonProcessingException {
        if (StringUtils.isBlank(json)) {
            throw new JsonHandleException("json string is blank, cannot be converted to collection");
        }
        return objectMapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(collectionType, valueType));
    }

}
