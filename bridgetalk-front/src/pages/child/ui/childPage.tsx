import React, { useState } from 'react';
import { HomeButton2 } from '@/shared';
import * as S from '@/styles/child/childMain.style';
import { useNavigate } from 'react-router-dom';
import { MovingScreen } from '@/shared/ui/loading/movingScreen';
import { Canvas, extend } from '@react-three/fiber';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import { CameraControls } from '@/pages/child/ui/talk/components/cameraControl';
import { Dino2 } from '@/pages/Test/ui/testPuzzle/components/dino2';
import { Dino } from '@/pages/Test/ui/testPuzzle/components/dino';
import { Dino3 } from '@/pages/Test/ui/testPuzzle/components/dino3';

extend({ OrbitControls });

export function ChildPage() {
  const [showWelcomeScreen, setShowWelcomeScreen] = useState(true); // 모달 상태 관리
  const navigate = useNavigate();

  const handleCloseWelcomeScreen = () => {
    setShowWelcomeScreen(false);
  };

  return (
    <>
      {showWelcomeScreen && <MovingScreen onClose={handleCloseWelcomeScreen} />}
      {!showWelcomeScreen && (
        <>
          <HomeButton2 navigate={navigate} />
          <S.Container>
            <div className="childMain">
              <div className="childMain__header">
                <div className="childMain__header-toHome"></div>
                <div className="childMain__header-setting"></div>
              </div>
              <div className="childMain__container">
                <div className="childMain__container-title">
                  <img src={'assets/img/pic/childMenu.svg'} alt="Child Menu" />
                </div>
                <div className="childMain__container-content">
                  <div className="childMain__container-content-toMessage">
                    <img
                      src={'assets/img/child/toMessage.svg'}
                      alt="To Message"
                      onClick={() => {
                        navigate('/message/list');
                      }}
                    />
                  </div>
                  <div className="childMain__container-content-toTalk">
                    <img
                      src={'assets/img/child/toTalk.svg'}
                      alt="To Talk"
                      onClick={() => {
                        navigate('/talk');
                      }}
                    />
                  </div>
                  <div className="childMain__container-content-toGame">
                    <img
                      src={'assets/img/child/toGame.svg'}
                      alt="To Game"
                      onClick={() => {
                        navigate('/game');
                      }}
                    />
                  </div>
                </div>
              </div>
              <div className="childMain__footer">
                <div className="dino">
                  <p>
                    여기선
                    <br />
                    다이노가 보낸 편지를
                    <br />
                    읽어볼 수 있어!
                  </p>
                  <Canvas camera={{ position: [0, 0, 1.2], fov: 50 }}>
                    <ambientLight intensity={2} />
                    <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} />
                    <pointLight position={[-10, -10, -10]} />
                    <Dino position={[0, -0.4, 0]} />
                    <CameraControls />
                  </Canvas>
                </div>
                <div className="dino">
                  <p>
                    어떤 고민이든 궁금증이든
                    <br />
                    내가 다 들어줄게!
                    <br />
                    나랑 대화하자!
                  </p>
                  <Canvas camera={{ position: [0, 0, 1.2], fov: 50 }}>
                    <ambientLight intensity={2} />
                    <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} />
                    <pointLight position={[-10, -10, -10]} />
                    <Dino2 position={[0, -0.4, 0]} />
                    <CameraControls />
                  </Canvas>
                </div>
                <div className="dino">
                  <p>
                    다양한 나라별
                    <br />
                    문화를 알아볼 수 있는
                    <br />
                    게임센터로 와!
                  </p>
                  <Canvas camera={{ position: [0, 0, 1.2], fov: 50 }}>
                    <ambientLight intensity={2} />
                    <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} />
                    <pointLight position={[-10, -10, -10]} />
                    <Dino3 position={[0, -0.4, 0]} />
                    <CameraControls />
                  </Canvas>
                </div>
              </div>
            </div>
          </S.Container>
        </>
      )}
    </>
  );
}
