import * as S from '@/styles/app/app.style';
import { AppRoutes } from './appRoutes';
import { ModalSpace } from '@/shared';

export function App() {
    return (
        <S.Container>
            <S.GlobalStyle />
            <AppRoutes />
            <ModalSpace />
        </S.Container>
    );
}
