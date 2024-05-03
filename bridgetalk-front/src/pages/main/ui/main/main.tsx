import { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import * as S from '@/styles/main/main.style';
import { LoginGuard } from '../guard/loginGuard';
import { decodeToken } from '@/shared';

export function Main() {
  const navigate = useNavigate();

  useEffect(() => {
    if (decodeToken('access') === null) {
      navigate('/start');
    } else {
      navigate('/profile');
    }
  }, []);

  return (
    <S.Background>
      <Outlet />
    </S.Background>
  );
}
