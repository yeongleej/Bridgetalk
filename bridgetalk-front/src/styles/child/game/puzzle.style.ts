import styled from 'styled-components';

export const Container = styled.div`
  width: 100svw;
  height: 100svh;

  background-image: url('/assets/img/child/game/candyBackground.png');
  background-size: cover;

  .puzzlePage {
    display: flex;
    gap: 2svw;
    height: 100%;
    padding: 5svw;

    position: relative;

    &__side {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 5svh;

      width: 30svw;
      height: 100%;
      padding: 2svw;
      border-radius: 5svh;

      background-color: pink;
      border-bottom: 2svh solid palevioletred;
      box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.25) inset, 0px -10px 10px 0px rgba(0, 0, 0, 0.25) inset,
        0px 4px 4px 0px rgba(0, 0, 0, 0.25);

      img {
        width: 100%;
        border-radius: 3svh;
        border-top: 1svh solid palevioletred;
      }

      span {
        text-align: center;
        font-size: 5svh;
        font-family: 'DNF';
        color: palevioletred;
      }

      p {
        text-align: center;
        font-size: 2svh;
        font-family: 'DNF';
      }

      &-timer {
        font-size: 5svh;
        font-family: 'DNF';
        color: palevioletred;
      }
    }

    &__puzzle {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 70svw;
      border-radius: 5svh;
      box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.25) inset, 0px -10px 10px 0px rgba(0, 0, 0, 0.25) inset,
        0px 4px 4px 0px rgba(0, 0, 0, 0.25);
      border-bottom: 2svh solid palevioletred;
      background-color: pink;

      * {
        width: 90%;
        height: 90%;
        object-fit: contain;
        background-color: palevioletred;
        border-radius: 1svh;
      }
    }
  }
`;
