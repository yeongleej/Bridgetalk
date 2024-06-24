import React, { useCallback, useEffect, useRef, useState } from 'react';
import { css } from '@emotion/css';
import Webcam from 'react-webcam';
import { Camera } from '@mediapipe/camera_utils';
import { Pose, Results } from '@mediapipe/pose';
import { drawDressParts } from './utils/drawParts';

// 이미지 URL을 변수에 직접 할당
const leftForearmUrl = '/assets/img/child/game/leftForearm.png';
const rightForearmUrl = '/assets/img/child/game/rightForearm.png';
const topUrl = '/assets/img/child/game/top.png';
const bottomUrl = '/assets/img/child/game/bottom.png';

export function TestCamera() {
  const webcamRef = useRef<Webcam>(null);
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const resultsRef = useRef<Results>();

  const [dressImages, setDressImages] = useState({
    leftForearm: null as HTMLImageElement | null,
    rightForearm: null as HTMLImageElement | null,
    top: null as HTMLImageElement | null,
    bottom: null as HTMLImageElement | null,
  });

  useEffect(() => {
    const loadImage = (src: string) => {
      return new Promise<HTMLImageElement>((resolve) => {
        const img = new Image();
        img.src = src;
        img.onload = () => resolve(img);
      });
    };

    Promise.all([loadImage(leftForearmUrl), loadImage(rightForearmUrl), loadImage(topUrl), loadImage(bottomUrl)]).then(
      ([leftForearm, rightForearm, top, bottom]) => {
        setDressImages({ leftForearm, rightForearm, top, bottom });
      },
    );
  }, []);

  const onResults = useCallback(
    (results: Results) => {
      resultsRef.current = results;
      const { leftForearm, rightForearm, top, bottom } = dressImages;
      if (leftForearm && rightForearm && top && bottom) {
        const canvasCtx = canvasRef.current!.getContext('2d')!;
        drawDressParts(canvasCtx, results, { leftForearm, rightForearm, top, bottom });
      }
    },
    [dressImages],
  );

  useEffect(() => {
    const pose = new Pose({
      locateFile: (file) => `https://cdn.jsdelivr.net/npm/@mediapipe/pose/${file}`,
    });

    pose.setOptions({
      modelComplexity: 1,
      smoothLandmarks: true,
      minDetectionConfidence: 0.5,
      minTrackingConfidence: 0.5,
    });

    pose.onResults(onResults);

    if (webcamRef.current !== undefined && webcamRef.current !== null) {
      const camera = new Camera(webcamRef.current.video!, {
        onFrame: async () => {
          await pose.send({ image: webcamRef.current!.video! });
        },
        width: 1280,
        height: 720,
      });
      camera.start();
    }
  }, [onResults]);

  const OutputData = () => {
    const results = resultsRef.current!;
    // console.log(results.poseLandmarks);
  };

  return (
    <div className={styles.container}>
      <Webcam
        audio={false}
        style={{ visibility: 'hidden' }}
        width={1280}
        height={720}
        ref={webcamRef}
        screenshotFormat="image/jpeg"
        videoConstraints={{ width: 1280, height: 720, facingMode: 'user' }}
      />
      <canvas ref={canvasRef} className={styles.canvas} width={1280} height={720} />
      <div className={styles.buttonContainer}>
        <button className={styles.button} onClick={OutputData}>
          Output Data
        </button>
      </div>
    </div>
  );
}

const styles = {
  container: css`
    position: relative;
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
  `,
  canvas: css`
    position: absolute;
    width: 1280px;
    height: 720px;
    background-color: #fff;
  `,
  buttonContainer: css`
    position: absolute;
    top: 20px;
    left: 20px;
  `,
  button: css`
    color: #fff;
    background-color: #0082cf;
    font-size: 1rem;
    border: none;
    border-radius: 5px;
    padding: 10px 10px;
    cursor: pointer;
  `,
};

export default TestCamera;
