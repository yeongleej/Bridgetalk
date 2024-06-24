import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100svw;
  height: 100svh;

  background-image: url('/assets/img/pic/talkBackground.png');

  .messagePage {
    display: flex;
    flex-direction: column;
    align-item: center;
    justify-content: baseline;
    gap: 10svh;

    flex: none;
    width: 100svw;
    padding: 0 3svw;

    &__header {
      display: flex;

      &-toBack {
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 1svh;

        width: 15.8svw;
        padding: 2svh;
        font-size: 2svw;
        cursor: pointer;

        background-color: white;
        border-radius: 5svw;
        box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.25) inset, 0px -10px 10px 0px rgba(0, 0, 0, 0.25) inset,
          0px 4px 4px 0px rgba(0, 0, 0, 0.25);

        * {
          color: #ff9f99;
        }

        span {
          color: #ff9f99;
          font-size: 3svh;
          font-family: 'DNF';
        }
      }
    }

    &__container {
      flex: 1;
    }
  }
`;
