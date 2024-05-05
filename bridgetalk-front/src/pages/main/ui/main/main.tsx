import { Outlet, useNavigate } from 'react-router-dom';
import * as S from '@/styles/main/main.style';
import { useEffect } from 'react';

export function Main() {
  const navigate = useNavigate();

  useEffect(() => {
    navigate('/start');
  }, []);

  return (
    <S.Background>
      <Outlet />
    </S.Background>
  );
}
