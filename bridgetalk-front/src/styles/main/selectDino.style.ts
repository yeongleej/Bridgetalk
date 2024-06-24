import styled from 'styled-components';
import { button, color } from './common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 2svh;

  .title {
    img {
      width: 66svw;
    }
  }

  .selectbox {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    width: 54svw;
    height: 46svh;

    background-color: ${color(0.5).black};
    border-radius: 5svw;

    &__content {
      display: flex;
      align-items: center;
      justify-content: space-between;

      width: 33svw;

      button {
        background-color: transparent;
        border: none;

        img {
          width: 2svw;
        }
      }
    }

    &__title {
      color: ${color(1).white};
      font-family: 'DNF';
      font-size: 3.2svw;
    }

    &__content {
      &-dino {
        img {
          width: 14svw;
        }
      }
    }
  }

  .buttons {
    display: flex;
    gap: 3svw;

    button {
      ${button}

      width: 19.2svw;
      height: 12svh;
      font-size: 2.5svw;

      img {
        width: 3svw;
      }
    }
  }
`;
