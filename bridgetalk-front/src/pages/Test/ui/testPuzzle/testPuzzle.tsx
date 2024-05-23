// // import React from 'react';
// // import { useNavigate } from 'react-router-dom';
// // import { Canvas, extend } from '@react-three/fiber';
// // import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
// // import { CameraControls } from '@/pages/child/ui/talk/components/cameraControl';
// // import { Dino } from './components/dino';
// // import { Dino2 } from './components/dino2'; // 'Dino2' 대소문자 수정
// // import * as S from './styles/character';
// // import SkyWrapper from './components/skyWrapper';

// // extend({ OrbitControls });

// // export function TestPuzzle() {
// //   const navigate = useNavigate();

// //   const handleDinoClick = () => {
// //     navigate('/dress');
// //   };

// //   return (
// //     <S.Container>
// //       <div className="three">
// //         <div className="header">
// //           <img src="/assets/flag/viet.png" alt="Vietnam Flag" />
// //           <img src="/assets/flag/kor.png" alt="Korea Flag" />
// //         </div>
// //         <Canvas camera={{ position: [0, 2, 5], fov: 50 }}>
// //           {/* 조명 설정 */}
// //           <ambientLight intensity={1} />
// //           <directionalLight
// //             intensity={1}
// //             position={[5, 5, 5]}
// //             shadow-mapSize-width={1024}
// //             shadow-mapSize-height={1024}
// //             shadow-camera-far={50}
// //             shadow-camera-left={-10}
// //             shadow-camera-right={10}
// //             shadow-camera-top={10}
// //             shadow-camera-bottom={-10}
// //           />
// //           <pointLight position={[-10, -10, -10]} intensity={0.5} />
// //           <pointLight position={[10, 10, 10]} intensity={0.5} />

// //           <Dino position={[-0.5, 0, 0]} onClick={handleDinoClick} />
// //           <Dino2 position={[0.5, 0, 0]} />
// //           <CameraControls />

// //           {/* 바닥 */}
// //           <mesh rotation={[-Math.PI / 2, 0, 0]} position={[0, -0.4, 0]}>
// //             <planeGeometry args={[100, 100]} />
// //             <meshStandardMaterial color="green" />
// //           </mesh>

// //           {/* 하늘 */}
// //           <SkyWrapper />
// //         </Canvas>
// //       </div>
// //     </S.Container>
// //   );
// // }

// export function TestPuzzle() {
//   const webcamRef = useRef<Webcam>(null);
//   const canvasRef = useRef<HTMLCanvasElement>(null);
//   const resultsRef = useRef<Results>();

//   const onResults = useCallback((results: Results) => {
//     resultsRef.current = results;
//     const canvasCtx = canvasRef.current!.getContext('2d')!;
//     drawPose(canvasCtx, results);
//   }, []);

//   useEffect(() => {
//     const pose = new Pose({
//       locateFile: (file) => `https://cdn.jsdelivr.net/npm/@mediapipe/pose/${file}`,
//     });

//     pose.setOptions({
//       modelComplexity: 1,
//       smoothLandmarks: true,
//       minDetectionConfidence: 0.5,
//       minTrackingConfidence: 0.5,
//     });

//     pose.onResults(onResults);

//     if (webcamRef.current !== undefined && webcamRef.current !== null) {
//       const camera = new Camera(webcamRef.current.video!, {
//         onFrame: async () => {
//           await pose.send({ image: webcamRef.current!.video! });
//         },
//         width: 1280,
//         height: 720,
//       });
//       camera.start();
//     }
//   }, [onResults]);

//   const OutputData = () => {
//     const results = resultsRef.current!;
//     // console.log(results.poseLandmarks);
//   };

//   return (
//     <div className={styles.container}>
//       <Webcam
//         audio={false}
//         style={{ visibility: 'hidden' }}
//         width={1280}
//         height={720}
//         ref={webcamRef}
//         screenshotFormat="image/jpeg"
//         videoConstraints={{ width: 1280, height: 1000, facingMode: 'user' }}
//       />
//       <canvas ref={canvasRef} className={styles.canvas} width={1280} height={720} />
//       <div className={styles.buttonContainer}>
//         <button className={styles.button} onClick={OutputData}>
//           Output Data
//         </button>
//       </div>
//     </div>
//   );
// }
