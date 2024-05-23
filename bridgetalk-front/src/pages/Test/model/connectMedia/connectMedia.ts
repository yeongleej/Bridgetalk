import { MutableRefObject, RefObject } from 'react';

export async function connectMedia(
  videoRef: RefObject<HTMLVideoElement>,
  streamRef: MutableRefObject<MediaStream | undefined>,
) {
  try {
    const constraints = {
      video: true,
      // audio: true,
    };

    const stream = await navigator.mediaDevices.getUserMedia(constraints);

    streamRef.current = stream;
    videoRef.current!.srcObject = stream;

    // console.log(stream.getTracks());
  } catch (err) {
    // console.log('미디어에 액세스에 실패했습니다.', err);
  }
}
