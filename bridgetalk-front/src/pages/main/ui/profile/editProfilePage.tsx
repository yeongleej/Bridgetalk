import { decodeToken, getUUIDbyToken } from '@/shared';
import * as S from '@/styles/main/editProfilePage.style';
import { useEffect, useMemo, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useUserStore } from '../../store';
import { validateName, validateNickname, validatePassword } from '../../model';
import { patchEditProfile } from '../../query/patchEditProfile/patchEditProfile';
import { postAddProfile } from '../../query';
import { useFrame, useLoader } from 'react-three-fiber';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
import { AnimationMixer } from 'three';
import { Canvas } from '@react-three/fiber';
import { PerspectiveCamera } from '@react-three/drei';
import { DinoSelect } from './components/item/dinoSelect';
import { useErrorStore } from '@/shared/store';

interface Props {
  type: string;
}
export function EditProfilePage({ type }: Props) {
  const { userName, setUserName, userNickname, setUserNickname, userDino, setUserDino, parentToken } = useUserStore(
    (state) => ({
      userName: state.userName,
      setUserName: state.setUserName,
      userNickname: state.userNickname,
      setUserNickname: state.setUserNickname,
      userDino: state.userDino,
      setUserDino: state.setUserDino,
      parentToken: state.userId,
    }),
  );
  const errorStore = useErrorStore();

  // State
  const [page, setPage] = useState<number>(0);
  const [dino, setDino] = useState<number>(type === 'edit' ? Number(userDino[1]) - 1 : 0);
  const [password, setPassword] = useState<string>('');
  const navigate = useNavigate();

  // Ref
  const nameRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const passwordCheckRef = useRef<HTMLInputElement>(null);

  const dinos: any[] = [];
  for (let i = 1; i <= 6; i++) {
    dinos.push(`D${i}`);
  }

  useEffect(() => {
    setUserDino(dinos[dino]);
  }, [dino]);

  const title: any = useMemo(
    () => ({
      edit: '닉네임을 입력해주세요',
      new: '사용자 정보를 입력해주세요',
    }),
    [],
  );

  useEffect(() => {
    if (page === 0) {
      nameRef.current?.focus();
    } else if (page === 1) {
      passwordRef.current?.focus();
    }
  }, [page]);

  return (
    <S.Container>
      {page === 0 && (
        <>
          <button
            className="back"
            onClick={() => {
              navigate('/profile');
            }}
          >
            <img src={'assets/img/main/backIcon.svg'} />
          </button>
          <div className="main">
            <div className="main__content">
              <div className="main__content-title">
                {type === 'new' ? (
                  <img src={'assets/img/main/newProfile.svg'} />
                ) : (
                  <img src={'assets/img/main/editProfile.svg'} />
                )}
              </div>
              <div className="main__content-box">
                <div className="main__content-box-title">{title[type]}</div>
                <div className="main__content-box-name">
                  {type === 'new' && (
                    <div className="flex">
                      <div className="main__content-box-name-title">
                        <img src={'assets/img/main/nameicon.svg'} />
                      </div>
                      <input
                        ref={nameRef}
                        type="text"
                        onChange={(e) => setUserName(e.target.value)}
                        className="main__content-box-name-input"
                      />
                    </div>
                  )}
                  <div className="flex">
                    <div className="main__content-box-nickname-title">
                      <img src={'assets/img/main/nicknameicon.svg'} />
                    </div>
                    <input
                      type="text"
                      defaultValue={type === 'edit' ? userNickname : ''}
                      onChange={(e) => setUserNickname(e.target.value)}
                      className="main__content-box-nickname-input"
                    />
                  </div>
                </div>
              </div>
            </div>
            <button
              className="main__button"
              onClick={() => {
                if (type === 'new') {
                  setPage((page) => page + 1);
                } else if (type === 'edit') {
                  setPage((page) => page + 2);
                }
              }}
            >
              다음 <img src={'assets/img/nexticon.svg'} />
            </button>
          </div>
        </>
      )}
      {page === 1 && (
        <>
          <button
            className="back"
            onClick={() => {
              setPage((page) => page - 1);
            }}
          >
            <img src={'assets/img/main/backIcon.svg'} />
          </button>
          <div className="main">
            <div className="main__content">
              <div className="main__content-title">
                {type === 'new' ? (
                  <img src={'assets/img/main/newProfile.svg'} />
                ) : (
                  <img src={'assets/img/main/editProfile.svg'} />
                )}
              </div>
              <div className="main__content-box">
                <div className="main__content-box-title">비밀번호를 입력해주세요</div>
                <div className="main__content-box-password">
                  {type === 'new' && (
                    <div className="flex">
                      <div className="main__content-box-password-title">
                        <img src={'assets/img/main/passwordicon.svg'} />
                      </div>
                      <input ref={passwordRef} type="password" className="main__content-box-password-input" />
                    </div>
                  )}
                  <div className="flex">
                    <div className="main__content-box-passwordcheck-title">
                      <img src={'assets/img/main/passwordcheckicon.svg'} />
                    </div>
                    <input ref={passwordCheckRef} type="password" className="main__content-box-nickname-input" />
                  </div>
                </div>
              </div>
            </div>
            <button
              className="main__button"
              onClick={() => {
                if (passwordRef.current!.value !== passwordCheckRef.current!.value) {
                  errorStore.setErrorModalState('비밀번호가 일치하지 않습니다');
                  return;
                }

                if (/\s/.test(passwordRef.current!.value)) {
                  errorStore.setErrorModalState('공백을 포함할 수 없습니다.');
                  return;
                }

                if (passwordRef.current!.value.trim() === '') {
                  errorStore.setErrorModalState('비밀번호를 입력해주세요');
                  return;
                }

                setPassword(passwordRef.current!.value);
                setPage((page) => page + 1);
              }}
            >
              다음 <img src={'assets/img/nexticon.svg'} />
            </button>
          </div>
        </>
      )}
      {page === 2 && (
        <>
          <button
            className="back"
            onClick={() => {
              setPage((page) => page - 1);
            }}
          >
            <img src={'assets/img/main/backIcon.svg'} />
          </button>
          <div className="selectDino">
            <div className="title">
              <img src={'assets/img/main/selectCharacter.svg'} />
            </div>
            <div className="selectbox">
              <div className="selectbox__title">캐릭터를 선택해주세요</div>
              <div className="selectbox__content">
                <DinoBox />
              </div>
            </div>
          </div>
          <div className="buttons">
            <button
              className="buttons__next"
              onClick={() => {
                if (type === 'new') {
                  // 신규 프로필 작성 로직
                  if (validateName(userName) && validateNickname(userNickname)) {
                    postAddProfile({
                      parentsId: parentToken,
                      kidsName: userName,
                      kidsDino: userDino,
                      kidsNickname: userNickname,
                      kidsPassword: password,
                    }).then((res: any) => {
                      errorStore.setErrorModalState('프로필이 성공적으로 추가됐습니다');
                      navigate('/profile');
                    });
                  } else if (!validateName(userName)) {
                    errorStore.setErrorModalState(
                      '이름은 영어, 한글, 숫자를 포함한 1자 ~ 20자 이내 문자만 허용됩니다.',
                    );
                  } else if (!validateNickname(userNickname)) {
                    errorStore.setErrorModalState(
                      '닉네임은 영어, 한글, 숫자를 포함한 1자 ~ 20자 이내 문자만 허용됩니다.',
                    );
                  }
                } else if (type === 'edit') {
                  // 프로필 수정 로직
                  if (validateName(userNickname)) {
                    patchEditProfile(
                      {
                        nickname: userNickname,
                        dino: userDino,
                      },
                      getUUIDbyToken(),
                    ).then((res: any) => navigate('/profile'));
                    errorStore.setErrorModalState('정보가 변경됐습니다.');
                  } else {
                    errorStore.setErrorModalState(
                      '이름은 영어, 한글, 숫자를 포함한 1자 ~ 20자 이내 문자만 허용됩니다.',
                    );
                    return;
                  }
                }
              }}
            >
              <img src={'assets/img/main/saveIcon.svg'} />
              저장
            </button>
          </div>
        </>
      )}
    </S.Container>
  );
}

function DinoBox() {
  const [dinoNum, setDinoNum] = useState<number>(3);
  const userStore = useUserStore();

  return (
    <>
      {Array(6)
        .fill(0)
        .map((_, idx) => (
          <Canvas style={{ width: `10svw` }} className="canvas">
            <ambientLight intensity={Number(userStore.userDino[1]) === idx + 1 ? 2 : 1} />
            <PerspectiveCamera position={[-0.01, 2, 2]} fov={55} zoom={1} />
            <DinoSelect idx={idx + 1} />
          </Canvas>
        ))}
    </>
  );
}
