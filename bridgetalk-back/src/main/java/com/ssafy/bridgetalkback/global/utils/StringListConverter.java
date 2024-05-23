package com.ssafy.bridgetalkback.global.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.reports.exception.ReportsErrorCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    // DB에 저장 될 때 사용
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (ObjectUtils.isEmpty(attribute)) return null;
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw BaseException.type(ReportsErrorCode.UNABLE_TO_CONVERT_STRING_TO_LIST);
        }
    }

    // DB의 데이터를 Object로 매핑할 때 사용
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (!StringUtils.hasText(dbData)) {
            return Collections.emptyList();
        }
        TypeReference<List<String>> typeReference = new TypeReference<List<String>>() {};
        try {
            return mapper.readValue(dbData, typeReference);
        } catch (JsonProcessingException e) {
            throw BaseException.type(ReportsErrorCode.UNABLE_TO_CONVERT_STRING_TO_LIST);
        }
    }
}
