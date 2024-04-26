import styled from 'styled-components';
import { color } from '../parent/common.style';

export const Container = styled.div`
    position: fixed;
    top: 0;
    left: 0;

    width: 100%;
    height: 100%;

    display: flex;
    justify-content: center;
    align-items: center;

    background-color: ${color(0.7).dark};
`;
