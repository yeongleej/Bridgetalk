import { useSignupStore } from '@/pages/main/store';
import * as S from '@/styles/main/selectDino.style';
import { Dispatch, SetStateAction, useState } from 'react';

interface Props {
  setPage: Dispatch<SetStateAction<number>>;
}

export function SelectDino({ setPage }: Props) {
  const { dino, setDino } = useSignupStore((state) => ({
    dino: state.dino,
    setDino: state.setDino,
  }));

  const [selDino, setSelDino] = useState<number>(0);

  const dinos: any[] = [];
  for (let i = 1; i <= 6; i++) {
    dinos.push(`D${i}`);
  }

  return (
    <S.Container>
      <div className="title">
        <img src={'assets/img/main/selectCharacter.svg'} />
      </div>
      <div className="selectbox">
        <div className="selectbox__title">캐릭터를 선택해주세요</div>
        <div className="selectbox__content">
          <button
            className="selectbox__content-prev"
            onClick={() => {
              setSelDino((selDino) => (selDino > 0 ? selDino - 1 : selDino));
            }}
          >
            <img src={'assets/img/prevTriangle.svg'} />
          </button>
          <div className="selectbox__content-dino">
            <img src={`assets/img/${dinos[selDino]}.svg`} />
          </div>
          <button
            className="selectbox__content-next"
            onClick={() => {
              setSelDino((selDino) => (selDino < dinos.length - 1 ? selDino + 1 : selDino));
            }}
          >
            <img src={'assets/img/nextTriangle.svg'} />
          </button>
        </div>
      </div>
      <div className="buttons">
        <button
          className="buttons__prev"
          onClick={() => {
            setDino(`D${selDino}`);
            setPage((page) => page - 1);
          }}
        >
          <img src={'assets/img/previcon.svg'} />
          이전
        </button>
        <button
          className="buttons__next"
          onClick={() => {
            setDino(`D${selDino}`);
            setPage((page) => page + 1);
          }}
        >
          다음
          <img src={'assets/img/nexticon.svg'} />
        </button>
      </div>
    </S.Container>
  );
}
