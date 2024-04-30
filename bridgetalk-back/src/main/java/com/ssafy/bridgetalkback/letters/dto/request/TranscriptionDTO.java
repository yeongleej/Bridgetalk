package com.ssafy.bridgetalkback.letters.dto.request;

import software.amazon.awssdk.services.transcribe.model.TranscriptionJobStatus;

import java.util.Map;

public record TranscriptionDTO (
        String jobName,
        Long accountId,
        TranscriptionJobStatus status,
        Map<String, Object> results
) {}
