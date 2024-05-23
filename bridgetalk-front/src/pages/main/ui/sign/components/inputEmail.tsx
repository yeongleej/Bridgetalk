import { Dispatch, SetStateAction, useRef } from 'react';
import * as S from '@/styles/main/inputEmail.style';
import { useSignupStore } from '@/pages/main/store';
import { validateEmail } from '@/pages/main/model';
import { useErrorStore } from '@/shared/store';
import { postAuthSend, postAuthVerify } from '@/pages';
import { errorCatch } from '@/shared';

interface EmailProps {
  setPage: Dispatch<SetStateAction<number>>;
}

export function InputEmail({ setPage }: EmailProps) {
  const { email, setEmail } = useSignupStore((state) => ({ email: state.email, setEmail: state.setEmail }));
  const setErrorModalState = useErrorStore((state) => state.setErrorModalState);
  const confirmRef: any = useRef<HTMLInputElement>();

  return (
    <S.Container>
      <div className="email">
        <div className="email__title">
          <img src={'assets/img/main/emailicon.svg'} />
        </div>
        <input className="email__input" value={email} onChange={(e) => setEmail(e.target.value)} type="email" />
        <button
          className="email__verify"
          onClick={() => {
            postAuthSend(email)
              .then((res) => {
                setErrorModalState('인증번호가 전송됐습니다.');
              })
              .catch((err) => {
                if (err instanceof Error) {
                  errorCatch(err, setErrorModalState);
                }
              });
          }}
        >
          Verify
        </button>
      </div>
      <div className="confirm">
        <div className="confirm__title">
          <img src={'assets/img/main/emailverifynumbericon.svg'} />
        </div>
        <input className="confirm__input" ref={confirmRef} />
      </div>
      <button
        className="next"
        onClick={async () => {
          let alert = '';
          if (!validateEmail(email)) {
            alert += '이메일은 영어(소문자), 숫자만 허용되며 5자 ~ 30자 이내만 가능합니다.';
          }

          if (alert) {
            setErrorModalState(alert);
            return;
          }

          try {
            const res = await postAuthVerify(email, confirmRef.current.value.trim());

            setPage((page) => page + 1);
          } catch (err: any) {
            errorCatch(err, setErrorModalState);
          }
        }}
      >
        다음
        <img src={'assets/img/nexticon.svg'} />
      </button>
    </S.Container>
  );
}
