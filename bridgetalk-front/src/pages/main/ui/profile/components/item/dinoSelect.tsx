import { useRef, useEffect, useState } from 'react';
import { useFrame, useLoader } from '@react-three/fiber';
import { AnimationMixer } from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
import { useUserStore } from '@/pages';

interface DinoProps {
  idx: number;
}

export function DinoSelect({ idx }: DinoProps) {
  const [act, setAct] = useState<string>('idle');
  const [gltf, setGltf] = useState<any>(null);
  const [scale, setScale] = useState<number>(1);

  const mixer = useRef<AnimationMixer | null>(null);

  const userStore = useUserStore();

  // GLTF 파일 로드 및 상태 설정
  const model = useLoader(GLTFLoader, `/assets/dino/D${idx}/${act}.glb`, (loader) => {
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
    if (model) {
      setGltf(model);
    }
  }, [model]);

  // 애니메이션 초기화 및 정리
  useEffect(() => {
    if (gltf && gltf.animations.length > 0) {
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

  // 상태 변경에 따른 애니메이션 업데이트
  useEffect(() => {
    if (Number(userStore.userDino[1]) === idx) {
      setAct('cute');
    } else {
      setAct('idle');
    }
  }, [userStore.userDino, idx]);

  useEffect(() => {
    const handleResize = () => {
      const viewportWidth = window.innerWidth;
      if (viewportWidth < 768) {
        // 모바일 화면 크기
        // console.log('모바일');
        setScale(3);
      } else {
        // PC 화면 크기
        // console.log('피씨');
        setScale(7);
      }
    };

    // 초기 크기 설정
    handleResize();

    // 윈도우 리사이즈 이벤트 리스너 추가
    window.addEventListener('resize', handleResize);

    // 리사이즈 이벤트 리스너 제거
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  useFrame((state, delta) => {
    mixer.current?.update(delta);
  });

  if (!gltf) return null;

  return (
    <primitive
      object={gltf ? gltf.scene : null}
      scale={scale}
      position={[0, -1.5, -2]}
      onClick={() => {
        userStore.setUserDino(`D${idx}`);
      }}
    />
  );
}
