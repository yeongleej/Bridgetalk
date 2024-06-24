import { getAvgVolume } from '@/pages/child/model';
import { handleTalkSend } from '@/pages/child/model/handleTalkSend/handleTalkSend';
import { handleTalkStart } from '@/pages/child/model/handleTalkStart/handleTalkStart';
import { getTalkStart } from '@/pages/child/query';
import { useTalkStore } from '@/pages/child/store';
import { useVoiceStore } from '@/pages/parent';
import {
  connectAudioStream,
  errorCatch,
  generateAudioContext,
  generateVolumeCheckInterval,
  startRecordVoice,
  stopRecordVoice,
} from '@/shared';
import { useErrorStore } from '@/shared/store';
import { MutableRefObject, useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export function TalkingComponents({ reply, setReply, devounceTimerRef }: any) {
  const navigate = useNavigate();

  // Global State
  const volume = useVoiceStore((state) => state.volume);
  const setVolume = useVoiceStore((state) => state.setVolume);
  const setAudioBlob = useVoiceStore((state) => state.setAudioBlob);
  const audioBlob = useVoiceStore((state) => state.audioBlob);
  const {
    reportsId,
    setReportsId,
    isRecording,
    setIsRecording,
    isSend,
    setIsSend,
    isTalking,
    setIsTalking,
    isWaiting,
    setIsWaiting,
    emotion,
    setEmotion,
    subtitle,
    setSubtitle,
    setIsEnd,
  } = useTalkStore((state) => ({
    reportsId: state.reportsId,
    setReportsId: state.setReportsId,
    isRecording: state.isRecording,
    setIsRecording: state.setIsRecording,
    isSend: state.isSend,
    setIsSend: state.setIsSend,
    isTalking: state.isTalking,
    setIsTalking: state.setIsTalking,
    isWaiting: state.isWaiting,
    setIsWaiting: state.setIsWaiting,
    emotion: state.emotion,
    setEmotion: state.setEmotion,
    subtitle: state.subtitle,
    setSubtitle: state.setSubtitle,
    setIsEnd: state.setIsEnd,
  }));
  const errorStore = useErrorStore();
  const talkStore = useTalkStore();
  const setIsStart = useTalkStore((state) => state.setIsStart);

  // Ref
  const audioDataRef = useRef<Blob | null>(null);
  const getAvgVolumeData = useRef<any>(null);
  const audioRef = useRef<HTMLAudioElement>(null);

  // 녹음 관련
  const streamRef: MutableRefObject<MediaStream | null> = useRef(null);
  const recorderRef: MutableRefObject<MediaRecorder | null> = useRef(null);

  // 화면 접속 시 초기화
  useEffect(() => {
    talkStore.resetStore();
  }, []);

  // 볼륨 체크
  useEffect(() => {
    if (isRecording && getAvgVolumeData.current) {
      //console.log('볼륨:', volume, '평균 볼륨:', getAvgVolumeData.current(volume));
      if (volume >= Math.floor(getAvgVolumeData.current(volume) * 0.8)) {
        // 기존 타이머 제거 후 새 타이머 생성
        if (devounceTimerRef.current) {
          clearTimeout(devounceTimerRef.current);
        }
        devounceTimerRef.current = setTimeout(() => {
          //console.log('타이머 작동');
          setIsSend(true);
          setIsRecording(false);
        }, 1000);
      }
    }
  }, [volume]);

  // 오디오 스트림 연결 및 해제
  useEffect(() => {
    // 마이크 연결 및 녹음 시작
    if (!streamRef.current && isTalking) {
      connectAudioStream(streamRef)
        .then((res) => {
          if (res instanceof MediaStream) {
            //console.log('{ 마이크 연결 }');
            handleTalkStart(setReply, setEmotion, setSubtitle, errorStore.setErrorModalState).catch((err) => {
              if (err instanceof Error) {
                errorCatch(err, errorStore.setErrorModalState);
                setIsRecording(true); // 이미 시작된 대화가 있는 경우에만 시작
              }
            });
          }
        })
        .catch((err) => {
          errorCatch(err, errorStore.setErrorModalState);
          setIsRecording(false);
          setIsTalking(false);
          setIsEnd(true);
          setTimeout(() => {
            setIsEnd(false);
          }, 0);
        });
    }

    // 마이크 연결 끊기 및 녹음 종료
    if (streamRef.current && !isTalking) {
      //console.log('{ 마이크 연결 끊기 }');

      streamRef.current.getTracks().forEach((track) => {
        track.stop();
      });

      streamRef.current = null;
      setIsRecording(false);
    }

    return () => {
      if (streamRef.current) {
        streamRef.current.getTracks().forEach((track) => {
          track.stop();
        });

        streamRef.current = null;
        setIsRecording(false);
      }
    };
  }, [isTalking]);

  // 녹음
  useEffect(() => {
    let volumeCheckInterval: any = null;

    if (isRecording && !volumeCheckInterval) {
      // 음량 체크
      if (isTalking) {
        const { analyser, bufferLength, dataArray }: any = generateAudioContext(streamRef)!;
        volumeCheckInterval = generateVolumeCheckInterval(analyser, dataArray, bufferLength, setVolume);
      }

      // 녹음 시작
      //console.log('{ 녹음 시작 }');
      startRecordVoice(streamRef, recorderRef, audioDataRef);
    }

    // if (!isRecording && isTalking) {
    if (!isRecording) {
      //console.log('{ 볼륨 측정 함수 재선언 }');
      getAvgVolumeData.current = getAvgVolume();
    }

    return () => {
      // 음량 체크 및 녹음 종료
      if (isRecording && volumeCheckInterval) {
        //console.log(' { 녹음 종료 }');
        clearInterval(volumeCheckInterval);
        stopRecordVoice(recorderRef);
        setVolume(0);
      }
    };
  }, [isRecording]);

  // 한마디 전송 시
  useEffect(() => {
    if (isSend) {
      setTimeout(() => {
        setAudioBlob(audioDataRef.current!);
      }, 0);
    }
  }, [isSend]);

  // audioBlob(내 녹음 내용) 저장 후 '한 마디 전송' API 요청
  useEffect(() => {
    if (audioBlob && isSend && isTalking) {
      setIsWaiting(true);

      handleTalkSend(
        audioBlob,
        setReply,
        setEmotion,
        setSubtitle,
        errorStore.setErrorModalState,
        setIsRecording,
      ).finally(() => {
        setIsSend(false);
        setIsWaiting(false);

        // /** 한마디 전송 후 임의대로 5초간 녹음 멈추게 하기 위해 */
        // setTimeout(() => {
        //   setIsRecording(true);
        // }, 5000);
      });

      // postSendTalk(reportsId, audioBlob, setReply).finally(() => {
      //   setIsSend(false);
      //   setIsWaiting(false);

      //   setTimeout(() => {
      //     setIsRecording(true);
      //   }, 1000);
      // });
    }
  }, [audioBlob]);

  useEffect(() => {
    //console.log('{reply 감지', reply);
    if (audioRef.current && isTalking) {
      audioRef.current.play();
    }
  }, [reply]);

  return (
    <>
      <div className="record" style={{ position: 'fixed', top: 0, opacity: 0 }}>
        <button
          onClick={() => {
            setIsSend(true);
            setIsRecording(false);
          }}
        >
          한 마디 전송하기
        </button>
      </div>

      <audio
        ref={audioRef}
        src={reply}
        hidden
        autoPlay
        onPlay={() => {
          setIsRecording(false);
        }}
        onEnded={() => {
          if (!talkStore.isTalking) {
            talkStore.setIsEnd(true);
            setTimeout(() => {
              navigate('/child');
            }, 300);

            return;
          }

          if (talkStore.isEnd) {
            //console.log('오디오 및 대화 종료');
            talkStore.resetStore();
            setTimeout(() => {
              navigate('/child');
            }, 300);
            return;
          }

          if (!talkStore.isEnd) {
            setIsRecording(true);
            return;
          }
        }}
      />
    </>
  );
}
