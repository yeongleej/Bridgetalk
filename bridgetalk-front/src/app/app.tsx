import * as S from '@/styles/app/app.style';
import { AppRoutes } from './appRoutes';
import { ModalSpace } from '@/shared';
import { AppPreloader } from './appPreloader';
import { registServiceWorker } from '@/shared/model/registServiceWorker/registServiceWorker';

export function App() {
  registServiceWorker();

  return (
    <S.Container>
      <AppPreloader />
      <S.GlobalStyle />
      <AppRoutes />
      <ModalSpace />
    </S.Container>
  );
}
