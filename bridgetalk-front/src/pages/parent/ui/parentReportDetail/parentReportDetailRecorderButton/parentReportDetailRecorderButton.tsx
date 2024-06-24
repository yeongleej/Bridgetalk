import { Dispatch, SetStateAction, useMemo } from 'react';
import * as S from '@/styles/parent/parentReportDetailRecorder.style';
import { FaMicrophone } from 'react-icons/fa';
import { useReportStore, useVoiceStore } from '@/pages/parent/store';

interface Props {
  readonly isRecording: boolean;
  readonly setIsRecording: Dispatch<SetStateAction<boolean>>;
}

export function ParentReportDetailRecorderButton({ isRecording, setIsRecording }: Props) {
  const setIsRecordFinished = useVoiceStore((state) => state.setIsRecordFinished);
  const language = useReportStore((state) => state.language);

  const buttonWord = useMemo(
    () => ({
      kor: !isRecording ? '녹음하기' : '종료하기',
      viet: !isRecording ? 'Đang ghi' : 'Trả lời',
      ph: !isRecording ? 'Mag-record' : 'Itigil ang pagre-record',
    }),
    [isRecording],
  );
  return (
    <S.ButtonWrapper
      $isRecording={isRecording}
      onClick={() => {
        if (isRecording) {
          setIsRecording(false);
          setTimeout(() => {
            setIsRecordFinished(true);
          }, 250);
        } else {
          setIsRecording(true);
        }
      }}
    >
      <div>
        <FaMicrophone />
      </div>
      <div style={{ fontFamily: language === 'kor' ? 'DNF' : 'CherryBomb' }}>{buttonWord[language]}</div>
    </S.ButtonWrapper>
  );
}
