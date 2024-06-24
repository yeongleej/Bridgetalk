package com.ssafy.bridgetalkback.letters.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.exception.GlobalErrorCode;
import com.ssafy.bridgetalkback.letters.exception.LettersErrorCode;
import com.ssafy.bridgetalkback.parents.exception.ParentsErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.transcribe.TranscribeClient;
import software.amazon.awssdk.services.transcribe.model.*;

import java.util.UUID;

@Service
@Transactional
@Slf4j
public class LettersTranscribeService {
//    private static final Region REGION = Region.US_WEST_2;
    private static final Region REGION = Region.AP_NORTHEAST_2;
    private static TranscribeClient client;

    public String transcribe(String bucketName, String fileName, Language language) {
        log.info("{ LettersTranscriptionService } => bucketName: {}, fileName: {}", bucketName, fileName);
        client = TranscribeClient.builder()
                .credentialsProvider(getCredentials())
                .region(REGION)
                .build();
        log.info(">> client 생성");

        String transcriptionJobName = "bridge-talk-transcription-job"+ "-"+ UUID.randomUUID();
//        String mediaType = "flac"; // can be other types
        String mediaType = "mp3"; // can be other types
        String mediaUri = "s3://" + bucketName + "/" + fileName;  // "s3://DOC-EXAMPLE-BUCKET/my-input-files/my-media-file.mp3"
        Media myMedia = Media.builder()
                .mediaFileUri(mediaUri)
                .build();

//        String outputS3BucketName = "s3://" + bucketName;
        log.info(">> {} / {}", transcriptionJobName, mediaUri);
        // Create the transcription job request
        StartTranscriptionJobRequest request = null;
        if (language.equals(Language.viet)){
            request = StartTranscriptionJobRequest.builder()
                    .transcriptionJobName(transcriptionJobName)
//                .languageCode(LanguageCode.EN_US.toString())
                    .languageCode(LanguageCode.VI_VN)
                    .mediaFormat(mediaType)
                    .media(myMedia)
                    .outputBucketName(bucketName)
                    .build();

        } else{
            request = StartTranscriptionJobRequest.builder()
                    .transcriptionJobName(transcriptionJobName)
//                .languageCode(LanguageCode.EN_US.toString())
                    .languageCode("tl-PH")
                    .mediaFormat(mediaType)
                    .media(myMedia)
                    .outputBucketName(bucketName)
                    .build();
        }

        log.info(">> Create the transciption job request : {}", request);

        // send the request to start the transcription job
        StartTranscriptionJobResponse startJobResponse = null;
        try {
            startJobResponse = client.startTranscriptionJob(request);
        }catch (BadRequestException be){
            log.error(be.getMessage());
            throw BaseException.type(LettersErrorCode.LETTERS_NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw BaseException.type(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }

        log.info(">> transcription job 생성: {}", startJobResponse.transcriptionJob());
//        System.out.println("Created the transcription job");
//        System.out.println(startJobResponse.transcriptionJob());

        // Create the get job request
        GetTranscriptionJobRequest getJobRequest = GetTranscriptionJobRequest.builder()
                .transcriptionJobName(transcriptionJobName)
                .build();

        // send the request to get the transcription job including the job status
        String resultJobName = "";
        try {
            GetTranscriptionJobResponse getJobResponse = client.getTranscriptionJob(getJobRequest);
            while(true) {
                getJobResponse = client.getTranscriptionJob(getJobRequest);
                log.info(">> transcription job request 요청 결과 (response): {}", getJobResponse.transcriptionJob());
                log.info(">> t_status : {}", getJobResponse.transcriptionJob().transcriptionJobStatus());
                if(getJobResponse.transcriptionJob().transcriptionJobStatus().equals(TranscriptionJobStatus.COMPLETED) || getJobResponse.transcriptionJob().transcriptionJobStatus().equals(TranscriptionJobStatus.FAILED)) {
                    break;
                }
            }
            log.info(">> TranscriptionJobName : {}", getJobResponse.transcriptionJob().transcriptionJobName());
            log.info(">> Result Transcription : {}", getJobResponse.transcriptionJob().transcript());
            resultJobName = getJobResponse.transcriptionJob().transcriptionJobName();
        }catch (Exception e) {
            log.error(e.getMessage());
            throw BaseException.type(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }

//        System.out.println("Get the transcription job request");
//        System.out.println(getJobResponse.transcriptionJob());
        return resultJobName;
    }

    private static AwsCredentialsProvider getCredentials() {
        return DefaultCredentialsProvider.create();
    }

}