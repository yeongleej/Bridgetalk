package com.ssafy.bridgetalkback.parentingInfo.dto.response;

import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfo;
import lombok.Builder;

@Builder
public record ParentingInfoResponseDto(
        Long parentingInfoId,
        String title,
        String content,
        String link,
        String category
) {
    public static ParentingInfoResponseDto from(ParentingInfo parentingInfo, Language language) {
        return ParentingInfoResponseDto.builder()
                .parentingInfoId(parentingInfo.getParentingInfoId())
                .title(selectTitle(parentingInfo, language))
                .content(selectContent(parentingInfo, language))
                .link(parentingInfo.getLink())
                .category(parentingInfo.getCategory().getValue())
                .build();
    }

    private static String selectTitle(ParentingInfo parentingInfo, Language language) {
        String title = null;
        switch (language) {
            case kor -> title = parentingInfo.getTitleKor();
            case viet -> title = parentingInfo.getTitleViet();
            case ph -> title = parentingInfo.getTitlePh();
        }
        return title;
    }

    private static String selectContent(ParentingInfo parentingInfo, Language language) {
        String title = null;
        switch (language) {
            case kor -> title = parentingInfo.getContentKor();
            case viet -> title = parentingInfo.getContentViet();
            case ph -> title = parentingInfo.getContentPh();
        }
        return title;
    }
}
