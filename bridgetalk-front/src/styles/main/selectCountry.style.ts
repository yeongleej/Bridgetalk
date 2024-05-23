import styled from 'styled-components';
import { button, color } from './common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 9svh;

  .top {
    display: flex;
    flex-direction: column;
    align-items: center;

    .title {
      img {
        width: 63svw;
      }
    }

    .countrylist {
      display: flex;
      gap: 4svw;

      .active {
        background-color: ${color(1).main};
      }

      button {
        width: 26svw;
        height: 27svh;

        ${button}
        background-color: ${color(1).sub};

        border-radius: 5svw;

        &::after {
          border-radius: 5svw;
        }

        &::before {
          border-radius: 5svw;
        }

        font-family: 'DNF';
        font-size: 3svw;
        color: ${color(1).white};
      }

      &__china {
        background-color: ${color(1).gray} !important;

        position: relative;

        img {
          z-index: 1;
          bottom: 0;
          position: absolute;
          transform: translate(0, 20%);

          width: 15.6svw;
        }
      }
    }
  }

  .buttons {
    display: flex;
    gap: 1.5svw;

    button {
      display: flex;
      align-items: center;
      gap: 2.7svw;

      ${button}
      width: 19.2svw;
      height: 12svh;

      font-family: 'DNF';
      color: ${color(1).white};
      font-size: 2.5svw;

      img {
        width: 3svw;
      }
    }
  }
`;
