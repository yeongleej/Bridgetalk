import React, { useState } from 'react';
import { useLogin } from '@/pages/main/query/login';
import { useUserStore } from '@/pages/main/store/useUserStore/useUserStore';
import { useNavigate } from 'react-router-dom';
import * as S from '@/styles/main/signIn.style';
import { HomeButton } from '@/shared';
import { postSignin } from '../../query';
import { handleSignin } from '../../model';
import { useErrorStore } from '@/shared/store';

export function SignInPage() {
  const navigate = useNavigate();

  const userStore = useUserStore();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const setErrorModalState = useErrorStore((state) => state.setErrorModalState);

  return (
    <S.Container>
      <HomeButton navigate={navigate} />
      <div className="title">
        <img src={'assets/img/main/login.svg'} />
      </div>

      <div className="email">
        <div className="email__title">
          <img src={'assets/img/main/emailicon.svg'} />
        </div>
        <input
          type="email"
          className="email__input"
          value={email}
          onChange={(e) => {
            setEmail(e.target.value);
          }}
        />
      </div>
      <div className="password">
        <div className="password__title">
          <img src={'assets/img/main/passwordicon.svg'} />
        </div>
        <input
          type="password"
          className="password__input"
          value={password}
          onChange={(e) => {
            setPassword(e.target.value);
          }}
          onKeyDown={(e) => {
            if (e.key === 'Enter') {
              handleSignin(
                {
                  email,
                  password,
                },
                userStore,
                navigate,
                setErrorModalState,
              );
            }
          }}
        />
      </div>
      <button
        className="button"
        onClick={() => {
          handleSignin(
            {
              email,
              password,
            },
            userStore,
            navigate,
            setErrorModalState,
          );
        }}
      >
        다음
        <img src={'assets/img/nexticon.svg'} />
      </button>
    </S.Container>
  );
}
