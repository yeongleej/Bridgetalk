import { FFmpeg } from '@ffmpeg/ffmpeg';
import { MutableRefObject } from 'react';

export function startRecordVoice(
  streamRef: MutableRefObject<MediaStream | null>,
  recorderRef: MutableRefObject<MediaRecorder | null>,
  audioDataRef: any,
) {
  if (streamRef.current) {
    recorderRef.current = new MediaRecorder(streamRef.current);

    const voiceChunk: Blob[] = [];
    recorderRef.current.ondataavailable = (e: BlobEvent) => {
      voiceChunk.push(e.data);
    };

    recorderRef.current.onstop = () => {
      const audioBlob: Blob = new Blob(voiceChunk, { type: 'audio/mp3' });

      voiceChunk.splice(0, voiceChunk.length);

      const ffmpeg = new FFmpeg();

      audioDataRef.current = audioBlob;
    };

    recorderRef.current.start();
  }
}
