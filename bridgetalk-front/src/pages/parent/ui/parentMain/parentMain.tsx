import { customAxios } from '@/shared';
import * as S from '@/styles/parent/parentMain.style';
import { useEffect, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { useReportStore } from '../../store';
import { getProfile } from '../../query';

export function ParentMain() {
  const navigate = useNavigate();

  const { setChildrenList, language } = useReportStore((state) => ({
    setChildrenList: state.setChildrenList,
    language: state.language,
  }));

  useEffect(() => {
    getProfile(setChildrenList);
  }, []);

  const leftMenu = useMemo(
    () => ({
      kor: <>아이의 마음</>,
      viet: (
        <>
          nỗi lòng <br />
          con cái
        </>
      ),
    }),
    [],
  );

  const rightMenu = useMemo(
    () => ({
      kor: <>정보</>,
      viet: (
        <>
          {' '}
          lấy <br />
          thông tin
        </>
      ),
    }),
    [],
  );

  return (
    <S.Container>
      {/* <button
        className="home"
        onClick={() => {
          navigate('/profile');
        }}
      >
        <img src={'/assets/img/parent/homeIcon.svg'} />
      </button>
      <div className="logo">
        <img src={'/assets/img/parent/bridgeTalkLogo.svg'} />
      </div>
      <div className="main">
        <button className="main__report" onClick={() => navigate('../report')}>
          {leftMenu[language]}
        </button>
        <div className="main__character">
          <img src={'/assets/img/parent/dino.svg'} />
        </div>
        <button className="main__info" onClick={() => navigate('../information')}>
          {rightMenu[language]}
        </button>
      </div> */}
    </S.Container>
  );
}
