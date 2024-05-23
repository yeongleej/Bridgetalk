import * as S from '@/styles/test/testVoice.style';
import { MutableRefObject, useEffect, useRef, useState } from 'react';
import {
  connectMicrophone,
  generateAudioContex,
  getAudioFrequency,
  getAudioInputList,
  handleStartRecordAudio,
  handleStopRecordAudio,
} from '@/pages/Test/model';
import styled from 'styled-components';

export function TestVoice() {
  // streamRef: 녹음기기와의 연결을 위한 스트림
  const streamRef: MutableRefObject<MediaStream | undefined> = useRef();
  // voiceRecorderRef: 음성을 녹음할 레코더
  const voiceRecorderRef: MutableRefObject<MediaRecorder | undefined> = useRef();
  // isRecording: 녹음 진행 상태
  const [isRecording, setIsRecording] = useState(false);
  // audioURL: voiceChunk를 바탕으로 생성된
  const [audioURL, setAudioURL] = useState<string>('');
  // audioAnalyzerRef: 오디오 정보 분석기
  const audioAnalyserRef: MutableRefObject<AnalyserNode | undefined> = useRef();
  // audioBufferLengthRef: 음성 데이터 버퍼 길이
  const audioBufferLengthRef: MutableRefObject<number> = useRef(0);
  // audioDataArrayRef: 음성 데이터 배열
  const audioDataArrayRef: MutableRefObject<Uint8Array | undefined> = useRef();

  const [volumeRef, setVolumeRef] = useState(0);

  const [inputList, setInputList] = useState<any>([]);

  useEffect(() => {
    getAudioInputList('audioinput').then((res: any) => {
      setInputList(res);
    });
    // if (!streamRef.current) {
    // }

    return () => {
      if (streamRef.current) {
        streamRef.current.getTracks().forEach((track) => track.stop());
      }

      if (voiceRecorderRef.current && voiceRecorderRef.current.state === 'recording') {
        voiceRecorderRef.current.stop();
      }
    };
  }, []);

  useEffect(() => {
    let volumeInterval: any;

    if (isRecording) {
      const audioData = generateAudioContex(streamRef);

      if (
        audioData &&
        audioData[0] instanceof AnalyserNode &&
        typeof audioData[1] === 'number' &&
        audioData[2] instanceof Uint8Array
      ) {
        [audioAnalyserRef.current, audioBufferLengthRef.current, audioDataArrayRef.current] = audioData;

        volumeInterval = setInterval(() => {
          audioAnalyserRef.current?.getByteFrequencyData(audioDataArrayRef.current!);
          setVolumeRef(
            Math.floor((getAudioFrequency(audioDataArrayRef.current!, audioBufferLengthRef.current) / 256) * 100),
          );
        }, 30);
      }
    } else if (!isRecording) {
      setVolumeRef(0);
    }

    return () => {
      if (volumeInterval) {
        clearInterval(volumeInterval);
      }
    };
  }, [isRecording]);

  useEffect(() => {
    // console.log(volumeRef);
  }, [volumeRef]);

  useEffect(() => {
    // console.log(inputList);
  }, [inputList]);

  return (
    <S.Wrapper>
      <button
        onClick={() => {
          handleStartRecordAudio(streamRef, voiceRecorderRef, setIsRecording, setAudioURL);
        }}
        disabled={isRecording}
      >
        녹음 시작
      </button>
      <button
        onClick={() => {
          handleStopRecordAudio(voiceRecorderRef, setIsRecording);
        }}
        disabled={!isRecording}
      >
        녹음 중지
      </button>
      <audio src={audioURL} controls></audio>
      {inputList.length > 0 ? (
        inputList.map((it: any) => (
          <button
            onClick={() => {
              connectMicrophone(streamRef, it.deviceId);
              if (isRecording) {
                handleStopRecordAudio(voiceRecorderRef, setIsRecording);
              }
            }}
          >
            {it.label}
          </button>
        ))
      ) : (
        <div style={{ color: 'white' }}>기기데이터못찾음</div>
      )}
      {streamRef.current && <div style={{ color: 'white' }}>{streamRef.current.getAudioTracks()[0].label}</div>}
      <Bars>
        {[...Array(10)].map((no, index) => (
          <Bar key={Symbol(index).toString()} volume={volumeRef} no={index} />
        ))}
      </Bars>
    </S.Wrapper>
  );
}

const Bars = styled.div`
  width: 0.4rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  gap: 0.8rem;
  margin-left: 10rem;
`;

const Bar = styled.div<{ volume: number; no: number }>`
  width: 100%;
  height: 1.6rem;
  background-color: ${(props) => (props.volume > (10 - props.no) * 10 ? '#4ADE80' : 'rgba(255, 255, 255, 0.3)')};
  border-radius: 5rem;
`;
