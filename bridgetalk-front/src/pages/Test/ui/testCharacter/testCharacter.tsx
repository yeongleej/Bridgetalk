// // import React from 'react';
// // import { useNavigate } from 'react-router-dom';
// // import { Canvas, extend } from '@react-three/fiber';
// // import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
// // import { CameraControls } from '@/pages/child/ui/talk/components/cameraControl';
// // import { Dino } from './components/dino';
// // import { Dino2 } from './components/dino2';
// // import * as S from './styles/character';

// // extend({ OrbitControls });

// // export function TestCharacter() {
// //   // Navigate
// //   const navigate = useNavigate();

// //   return (
// //     <S.Container>
// //       <div className="three">
// //         <div className="header">
// //           <img src="/assets/flag/viet.png" alt="Vietnam Flag" />
// //           <img src="/assets/flag/kor.png" alt="Korea Flag" />
// //         </div>
// //         <Canvas camera={{ position: [0, 0, 1.2], fov: 50 }}>
// //           <ambientLight intensity={2} />
// //           <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} />
// //           <pointLight position={[-10, -10, -10]} />
// //           <Dino position={[-0.5, -0.4, -5]} onClick={() => navigate('/dress')} />
// //           <Dino2 position={[0.5, -0.4, -5]} />
// //           <CameraControls />
// //         </Canvas>
// //       </div>
// //     </S.Container>
// //   );
// // }

// export function TestCharacter() {
//   const webcamRef = useRef<Webcam>(null);
//   const canvasRef = useRef<HTMLCanvasElement>(null);
//   const resultsRef = useRef<Results>();

//   /**
//    * 검출결과（프레임마다 호출됨）
//    * @param results
//    */
//   const onResults = useCallback((results: Results) => {
//     resultsRef.current = results;

//     const canvasCtx = canvasRef.current!.getContext('2d')!;
//     drawCanvas(canvasCtx, results);
//   }, []);

//   // 초기 설정
//   useEffect(() => {
//     const hands = new Hands({
//       locateFile: (file) => {
//         return `https://cdn.jsdelivr.net/npm/@mediapipe/hands/${file}`;
//       },
//     });

//     hands.setOptions({
//       maxNumHands: 2,
//       modelComplexity: 1,
//       minDetectionConfidence: 0.5,
//       minTrackingConfidence: 0.5,
//     });

//     hands.onResults(onResults);

//     if (typeof webcamRef.current !== 'undefined' && webcamRef.current !== null) {
//       const camera = new Camera(webcamRef.current.video!, {
//         onFrame: async () => {
//           await hands.send({ image: webcamRef.current!.video! });
//         },
//         width: 1280,
//         height: 720,
//       });
//       camera.start();
//     }
//   }, [onResults]);

//   /*  랜드마크들의 좌표를 콘솔에 출력 */
//   const OutputData = () => {
//     const results = resultsRef.current!;
//     // console.log(results.multiHandLandmarks);
//   };

//   return (
//     <div className={styles.container}>
//       {/* 비디오 캡쳐 */}
//       <Webcam
//         audio={false}
//         style={{ visibility: 'hidden' }}
//         width={1280}
//         height={720}
//         ref={webcamRef}
//         screenshotFormat="image/jpeg"
//         videoConstraints={{ width: 1280, height: 720, facingMode: 'user' }}
//       />
//       {/* 랜드마크를 손에 표시 */}
//       <canvas ref={canvasRef} className={styles.canvas} width={1280} height={720} />
//       {/* 좌표 출력 */}
//       <div className={styles.buttonContainer}>
//         <button className={styles.button} onClick={OutputData}>
//           Output Data
//         </button>
//       </div>
//     </div>
//   );
// }
