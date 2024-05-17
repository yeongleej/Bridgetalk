import * as S from '@/styles/app/app.style';
import { AppRoutes } from './appRoutes';
import { ModalSpace } from '@/shared';
import { AppPreloader } from './appPreloader';

export function App() {
  return (
    <S.Container>
      <AppPreloader />
      <S.GlobalStyle />
      <AppRoutes />
      <ModalSpace />
    </S.Container>
  );
}
