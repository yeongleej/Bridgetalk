import styled from 'styled-components';
import { ReportContainer, color } from '@/styles/parent/common.style';

export const Container = styled.div`
    ${ReportContainer}
`;

export const ContentContainer = styled.div`
    display: grid;
    grid-template-columns: 1fr 1.5fr;

    gap: 2svw;

    width: 100%;
    height: 100%;
`;
