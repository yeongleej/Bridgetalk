import { Dispatch, MutableRefObject, SetStateAction } from 'react';

export const startRecordVideo = async (
  recordRef: MutableRefObject<MediaRecorder | undefined>,
  streamRef: MutableRefObject<MediaStream | undefined>,
  setRecordBlob: Dispatch<SetStateAction<Blob[] | undefined>>,
  setIsRecording: Dispatch<SetStateAction<boolean>>,
) => {
  setRecordBlob([]);
  setIsRecording(true);

  try {
    recordRef.current = new MediaRecorder(streamRef.current!);

    recordRef.current.ondataavailable = (e: any) => {
      if (e.data && e.data.size > 0) {
        setRecordBlob((prev) => [...prev!, e.data]);
      }
    };

    recordRef.current.start();
  } catch (err) {
    // console.log('녹화에 실패했습니다.', err);
  }
};
