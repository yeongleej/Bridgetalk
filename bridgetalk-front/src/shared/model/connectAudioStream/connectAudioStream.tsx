import { MutableRefObject } from 'react';

export async function connectAudioStream(streamRef: MutableRefObject<MediaStream | null>) {
  try {
    const constraints = {
      audio: true,
    };
    const stream = await navigator.mediaDevices.getUserMedia(constraints);

    streamRef.current = stream;

    return stream;
  } catch (err) {
    throw err;
  }
}
