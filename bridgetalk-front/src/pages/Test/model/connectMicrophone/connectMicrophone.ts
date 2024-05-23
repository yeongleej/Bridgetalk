import { MutableRefObject } from 'react';

export async function connectMicrophone(streamRef: MutableRefObject<MediaStream | undefined>, deviceId: string) {
  if (streamRef.current) {
    streamRef.current.getTracks().forEach((track) => {
      track.stop();
    });
  }

  try {
    const constraints = {
      audio: {
        noiseSuppression: true,
        echoCancellation: true,
        volume: 20,
        deviceId: deviceId,
      },
    };

    const stream = await navigator.mediaDevices.getUserMedia(constraints);

    streamRef.current = stream;
    // console.log(stream.getAudioTracks());
  } catch (err) {
    // console.log('마이크 액세스에 실패했습니다.', err);
  }
}
