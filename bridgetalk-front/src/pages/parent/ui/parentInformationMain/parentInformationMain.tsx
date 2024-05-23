import { useNavigate } from 'react-router-dom';
import * as S from '@/styles/parent/parentInformationMain.style';
import { BackButton } from '@/shared';
import { useMemo } from 'react';
import { useReportStore } from '../../store';

export function ParentInformationMain() {
  const navigate = useNavigate();

  const language = useReportStore((state) => state.language);

  const title = useMemo(
    () => ({
      kor: ['육아 정보', '단어 학습'],
      viet: ['Nuôi dưỡng thông tin', 'học chữ'],
      ph: [''],
    }),
    [],
  );

  return (
    <>
      <BackButton path="../main" navigate={navigate} />
      <S.Container>
        <button onClick={() => navigate('nurture')}>
          <img src={'/assets/img/parent/nurture.svg'} />
          <div>{title[language][0]}</div>
        </button>
        <button onClick={() => navigate('word')}>
          <img src={'/assets/img/parent/edu.svg'} />
          <div>{title[language][1]}</div>
        </button>
      </S.Container>
    </>
  );
}
