package com.ssafy.bridgetalkback.global.config;

import com.ssafy.bridgetalkback.auth.utils.JwtProvider;
import com.ssafy.bridgetalkback.global.annotation.ExtractPayloadArgumentResolver;
import com.ssafy.bridgetalkback.global.annotation.ExtractTokenArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final JwtProvider jwtProvider;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "DENY")
                .maxAge(3600);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ExtractTokenArgumentResolver(jwtProvider));
        resolvers.add(new ExtractPayloadArgumentResolver(jwtProvider));
    }

}

