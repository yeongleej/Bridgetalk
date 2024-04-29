import styled from 'styled-components';
import { color } from '@/styles/parent/common.style';

export const Container = styled.div`
    display: flex;
    background-color: ${color(1).sub};
    padding: 0.5svw;
`;

export const Content = styled.div`
    display: flex;
    flex-direction: column;
`;

export const ContentHeader = styled.div`
    display: flex;
`;

export const ContentBody = styled.div``;
