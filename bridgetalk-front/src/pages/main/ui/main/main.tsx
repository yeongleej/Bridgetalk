import { Outlet } from 'react-router-dom';
import * as S from '@/styles/main/main.style';

export function Main() {
  return (
    <S.Background>
      <Outlet />
    </S.Background>
  );
}
