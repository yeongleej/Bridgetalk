import styled, { createGlobalStyle } from 'styled-components';

export const Container = styled.div`
    width: 100vw;
    height: 100vh;
`;

export const GlobalStyle = createGlobalStyle`
  @font-face {
    font-family: 'CherryBomb';
    src: url('@/fonts/CherryBombOne-Regular.ttf') format('truetype'); /* 폰트 파일의 경로에 맞게 수정 */
    font-weight: normal;
    font-style: normal;
  }

  @font-face {
    font-family: 'DNF';
    src: url('@/fonts/DNFForgedBlade-Bold.ttf')
  }
`;
