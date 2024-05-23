import * as THREE from 'three';

export class Sphere {
  sphere!: THREE.Mesh;

  constructor() {
    const geometry = new THREE.SphereGeometry(1, 32, 32);
    const textureLoader = new THREE.TextureLoader();

    // 텍스처 로드
    textureLoader.load('./test.jpg', (texture) => {
      const material = new THREE.MeshBasicMaterial({ map: texture });
      this.sphere = new THREE.Mesh(geometry, material); // 텍스처가 로드된 후, 구체 생성
    });
  }

  update() {
    if (!this.sphere) return;

    this.sphere.rotation.x += 0.01;
    this.sphere.rotation.y += 0.01;
  }
}
