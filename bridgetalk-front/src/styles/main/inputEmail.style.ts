import styled from 'styled-components';
import { button, color, insetShadow } from './common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2svh;

  div {
    font-family: 'CherryBomb';
    color: ${color(1).white};
  }

  input {
    border-radius: 15svw;
    border: 1svw solid ${color(1).main};
    font-size: 2.5svw;
    font-family: 'DNF';
    padding: 1.2svh 2svw;
    height: 13.3svh;
    width: 47.4svw;
  }

  .email {
    display: flex;
    align-items: center;
    gap: 1svw;

    position: relative;

    &__title {
      img {
        width: 13.6svw;
      }
    }

    &__input {
      padding-right: 11.5svw;
    }

    &__verify {
      position: absolute;
      top: 50%;
      right: 0.9svw;

      background-color: ${color(1).main};

      border: none;
      border-radius: 15svw;
      height: 9.6svh;
      width: 11.5svw;

      color: ${color(1).white};
      font-family: 'CherryBomb';
      font-size: 2svw;

      padding: 0 2svw;

      transform: translateY(-50%);
      cursor: pointer;

      &::after {
        ${insetShadow}
        border-radius: 15svw;
        box-shadow: inset 0 0.5svh 0.4svh ${color(0.5).white};
      }

      &::before {
        ${insetShadow}
        border-radius: 15svw;
        box-shadow: inset 0 -0.5svh 0.4svh ${color(0.5).black};
      }

      &:hover {
        &::after {
          ${insetShadow}
          border-radius: 15svw;
          box-shadow: inset 0 0.5svh 0.4svh ${color(0.4).white};
        }

        &::before {
          ${insetShadow}
          border-radius: 15svw;
          box-shadow: inset 0 -0.5svh 0.4svh ${color(0.4).black};
        }
      }
    }
  }

  .confirm {
    display: flex;
    align-items: center;
    gap: 1svw;

    &__title {
      img {
        width: 13.6svw;
      }
    }
  }

  .next {
    ${button}
    width: 19.2svw;
    height: 12svh;
    font-size: 2.5svw;

    cursor: pointer;

    img {
      width: 3svw;
    }
  }
`;
