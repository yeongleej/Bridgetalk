import * as S from '@/styles/parent/parentInformationWord.style';

interface Props {
    kwordAbbreviation: string;
}

export function ParentInformationWordListItem({ kwordAbbreviation }: Props) {
    return <S.ItemWrapper>{kwordAbbreviation}</S.ItemWrapper>;
}
