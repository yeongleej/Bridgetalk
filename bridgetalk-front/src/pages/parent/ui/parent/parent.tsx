import * as S from '@/styles/parent/parent.style';

import { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';

export function Parent() {
    const navigate = useNavigate();

    useEffect(() => {
        navigate('main');
    }, []);

    return (
        <S.Background>
            <Outlet />
        </S.Background>
    );
}
