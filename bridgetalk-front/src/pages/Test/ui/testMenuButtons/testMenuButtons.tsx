import { ChildrenProps } from '@/shared';
import * as S from '@/styles/test/testMenuButtons.style';

export function TestMenuButtons({ children }: ChildrenProps) {
    return <S.TestMenuButtonsContainer>{children}</S.TestMenuButtonsContainer>;
}
