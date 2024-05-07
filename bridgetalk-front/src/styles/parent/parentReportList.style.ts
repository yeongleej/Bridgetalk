import styled from 'styled-components';
import { CommonContainer, color } from '@/styles/parent/common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;

  width: 90svw;
  height: 70svh;

  margin-top: 5svh;
`;

export const ContentContainer = styled.div`
  display: grid;
  grid-template-columns: 1.5fr 1fr;

  gap: 4svw;

  width: 100%;
  height: 100%;
`;
