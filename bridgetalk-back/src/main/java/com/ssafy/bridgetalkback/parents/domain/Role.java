package com.ssafy.bridgetalkback.parents.domain;

import com.ssafy.bridgetalkback.global.utils.EnumConverter;
import com.ssafy.bridgetalkback.global.utils.EnumStandard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role implements EnumStandard {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER")
    ;

    private final String authority;

    @Override
    public String getValue() {
        return authority;
    }

    @jakarta.persistence.Converter
    public static class RoleConverter extends EnumConverter<Role> {
        public RoleConverter() {
            super(Role.class);
        }
    }
}

