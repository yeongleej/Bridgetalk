package com.ssafy.bridgetalkback.reports.service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.reports.dto.response.VideoResponseDto;
import com.ssafy.bridgetalkback.reports.exception.ReportsErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportsVideoService {

    @Value("${YOUTUBE_VIDEO_SECRET}")
    private String key;
    @Value("${YOUTUBE_CHANNEL_ID}")
    private String channelId;


    public List<VideoResponseDto> searchVideo(List<String> reportsKeywordsKor) {
        log.info("{ ReportsVideoService } : 레포트 유튜브리스트 진입");
        String keywords = StringUtils.join(reportsKeywordsKor, "+");
        JsonFactory jsonFactory = new JacksonFactory();

        try {
            YouTube youTube = new YouTube.Builder(
                    new NetHttpTransport(),
                    jsonFactory,
                    request -> {
                    })
                    .build();
            YouTube.Search.List search = youTube.search().list(Collections.singletonList("id,snippet"));
            search.setKey(key);
            search.setType(Collections.singletonList("video"));
            search.setMaxResults(10L);
            search.setQ(keywords);
            search.setChannelId(channelId);
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            List<VideoResponseDto> reportsVideoList = new ArrayList<>();
            if (searchResultList != null && searchResultList.size() > 0) {
                for (SearchResult searchResult : searchResultList) {
                    String videoId = searchResult.getId().getVideoId();
                    String url = "https://www.youtube.com/watch?v=" + videoId;
                    String title = searchResult.getSnippet().getTitle();
                    VideoResponseDto videoResponseDto = new VideoResponseDto(videoId, url, title);
                    reportsVideoList.add(videoResponseDto);
                }
            }
            log.info(">> 유튜브리스트 확인 {}", reportsVideoList);
            return reportsVideoList;
        } catch (IOException e) {
            log.error("유튜브 video api 호출 실패: {}", e.getMessage());
            throw BaseException.type(ReportsErrorCode.YOUTUBE_API_FAILED);
        }
    }
}
