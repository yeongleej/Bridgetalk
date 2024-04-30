import { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import * as S from '@/styles/main/main.style';

export function Main() {
  const navigate = useNavigate();

  // useEffect(() => {
  //   navigate('start');
  // }, []);

  return (
    <S.Background>
      <Outlet />
      <div>dsfsd</div>
    </S.Background>
  );
}
