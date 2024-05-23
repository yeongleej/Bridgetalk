import { Dispatch, MutableRefObject, SetStateAction } from 'react';

export function handleStartRecordAudio(
  streamRef: MutableRefObject<MediaStream | undefined>,
  voiceRecorderRef: MutableRefObject<MediaRecorder | undefined>,
  setIsRecording: Dispatch<SetStateAction<boolean>>,
  setAudioURL: Dispatch<SetStateAction<string>>,
) {
  if (streamRef.current) {
    voiceRecorderRef.current = new MediaRecorder(streamRef.current);

    const voiceChunk: Blob[] = [];
    voiceRecorderRef.current.ondataavailable = (e) => {
      voiceChunk.push(e.data);
    };

    voiceRecorderRef.current.onstop = () => {
      const blob = new Blob(voiceChunk, { type: 'audio/webm' });
      const audioURL = URL.createObjectURL(blob);
      setAudioURL(audioURL);

      voiceChunk.splice(0, voiceChunk.length);
    };

    voiceRecorderRef.current.start();
    setIsRecording(true);
  }
}
