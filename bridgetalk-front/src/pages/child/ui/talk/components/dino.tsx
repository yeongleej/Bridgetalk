import { useRef, useEffect, useState } from 'react';
import { useFrame, useLoader, extend } from '@react-three/fiber';
import { AnimationMixer } from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import { useTalkStore } from '@/pages/child/store';
import { postProfileLogin } from '@/pages/main';
import { getUUIDbyToken } from '@/shared';
import { getDinoEmotion } from '@/pages/child/model';

extend({ OrbitControls });

export function Dino() {
  const setIsTalking = useTalkStore((state) => state.setIsTalking);
  const emotion = useTalkStore((state) => state.emotion);
  const isEnd = useTalkStore((state) => state.isEnd);
  const setIsStart = useTalkStore((state) => state.setIsStart);
  const [scale, setScale] = useState<number>(1);
  const [dino, setDino] = useState<string>(sessionStorage.getItem('dino') ?? 'D1');
  const [act, setAct] = useState<string>('idle');
  const [gltf, setGltf] = useState<any>(null);

  const mixer = useRef<AnimationMixer | null>(null);

  const model = useLoader(GLTFLoader, `/assets/dino/${dino}/${act}.glb`, (loader) => {
    loader.manager.onStart = (url, itemsLoaded, itemsTotal) => {
      // console.log('Started loading file: ' + url + '.\nLoaded ' + itemsLoaded + ' of ' + itemsTotal + ' files.');
    };
    loader.manager.onLoad = () => {
      // console.log('Loading complete!');
    };
    loader.manager.onProgress = (url, itemsLoaded, itemsTotal) => {
      // console.log('Loading file: ' + url + '.\nLoaded ' + itemsLoaded + ' of ' + itemsTotal + ' files.');
    };
    loader.manager.onError = (url) => {
      // console.log('There was an error loading ' + url);
    };
  });

  useEffect(() => {
    setGltf(model);
  }, [model]);

  useEffect(() => {
    const sessionDino = sessionStorage.getItem('dino');

    if (sessionDino) {
      setDino(sessionDino);
    }

    if (!sessionDino) {
      postProfileLogin(getUUIDbyToken(), '').then((res) => {
        setDino(res.data.userDino);
        sessionStorage.setItem('dino', res.data.userDino);
      });
    }
  }, []);

  useEffect(() => {
    setAct(getDinoEmotion(emotion));
    // console.log(getDinoEmotion(emotion));
  }, [emotion]);

  useEffect(() => {
    if (gltf && gltf.animations && gltf.animations.length > 0) {
      if (mixer.current) {
        mixer.current.stopAllAction();
        mixer.current.uncacheRoot(gltf.scene);
      }
      mixer.current = new AnimationMixer(gltf.scene);
      const action = mixer.current.clipAction(gltf.animations[0]);
      action.play();
    }
    return () => {
      if (mixer.current) {
        mixer.current.stopAllAction();
        mixer.current.uncacheRoot(gltf.scene);
        mixer.current = null;
      }
    };
  }, [gltf]);

  useFrame((state, delta) => {
    mixer.current?.update(delta);
  });

  if (!gltf) return null; // 모델이 로드되지 않았을 때는 렌더링하지 않음

  return (
    <primitive
      onClick={() => {
        if (!isEnd) {
          // console.log('{ 대화 시작 }');
          setIsStart(true);
          setIsTalking(true);
        }
      }}
      object={gltf.scene}
      scale={scale}
      position={[0, -0.4, 0]}
    />
  );
}
