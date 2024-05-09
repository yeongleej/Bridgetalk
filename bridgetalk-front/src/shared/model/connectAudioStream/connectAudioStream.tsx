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
    console.log('녹음 장치 액세스에 실패했습니다.', err);

    return err;
  }
}
