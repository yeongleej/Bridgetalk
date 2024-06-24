import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100svw;
  height: 100svh;

  background-image: url('/assets/img/child/game/gameBackground.png');
  background-size: cover;
  background-position: center center;

  .gamingPage {
    width: 100%;
    height: 100%;
    padding: 5svh;

    &__header {
      display: flex;
      align-items: center;
      justify-content: space-between;

      &-title {
        img {
          height: 15svh;
        }
      }
    }

    &__container {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 100%;
      height: 100%;
      padding-bottom: 10%;

      &-toPuzzle {
        display: flex;
        align-items: center;
        justify-content: center;

        img {
          width: 100%;
        }
      }
    }
  }
`;
