import styled from 'styled-components';
import { backButton, color } from './common.style';

export const Container = styled.div`
  width: 100svw;
  height: 100svh;

  display: flex;
  justify-content: center;
  align-items: center;

  background-color: ${color(0.2).black};
`;
