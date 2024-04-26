import styled from 'styled-components';
import { CommonContainer, color } from '@/styles/parent/common.style';

export const Container = styled.div`
    ${CommonContainer}
`;

export const ContentContainer = styled.div`
    display: grid;
    grid-template-columns: 1fr 1.5fr;

    gap: 2svw;

    width: 100%;
    height: 100%;
`;
