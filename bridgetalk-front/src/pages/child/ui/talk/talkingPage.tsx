import { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { TalkingComponents } from './components/talkingComponents';
import { getTalkStop } from '../../query';
import { useTalkStore } from '../../store';
import { Canvas, extend } from '@react-three/fiber';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import { CameraControls } from '@/pages/child/ui/talk/components/cameraControl';
import { Dino } from '@/pages/child/ui/talk/components/dino';
import * as S from '@/styles/child/talk/talk.style';
import { Timer } from '@/shared';
import { handleTalkEnd } from '../../model';

extend({ OrbitControls });

export function TalkingPage() {
  // Navigate
  const navigate = useNavigate();

  // State
  const [reply, setReply] = useState<any>();

  // GlobalState
  const reportsId = useTalkStore((state) => state.reportsId);
  const isRecording = useTalkStore((state) => state.isRecording);
  const setIsRecording = useTalkStore((state) => state.setIsRecording);
  // const setIsSend = useTalkStore((state) => state.setIsSend);
  const isEnd = useTalkStore((state) => state.isEnd);
  const isStart = useTalkStore((state) => state.isStart);
  const isTalking = useTalkStore((state) => state.isTalking);
  const isWaiting = useTalkStore((state) => state.isWaiting);
  const setIsEnd = useTalkStore((state) => state.setIsEnd);
  const setIsStart = useTalkStore((state) => state.setIsStart);
  const setIsTalking = useTalkStore((state) => state.setIsTalking);
  const talkStore = useTalkStore();

  // Ref
  const devounceTimerRef = useRef<any>(null);

  return (
    <S.Container>
      <div className="talking">
        <div className="talking__header">
          <button
            className="talking__header-end"
            onClick={() => {
              handleTalkEnd(
                setReply,
                setIsTalking,
                setIsEnd,
                setIsRecording,
                isRecording,
                devounceTimerRef,
                isEnd,
                isTalking,
                navigate,
                talkStore.setEmotion,
                talkStore.setSubtitle,
                talkStore.isStart,
              );
            }}
          >
            <img src={'assets/img/pic/end.svg'} />
          </button>
          <Timer
            devounceTimerRef={devounceTimerRef}
            getTalkStop={getTalkStop}
            reportsId={reportsId}
            setIsRecording={setIsRecording}
            setReply={setReply}
            setIsTalking={setIsTalking}
            navigate={navigate}
            setEmotion={talkStore.setEmotion}
            setSubtitle={talkStore.setSubtitle}
          />
          <div className="talking__header-message"></div>
        </div>
        <TalkingComponents reply={reply} setReply={setReply} devounceTimerRef={devounceTimerRef} />
        <div className="talking__container">
          <div className="talking__container-dino">
            <Canvas camera={{ position: [0, 0, 1.2], fov: 50 }}>
              <ambientLight intensity={2} />
              <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} />
              <pointLight position={[-10, -10, -10]} />
              <Dino />
              <CameraControls />
            </Canvas>
          </div>
          {!isTalking && !isEnd && (
            <div className="talking__container-talk">
              <p>나랑 이야기 하고 싶다면 나를 눌러줘!</p>
            </div>
          )}

          {isWaiting && (
            <div className="talking__container-talk">
              <p>음.. 너에게 어떤 말을 해주면 좋을까?</p>
            </div>
          )}
          {!isWaiting && talkStore.subtitle && (
            <div className="talking__container-talk">
              <p>{talkStore.subtitle}</p>
            </div>
          )}
        </div>
      </div>
    </S.Container>
  );
}
