import * as S from '@/styles/app/app.style';
import { AppRoutes } from './appRoutes';
import { ModalSpace } from '@/shared';
import { AppSseSubscriber } from './appSseSubscriber';

export function App() {
  return (
    <S.Container>
      <AppSseSubscriber />
      <S.GlobalStyle />
      <AppRoutes />
      <ModalSpace />
    </S.Container>
  );
}
