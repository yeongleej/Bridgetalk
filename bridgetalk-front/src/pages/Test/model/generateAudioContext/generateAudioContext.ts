import { MutableRefObject } from 'react';

export function generateAudioContex(streamRef: MutableRefObject<MediaStream | undefined>) {
    if (streamRef.current) {
        const audioContext = new AudioContext();
        const analyser: AnalyserNode = audioContext.createAnalyser();
        const microphone = audioContext.createMediaStreamSource(streamRef.current);
        microphone.connect(analyser);
        analyser.fftSize = 256;

        const bufferLength = analyser.frequencyBinCount;
        const dataArray: Uint8Array = new Uint8Array(bufferLength);

        if (analyser instanceof AnalyserNode && typeof bufferLength === 'number' && dataArray instanceof Uint8Array) {
            return [analyser, bufferLength, dataArray];
        }
    }
}
