package com.ssafy.bridgetalkback.notification.repository;

import com.ssafy.bridgetalkback.notification.domain.Notification;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiverUuid(String receiverUuid);

    @Modifying
    @Query("update Notification set isChecked = 1 where receiverUuid = :receiverUuid")
    void bulkReadUpdate(@Param("receiverUuid") String receiverUuid);

    @Modifying
    @Query("update Notification set isDeleted = 1 where emitterId = :emitterId")
    void bulkDeletedUpdate(String emitterId);

    Notification findByEmitterId(String emitterId);

    List<Notification> findAllByReceiverUuidAndIsDeleted(String receiverUuid, int isDeleted);
}