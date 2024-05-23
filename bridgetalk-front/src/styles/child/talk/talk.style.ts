import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100svw;
  height: 100svh;

  background-image: url('/assets/img/pic/talkBackground.png');
  background-size: cover;

  .talking {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: start;
    height: 100%;

    &__header {
      display: flex;
      align-items: center;
      justify-content: space-between;

      width: 100svw;
      padding: 0 3svh;

      &-end {
        background-color: transparent;
        border: none;

        img {
          height: 10svh;

          &:hover {
            cursor: pointer;
          }
        }
      }

      &-guide {
        padding: 3svh;

        text-align: center;
        font-size: 3svh;
        font-family: 'DNF';

        border-radius: 1svw;
        background-color: #00000090;
        color: white;

        position: fixed;
        left: 50%;
        transform: translateX(-50%);
      }

      &-message {
        img {
          height: 15svh;

          &:hover {
            cursor: pointer;
          }
        }
      }
    }

    &__container {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;

      /* &-guide {
        padding: 3svh;

        text-align: center;
        font-size: 3svh;
        font-family: 'DNF';

        border-radius: 1svw;
        background-color: #00000090;
        color: white;

        position: fixed;
        top: 50%;
        left: 50%;
        transform: translateX(-50%);
        z-index: 1;
      } */

      &-dino {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);

        * {
          height: 50svh;
        }
      }

      &-talk {
        width: 90svw;
        padding: 5svh;

        text-align: center;
        font-size: 4svh;
        font-family: 'DNF';

        border-radius: 2svw;
        background-color: white;
        box-shadow: 0 1px 3px #00000050;

        position: fixed;
        bottom: 3svh;
      }
    }
  }
`;
