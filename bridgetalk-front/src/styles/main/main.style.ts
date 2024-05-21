import styled, { keyframes } from 'styled-components';
import { bg } from './common.style';

const move = keyframes`
  0% {
    background-position: 0% 0%;
  }
  50% {
    background-position: 5% 0%;
  }
  100% {
    background-position: 0% 0%;
  }
`;

export const Background = styled.div`
  background-position: center center;
  background-image: url('/assets/img/main_bg.png');
  background-size: cover;
  background-repeat: no-repeat;
  ${bg}
  animation: ${move} 30s ease-in-out infinite;
`;
