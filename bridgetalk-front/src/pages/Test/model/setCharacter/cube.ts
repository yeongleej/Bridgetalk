import * as THREE from 'three';

export class Cube {
  cube: THREE.Mesh;

  constructor() {
    const geometry = new THREE.BoxGeometry();

    const textureLoader = new THREE.TextureLoader();
    const texture = textureLoader.load('./test.jpg');
    const material = new THREE.MeshBasicMaterial({ map: texture });
    this.cube = new THREE.Mesh(geometry, material);
  }

  update() {
    this.cube.rotation.x += 0.01;
    this.cube.rotation.y += 0.01;
  }
}
