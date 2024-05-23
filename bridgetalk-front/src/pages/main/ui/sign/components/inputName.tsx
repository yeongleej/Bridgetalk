import { Dispatch, SetStateAction } from 'react';
import * as S from '@/styles/main/inputName.style';
import { useSignupStore } from '@/pages/main/store';
import { handleNicknameCheck, validateName, validateNickname, validatePassword } from '@/pages/main/model';
import { useErrorStore } from '@/shared/store';

interface Props {
  setPage: Dispatch<SetStateAction<number>>;
}

export function InputName({ setPage }: Props) {
  const { name, setName, nickname, setNickname, password, setPassword, passwordCheck, setPasswordCheck } =
    useSignupStore((state) => ({
      name: state.name,
      setName: state.setName,
      nickname: state.nickname,
      setNickname: state.setNickname,
      password: state.password,
      setPassword: state.setPassword,
      passwordCheck: state.passwordCheck,
      setPasswordCheck: state.setPasswordCheck,
    }));
  const setErrorModalState = useErrorStore((state) => state.setErrorModalState);

  return (
    <S.Container>
      <form
        onSubmit={(e) => {
          e.preventDefault();
        }}
      >
        <div className="name">
          <div className="name__title">
            <img src={'assets/img/main/nameicon.svg'} />
          </div>
          <input
            className="name__input"
            type="text"
            value={name}
            onChange={(e) => {
              setName(e.target.value);
            }}
          />
        </div>
        <div className="nickname">
          <div className="nickname__title">
            <img src={'assets/img/main/nicknameicon.svg'} />
          </div>
          <input
            className="nickname__input"
            type="text"
            value={nickname}
            onChange={(e) => {
              setNickname(e.target.value);
            }}
          />
        </div>
        <div className="password">
          <div className="password__title">
            <img src={'assets/img/main/passwordicon.svg'} />
          </div>
          <input
            className="password__input"
            type="password"
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />
        </div>
        <div className="passwordcheck">
          <div className="passwordcheck__title">
            <img src={'assets/img/main/passwordcheckicon.svg'} />
          </div>
          <input
            className="passwordcheck__input"
            type="password"
            value={passwordCheck}
            onChange={(e) => {
              setPasswordCheck(e.target.value);
            }}
          />
        </div>
        <div className="buttons">
          <button
            className="buttons__prev"
            onClick={() => {
              setPage((page) => page - 1);
            }}
          >
            <img src={'assets/img/previcon.svg'} />
            이전
          </button>
          <button
            className="buttons__next"
            onClick={() => {
              let alert = '';

              if (!validateName(name)) {
                alert += '이름은 영어, 한글, 숫자만 허용되며 1 ~ 20자 이내여야 합니다.';
              }
              if (!validateNickname(nickname)) {
                alert += '닉네임은 영어, 한글, 숫자, 공백만 허용되며 1 ~ 20자 이내여야 합니다.\n';
              }
              if (!validatePassword(password)) {
                alert += '비밀번호는 영문, 숫자 포함 8 ~ 20자 이내이며\n특수문자(!@#$%^&*+=-)를 포함할 수 있습니다.\n';
              }
              if (password !== passwordCheck) {
                alert += '입력한 비밀번호가 일치하지 않습니다.';
              }

              if (alert) {
                window.alert(alert);
                return;
              }

              handleNicknameCheck(nickname, setErrorModalState).then((res) => {
                if (res) {
                  setPage((page) => page + 1);
                }
              });
            }}
          >
            다음
            <img src={'assets/img/nexticon.svg'} />
          </button>
        </div>
      </form>
    </S.Container>
  );
}
