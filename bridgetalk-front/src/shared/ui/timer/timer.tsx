import { handleTalkEnd, useTalkStore } from '@/pages';
import { useEffect, useRef, useState } from 'react';
import * as S from '@/styles/shared/timer.style';

interface Props {
  isRecording?: any;
  setIsRecording?: any;
  getTalkStop?: any;
  reportsId?: any;
  setReply?: any;
  devounceTimerRef?: any;
  setIsTalking?: any;
  navigate?: any;
  setEmotion?: any;
  setSubtitle?: any;
}

export function Timer({
  isRecording,
  setIsRecording,
  getTalkStop,
  reportsId,
  setReply,
  devounceTimerRef,
  setIsTalking,
  navigate,
  setEmotion,
  setSubtitle,
}: Props) {
  const [time, setTime] = useState<number>(0);

  const isEnd = useTalkStore((state) => state.isEnd);
  const setIsEnd = useTalkStore((state) => state.setIsEnd);
  const isTalking = useTalkStore((state) => state.isTalking);

  const timerRef = useRef<any>();

  useEffect(() => {
    if (isRecording === undefined && isTalking) {
      timerRef.current = setInterval(() => {
        setTime((prevTime) => prevTime + 1);
      }, 1000);
    } else if (isRecording !== undefined && isRecording) {
      timerRef.current = setInterval(() => {
        setTime((prevTime) => prevTime + 1);
      }, 1000);
    } else if (isTalking) {
      setTime(0);
      clearInterval(timerRef.current);
    }
    // console.log(`{ Recording 상태 변화 ${isRecording} ${isEnd}}`);
    if (isEnd) {
      // console.log('{ 타이머 종료 }');
      clearInterval(timerRef.current);
    }

    return () => {
      if (timerRef.current) {
        clearInterval(timerRef.current);
      }
    };
  }, [isRecording, isTalking]);

  useEffect(() => {
    if (isEnd) {
      clearTimeout(timerRef.current);
    }
  }, [isEnd]);

  useEffect(() => {
    if (setIsRecording !== null && time >= 60 * 4 + 40 && !isEnd) {
      handleTalkEnd(
        setReply,
        setIsTalking,
        setIsEnd,
        setIsRecording,
        isRecording,
        devounceTimerRef,
        isEnd,
        isTalking,
        navigate,
        setEmotion,
        setSubtitle,
      );
    }
  }, [time]);

  return (
    <S.ChildWrapper>
      {`${Math.floor(time / 60)}`.padStart(2, '0') + ' : ' + `${Math.floor(time % 60)}`.padStart(2, '0')}
    </S.ChildWrapper>
  );
}
