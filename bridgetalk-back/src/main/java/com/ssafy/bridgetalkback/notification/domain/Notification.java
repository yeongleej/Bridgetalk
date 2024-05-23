package com.ssafy.bridgetalkback.notification.domain;

import com.ssafy.bridgetalkback.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.N;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="notifications")
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column
    private String emitterId;

    @Column
    private String receiverUuid; //uuid

    @Column
    private String content;

    @Column
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Column(columnDefinition = "integer default 0")
    private int isChecked;

    private Notification(String receiverUuid, String emitterId, String content, String url, NotificationType notificationType) {
        this.receiverUuid = receiverUuid;
        this.emitterId = emitterId;
        this.content = content;
        this.url = url;
        this.notificationType = notificationType;
    }

    public static Notification createNotification(String receiverUuid, String emitterId, String content, String url, NotificationType notificationType){
        return new Notification(receiverUuid, emitterId, content, url, notificationType);
    }

    public void updateNotification(){
        this.isChecked = 1;
    }

}
