import styled from 'styled-components';
import { color } from './common.style';

export const Wrapper = styled.div`
  width: 100%;
  background-color: ${color(1).sub};
  box-shadow: 0.5svh 0.5svh 0.5svh ${color(0.2).dark};
`;
