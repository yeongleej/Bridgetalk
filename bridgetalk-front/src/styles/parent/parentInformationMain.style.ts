import styled from 'styled-components';
import { buttonNormal } from './common.style';

export const Container = styled.div`
    display: flex;
    justify-content: center;
    gap: 10svw;

    button {
        img {
            width: 100%;
        }
        ${buttonNormal}
    }
`;
