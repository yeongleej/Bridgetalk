package com.ssafy.bridgetalkback.notification.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.notification.domain.NotificationType;
import com.ssafy.bridgetalkback.notification.dto.request.NotificationRequestDto;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sse [Service Layer] -> Sse 테스트")
class SseServiceTest extends ServiceTest {

    private Parents parents;
    private Kids kids;

    @Autowired
    private SseService sseService;

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
    }

    @Test
    @DisplayName("알림 구독을 진행한다.")
    public void subscribe() throws Exception {
        //given
        String receiverUuid = kids.getUuid().toString();
        String lastEventId = "";

        // when, then
        Assertions.assertDoesNotThrow(() -> sseService.subscribe(receiverUuid, lastEventId));

    }

    @Test
    @DisplayName("알림 메시지를 보낸다.")
    public void send() throws Exception {
        // given
        String receiverUuid = kids.getUuid().toString();
        String lastEventId = "";
        sseService.subscribe(receiverUuid, lastEventId);
        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                .receiverUuid(receiverUuid)
                .content("send to message test")
                .url("https://bridgetalk.co.kr")
                .notificationType(NotificationType.KID_REPORTS_REGISTER)
                .build();

        // when, then
        Assertions.assertDoesNotThrow(() -> sseService.send(notificationRequestDto));
    }


}