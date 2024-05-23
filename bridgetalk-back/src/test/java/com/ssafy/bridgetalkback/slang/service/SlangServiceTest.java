package com.ssafy.bridgetalkback.slang.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.config.CacheConfig;
import com.ssafy.bridgetalkback.slang.domain.Slang;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(CacheConfig.class)
@DisplayName("Slang [Service Layer] -> SlangService 테스트")
public class SlangServiceTest extends ServiceTest {

    @Autowired
    private SlangService slangService;

    @BeforeEach
    void setup() {

        slangRepository.save(Slang.createSlang("다다다", "originalWord3", "meaning3", "vietnamesePronunciation3", "vietnameseTranslation3"));
        slangRepository.save(Slang.createSlang("가가가", "originalWord1", "meaning1", "vietnamesePronunciation1", "vietnameseTranslation1"));
        slangRepository.save(Slang.createSlang("나나나", "originalWord2", "meaning2", "vietnamesePronunciation2", "vietnameseTranslation2"));
    }

    @Test
    @DisplayName("slang 전체 조회 캐싱처리 테스트")
    void testCaching() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "slangWord"));
        slangRepository.findAllByIsDeleted(0, pageable);

        // 첫 번째 호출: 캐시 저장
        long startTime1 = System.currentTimeMillis();
        slangService.findAllSlang(pageable);
        long duration1 = System.currentTimeMillis() - startTime1;

        // 두 번째 호출: 캐시 사용
        long startTime2 = System.currentTimeMillis();
        slangService.findAllSlang(pageable);
        long duration2 = System.currentTimeMillis() - startTime2;

        // 캐싱에 의해 두 번째 호출이 더 빠르게 처리되어야 함
//        System.out.println(duration1 + "     " + duration2);
        assertThat(duration2).isLessThan(duration1);
    }
}

