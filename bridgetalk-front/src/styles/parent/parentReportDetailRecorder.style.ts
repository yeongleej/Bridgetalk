import styled from 'styled-components';
import { color } from './common.style';

export const Container = styled.div`
    display: flex;
    flex-direction: column;
    padding: 1svh 1svw;
    justify-content: space-evenly;
    align-items: center;

    width: 100%;
    height: 100%;
    background-color: ${color(1).sub};

    div {
        display: flex;
        align-items: center;
        height: 100%;
    }
`;
