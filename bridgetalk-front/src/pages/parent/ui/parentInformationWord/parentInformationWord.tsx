import { BackButton } from '@/shared';
import * as S from '@/styles/parent/common.style';

export function ParentInformationWord() {
    return (
        <div>
            ParentInformationWord
            <S.Absolute top={3} left={3}>
                <BackButton />
            </S.Absolute>
        </div>
    );
}
