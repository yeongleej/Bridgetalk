package com.ssafy.bridgetalkback.global.security;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.auth.utils.JwtProvider;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.service.ParentsFindService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final ParentsFindService parentsFindService;
    private final KidsFindService kidsFindService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = jwtProvider.resolveToken(request);
        log.info("{ JwtAuthenticationFilter } : authorizationHeader - "+authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtProvider.validateToken(token)) {
                log.info("{ JwtAuthenticationFilter } : 유효한 토큰임을 확인");

                UUID userId = UUID.fromString(jwtProvider.getId(token));
                log.info("{ JwtAuthenticationFilter } : userId - "+userId);
                UserDetailDto userDetailDto = null;

                // userId를 가진 회원이 없다면 404 발생
                if(parentsFindService.existsParentsByUuidAndIsDeleted(userId)){
                    Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(userId);
                    userDetailDto = new UserDetailDto(parents.getUuid(), parents.getParentsEmail().getValue(), parents.getParentsName(), parents.getRole().getValue());
                }
                else if(kidsFindService.existsKidsByUuidAndIsDeleted(userId)) {
                    Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
                    userDetailDto = new UserDetailDto(kids.getUuid(), kids.getKidsEmail(), kids.getKidsName(), kids.getRole().getValue());
                }
                else {
                    log.info("{ JwtAuthenticationFilter } : 토큰에 추출된 uuid를 가진 회원 없음");
                    throw BaseException.type(AuthErrorCode.USER_NOT_FOUND);
                }

                CustomUserDetails customUserDetails = new CustomUserDetails(userDetailDto);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails, "", customUserDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                log.info("{ JwtAuthenticationFilter } : 유효하지 않은 토큰");
                throw BaseException.type(AuthErrorCode.AUTH_INVALID_TOKEN);
            }
        }

        filterChain.doFilter(request, response);
    }
}
