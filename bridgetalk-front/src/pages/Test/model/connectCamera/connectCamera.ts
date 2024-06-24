import { MutableRefObject, RefObject } from 'react';

export async function connectCamera(
  videoRef: RefObject<HTMLVideoElement>,
  streamRef: MutableRefObject<MediaStream | undefined>,
) {
  try {
    // const stream = await navigator.mediaDevices.getUserMedia({ video: { facingMode: 'environment' } }); // 일반 카메라 모드 연결
    const stream = await navigator.mediaDevices.getUserMedia({ video: true }); // 셀카 모드 연결

    streamRef.current = stream;
    videoRef.current!.srcObject = stream;
  } catch (err) {
    // console.log('카메라 액세스에 실패했습니다.', err);
  }

  // // 일반 모드 카메라 연결하기
  // navigator.mediaDevices
  //     // .getUserMedia({ video: { facingMode: 'environment' } }) // 일반 카메라 모드
  //     .getUserMedia({ video: true }) // 셀카모드
  //     .then((stream) => {
  //         streamRef.current = stream; // 현재 연결된 stream (video)를 저장
  //         videoRef.current!.srcObject = stream; // video의 영상 소스로 현재 연결된 스트림 활용
  //         console.log(stream.getTracks());
  //     })
  //     .catch(function (err) {
  //         console.log('카메라 액세스에 실패했습니다: ', err);
  //     });
}
