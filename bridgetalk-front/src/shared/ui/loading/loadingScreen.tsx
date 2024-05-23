import { Canvas, extend } from '@react-three/fiber';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import { CameraControls } from '@/pages/child/ui/talk/components/cameraControl';
import { Dino } from '@/pages/child/ui/talk/components/dino';
import * as S from '@/styles/shared/loading.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';

extend({ OrbitControls });

export function LoadingScreen() {
  return (
    <S.Container>
      <div className="loadingScreen__character">
        {/* <Canvas camera={{ position: [0, 0, 1.2], fov: 50 }}>
          <ambientLight intensity={2} />
          <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} />
          <pointLight position={[-10, -10, -10]} />
          <Dino />
          <CameraControls />
        </Canvas> */}
        <FontAwesomeIcon icon={faSpinner} spinPulse />
      </div>
      <div className="loadingScreen__text">이동 중...</div>
    </S.Container>
  );
}
