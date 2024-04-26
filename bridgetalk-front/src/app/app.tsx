import * as S from '@/styles/app/app.style';
import { AppRoutes } from './appRoutes';
import { ModalSpace } from '@/shared';

export function App() {
    return (
        <S.Container>
            <AppRoutes />
            <ModalSpace />
        </S.Container>
    );
}
