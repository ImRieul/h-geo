package com.example.hgeo.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new NullToEmptyStringDeserializer());

        this.objectMapper = new ObjectMapper();
        this.objectMapper.setVisibility(FIELD, ANY);
        this.objectMapper.registerModule(simpleModule);
    }

    public <T> T parse(String body, Class<T> valueType) {
        try {
            return objectMapper.readValue(body, valueType);
        } catch (JsonProcessingException e1) {
            try {
                return valueType.newInstance();
            } catch (InstantiationException | IllegalAccessException e3) {
                return null;
            }
        }
    }

    public <T> T parse(String body, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(body, valueTypeRef);
        } catch (JsonProcessingException e1) {
            try {
                return (T) valueTypeRef.getType().getClass().newInstance();
            } catch (InstantiationException | IllegalAccessException e3) {
                return null;
            }
        }
    }


    /**
     * Null 값을 빈 문자열로 변환
     */
    class NullToEmptyStringDeserializer extends StdDeserializer<String> {

        public NullToEmptyStringDeserializer() {
            super(String.class);
        }

        @Override
        public String deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext ctxt) throws IOException {
            String value = p.getValueAsString();
            return null == value || "null".equals(value) ? "" : value;
        }
    }
}