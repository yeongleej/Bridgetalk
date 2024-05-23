import * as S from '@/styles/parent/parent.style';

import { useEffect, useState } from 'react';
import { Outlet, useLocation, useNavigate } from 'react-router-dom';
import { useReportStore } from '../../store';
import { getProfile, getSlang } from '../../query';

export function Parent() {
  const navigate = useNavigate();

  const { setChildrenList, language } = useReportStore((state) => ({
    setChildrenList: state.setChildrenList,
    language: state.language,
  }));

  useEffect(() => {
    getProfile(setChildrenList);

    navigate('report');
  }, []);

  return (
    <S.Background>
      <Navbar navigate={navigate} />
      <div className="outline">
        <Outlet />
      </div>
      <LangIcon />
    </S.Background>
  );
}
function Navbar({ navigate }: any) {
  const location = useLocation();
  const path = new Set(location.pathname.split('/').slice(1));

  function pathCheck(_path: string) {
    if (path.has(_path)) {
      return 'on';
    }
    return 'off';
  }

  useEffect(() => {}, [location]);
  return (
    <S.Navbar>
      <button
        onClick={() => {
          navigate('/profile');
        }}
      >
        <img src={`/assets/img/parent/navbar/home_${pathCheck('main')}.svg`} />
      </button>
      <button
        onClick={() => {
          navigate('report');
        }}
      >
        <img src={`/assets/img/parent/navbar/message_${pathCheck('report')}.svg`} />
      </button>
      <button
        onClick={() => {
          navigate('information/nurture');
        }}
      >
        <img src={`/assets/img/parent/navbar/nurture_${pathCheck('nurture')}.svg`} />
      </button>
      <button
        onClick={() => {
          navigate('information/word');
        }}
      >
        <img src={`/assets/img/parent/navbar/info_${pathCheck('word')}.svg`} />
      </button>
      <button
        onClick={() => {
          navigate('board');
        }}
      >
        <img src={`/assets/img/parent/navbar/community_${pathCheck('community')}.svg`} />
      </button>
    </S.Navbar>
  );
}

function LangIcon() {
  const { language, setLanguage } = useReportStore((state) => ({
    language: state.language,
    setLanguage: state.setLangauge,
  }));

  const lang = localStorage.getItem('language');

  let userLang: 'viet' | 'ph' | 'kor' = 'kor';
  if (lang === 'viet' || lang === 'ph' || lang === 'kor') {
    userLang = lang || 'kor';
  }

  return (
    <button
      className="lang"
      onClick={() => {
        setLanguage(language === 'kor' ? userLang : 'kor');
      }}
    >
      {language === 'kor' ? (
        <img src={`/assets/flag/kor.png`} />
      ) : language === 'ph' ? (
        <img src={`/assets/flag/ph.png`} />
      ) : (
        <img src={`/assets/flag/viet.png`} />
      )}
    </button>
  );
}
