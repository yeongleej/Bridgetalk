import styled from 'styled-components';

export const Container = styled.div`
  width: 100svw;
  height: 100svh;

  background-image: url('/assets/img/child/game/gameBackground.png');
  background-size: cover;
  background-position: center center;

  .three {
    width: 100%;
    height: 100%;
    position: relative;

    .header {
      display: flex;
      justify-content: space-between;

      width: 100%;
      position: absolute;
      top: 0;
      left: 0;
      z-index: 1000;

      img {
        width: 20%;
      }
    }
  }
`;
