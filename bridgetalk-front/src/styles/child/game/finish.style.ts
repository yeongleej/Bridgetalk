import styled from 'styled-components';

export const Container = styled.div`
  width: 100svw;
  height: 100svh;

  background-image: url('/assets/img/child/game/candyBackground.png');
  background-size: cover;

  .finishPage {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 2svw;
    height: 100%;
    padding: 5svw;

    &__container {
      display: flex;
      height: 100%;

      img {
        width: 10px;
      }

      &-img {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 60svw;
        border-radius: 5svh;
        border-bottom: 2svh solid palevioletred;
        background-color: pink;
        box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.25) inset, 0px -10px 10px 0px rgba(0, 0, 0, 0.25) inset,
          0px 4px 4px 0px rgba(0, 0, 0, 0.25);

        img {
          width: 100%;
          border-radius: 3svh;
          border-top: 5px solid palevioletred;
        }
      }

      &-side {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        gap: 3svh;

        width: 30svw;
        height: 100%;
        padding: 3svh 2svw;
        border-radius: 5svh;

        background-color: pink;
        border-bottom: 2svh solid palevioletred;
        box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.25) inset, 0px -10px 10px 0px rgba(0, 0, 0, 0.25) inset,
          0px 4px 4px 0px rgba(0, 0, 0, 0.25);

        &-title {
          font-family: 'DNF';
          font-size: 5svh;
          color: #ff6161;
        }

        &-description {
          font-size: 3svh;
          line-height: 5svh;
        }

        &-savePic {
          padding: 3svh 3svw;

          background-color: white;
          border-radius: 3svh;
          box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.25) inset, 0px -10px 10px 0px rgba(0, 0, 0, 0.25) inset,
            0px 4px 4px 0px rgba(0, 0, 0, 0.25);

          font-size: 3svh;
          font-family: 'DNF';
          color: #ff6161;

          &:hover {
            cursor: pointer;
          }
        }

        &-toBack {
          padding: 3svh 3svw;

          background-color: white;
          border-radius: 3svh;
          box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.25) inset, 0px -10px 10px 0px rgba(0, 0, 0, 0.25) inset,
            0px 4px 4px 0px rgba(0, 0, 0, 0.25);

          font-size: 3svh;
          font-family: 'DNF';
          color: #ff6161;

          &:hover {
            cursor: pointer;
          }
        }
      }
    }
  }
`;
