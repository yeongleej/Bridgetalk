import { useEffect } from 'react';
import { extend, useThree } from '@react-three/fiber';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';

extend({ OrbitControls });

export function CameraControls() {
  const {
    camera,
    gl: { domElement },
  } = useThree();

  useEffect(() => {
    const controls = new OrbitControls(camera, domElement);
    controls.minDistance = 1; // 최소 거리 제한
    controls.maxDistance = 20; // 최대 거리 제한
    return () => {
      controls.dispose();
    };
  }, [camera, domElement]);

  return null;
}
