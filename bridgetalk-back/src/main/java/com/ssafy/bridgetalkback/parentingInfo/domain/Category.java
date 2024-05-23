package com.ssafy.bridgetalkback.parentingInfo.domain;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.utils.EnumConverter;
import com.ssafy.bridgetalkback.global.utils.EnumStandard;
import com.ssafy.bridgetalkback.parentingInfo.exception.ParentingInfoErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Category implements EnumStandard {
    PROSPECTIVE("prospective"),
    INFANT_AND_TODDLER("infant_and_toddler"),
    SCHOOL("school"),
    PUBERTY("puberty")
    ;

    private final String category;

    @Override
    public String getValue() {
        return category;
    }

    @jakarta.persistence.Converter
    public static class CategoryConverter extends EnumConverter<Category> {
        public CategoryConverter() {
            super(Category.class);
        }
    }

    public static Category from(String value) {
        return Arrays.stream(values())
                .filter(category -> category.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> BaseException.type(ParentingInfoErrorCode.CATEGORY_NOT_FOUND));
    }
}