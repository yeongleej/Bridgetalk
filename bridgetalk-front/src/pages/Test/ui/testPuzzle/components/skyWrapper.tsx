// import { useEffect, useRef } from 'react';
// import * as THREE from 'three';
// import { useThree } from '@react-three/fiber';
// import { Sky } from 'three/examples/jsm/objects/Sky';

// const SkyWrapper = () => {
//   const { scene } = useThree();
//   const skyRef = useRef<Sky | null>(null);

//   useEffect(() => {
//     const sky = new Sky();
//     sky.scale.setScalar(450000);
//     scene.add(sky);
//     skyRef.current = sky;

//     const sun = new THREE.Vector3();
//     const phi = THREE.MathUtils.degToRad(90 - 10); // Elevation
//     const theta = THREE.MathUtils.degToRad(180); // Azimuth

//     sun.setFromSphericalCoords(1, phi, theta);
//     sky.material.uniforms['sunPosition'].value.copy(sun);

//     return () => {
//       scene.remove(sky);
//     };
//   }, [scene]);

//   return null;
// };

// export default SkyWrapper;
