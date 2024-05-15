import styled from 'styled-components';
import { CommonContainer, color } from '@/styles/parent/common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;

  width: 90svw;
  height: 100svh;

  margin-top: 5svh;
`;

export const ContentContainer = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;

  gap: 2svw;

  padding: 0 2svw;

  width: 100%;
  height: 100%;
`;
