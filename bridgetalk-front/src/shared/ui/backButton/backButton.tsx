import { NavigateFunction } from 'react-router-dom';
import * as S from '@/styles/shared/backButton.style';
import { GoTriangleLeft } from 'react-icons/go';

interface Props {
    path: string;
    navigate: NavigateFunction;
}

export function BackButton({ path, navigate }: Props) {
    return (
        <S.Button
            onClick={() => {
                navigate(path);
            }}
        >
            <img src="/assets/img/backButton.svg" />
        </S.Button>
    );
}
