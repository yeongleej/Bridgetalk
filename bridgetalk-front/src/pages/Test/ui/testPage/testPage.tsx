import { ChildrenProps } from '@/shared';
import * as S from '@/styles/test/testPage.style';

export function TestPage({ children }: ChildrenProps) {
    return <S.TestPageWrapper>{children}</S.TestPageWrapper>;
}
