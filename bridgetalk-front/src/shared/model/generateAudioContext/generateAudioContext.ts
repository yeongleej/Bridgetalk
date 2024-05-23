import { MutableRefObject } from 'react';

interface AudioContext {
  analyser: AnalyserNode;
  bufferLength: number;
  dataArray: Uint8Array;
}

export function generateAudioContext(streamRef: MutableRefObject<MediaStream | null>): AudioContext | undefined {
  if (streamRef.current) {
    const audioContext = new AudioContext();
    const analyser: AnalyserNode = audioContext.createAnalyser();
    const microphone = audioContext.createMediaStreamSource(streamRef.current);
    microphone.connect(analyser);
    analyser.fftSize = 256;

    const bufferLength = analyser.frequencyBinCount;
    const dataArray: Uint8Array = new Uint8Array(bufferLength);

    if (analyser instanceof AnalyserNode && typeof bufferLength === 'number' && dataArray instanceof Uint8Array) {
      return { analyser, bufferLength, dataArray };
    }
  }
}
