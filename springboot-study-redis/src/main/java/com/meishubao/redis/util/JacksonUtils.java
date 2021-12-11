package com.meishubao.redis.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class JacksonUtils {
    private final static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        // 设置时区
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 设置时间格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        /** 序列化配置,针对java8 时间 **/
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        /** 反序列化配置,针对java8 时间 **/
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        SimpleModule module = new SimpleModule("CustomSerializer", new Version(1, 0, 0, "", "", ""));
        module.addSerializer(Double.class, new DoubleSerializer());
        module.addSerializer(double.class, new DoubleSerializer());
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(long.class, ToStringSerializer.instance);

        /** 注册模块 **/
        OBJECT_MAPPER.registerModule(javaTimeModule)
                .registerModule(module)
                .registerModule(new Jdk8Module())
                .registerModule(new ParameterNamesModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JacksonUtils() {
    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * Object 转换成json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return toJson(object, OBJECT_MAPPER);
    }

    /**
     * Object 转换成json字符串
     *
     * @param object
     * @param objectMapper
     * @return
     */
    public static String toJson(Object object, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Object转换Json异常", e);
            throw new RuntimeException("Object转换Json异常", e);
        }
    }

    /**
     * 对象 转换为json字符串,忽略空值
     *
     * @param object
     * @return
     */
    public static String toJsonIgnoreNull(Object object) {
        return toJsonIgnoreNull(object, new ObjectMapper());
    }

    /**
     * 对象 转换为json字符串,忽略空值
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
     * json 转 对象
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        return jsonToObject(jsonString, clazz, OBJECT_MAPPER);
    }

    /**
     * Json 转 对象
     *
     * @param jsonString
     * @param clazz
     * @param objectMapper
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String jsonString, Class<T> clazz, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            log.error("Json转换对象异常", e);
            throw new RuntimeException("Json转换对象异常", e);
        }
    }

    /**
     * json字符串转换为map
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> jsonTomap(String jsonString) {
        return jsonTomap(jsonString, OBJECT_MAPPER);
    }

    /**
     * json字符串转换为map
     *
     * @param jsonString
     * @param objectMapper
     * @return
     */
    public static Map<String, Object> jsonTomap(String jsonString, ObjectMapper objectMapper) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return jsonToObject(jsonString, Map.class, objectMapper);
    }

    /**
     * 深度转换json成list
     *
     * @param json
     * @return
     */
    public static List<Object> jsonToListDeeply(String json) {
        return jsonToListDeeply(json, OBJECT_MAPPER);
    }

    /**
     * 把json解析成list，如果list内部的元素存在jsonString，继续解析
     *
     * @param json
     * @param mapper 解析工具
     * @return
     */
    public static List<Object> jsonToListDeeply(String json, ObjectMapper mapper) {
        if (json == null) {
            return null;
        }
        try {
            List<Object> list = mapper.readValue(json, List.class);
            for (Object obj : list) {
                if (obj != null && obj instanceof String) {
                    String str = (String) obj;
                    if (str.startsWith("[")) {
                        obj = jsonToListDeeply(str, mapper);
                    } else if (obj.toString().startsWith("{")) {
                        obj = jsonToMapDeeply(str, mapper);
                    }
                }
            }
            return list;
        } catch (JsonProcessingException e) {
            log.error("Json转换list失败", e);
            throw new RuntimeException("Json转换list失败", e);
        }

    }

    /**
     * 深度转换json成map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMapDeeply(String json) {
        return jsonToMapDeeply(json, OBJECT_MAPPER);
    }

    /**
     * 深度转换把json解析成map，
     * 如果map内部的value存在jsonString，继续解析
     *
     * @param json
     * @param mapper
     * @return
     */
    public static Map<String, Object> jsonToMapDeeply(String json, ObjectMapper mapper) {
        if (json == null) {
            return null;
        }
        try {
            Map<String, Object> map = mapper.readValue(json, Map.class);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object object = entry.getValue();
                if (object != null && object instanceof String) {
                    String str = ((String) object);
                    if (str.startsWith("[")) {
                        List<?> list = jsonToListDeeply(str, mapper);
                        map.put(entry.getKey(), list);
                    } else if (str.startsWith("{")) {
                        Map<String, Object> mapRecursion = jsonToMapDeeply(str, mapper);
                        map.put(entry.getKey(), mapRecursion);
                    }
                }
            }
            return map;
        } catch (JsonProcessingException e) {
            log.error("Json转换map失败", e);
            throw new RuntimeException("Json转换map失败", e);
        }
    }

    /**
     * json数组字符串转换为列表
     *
     * @param jsonArrayStr
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> jsonToList(String jsonArrayStr) throws Exception {
        List<T> lst = (List<T>) OBJECT_MAPPER.readValue(jsonArrayStr, List.class);
        return lst;
    }

    /**
     * json数组字符串转换为列表
     *
     * @param jsonArrayStr
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> jsonToList(String jsonArrayStr, TypeReference<List<T>> typeReference) throws Exception {
        List<T> lst = OBJECT_MAPPER.readValue(jsonArrayStr, typeReference);
        return lst;
    }


    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * Object转JavaBean
     *
     * @param object
     * @param clazz
     * @return
     */
    public static <T> T toPojo(Object object, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(object, clazz);
    }

}
