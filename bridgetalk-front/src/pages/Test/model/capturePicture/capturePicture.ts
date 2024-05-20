import { Context, RefObject } from 'react';

// 카메라 화면 캡쳐하기
export const capturePicture = (canvasRef: RefObject<HTMLCanvasElement>, videoRef: RefObject<HTMLVideoElement>) => {
  const ctx: CanvasRenderingContext2D = canvasRef.current!.getContext('2d')!;

  ctx.drawImage(videoRef.current!, 0, 0, canvasRef.current!.width, canvasRef.current!.height);

  // console.log(ctx);
  // return canvasRef.current!.toDataURL('image/jpg');
};
