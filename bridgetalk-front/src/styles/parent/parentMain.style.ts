import styled from 'styled-components';
import { buttonNormal, color } from './common.style';

export const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  .home {
    position: fixed;
    top: 3svh;
    left: 2svw;
    width: 7svw;

    border: none;
    background-color: transparent;

    img {
      width: 100%;
    }
  }

  .logo {
    img {
      width: 40svw;
    }
  }

  .main {
    display: flex;
    gap: 4svw;

    button {
      background-color: ${color(1).main};
      border-radius: 5.2svw;
      border: none;

      font-family: 'CherryBomb', 'DNF';
      font-size: 4.1svw;
      color: ${color(1).bright};

      width: 26svw;
      height: 27.8svh;

      position: relative;
      cursor: pointer;

      &::after,
      &::before {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        content: '';
        pointer-events: none;
        border-radius: 5.2svw;
      }

      &::after {
        box-shadow: inset 0 -1svh 0.8svh ${color(0.5).black};
      }

      &::before {
        box-shadow: inset 0 1svh 0.8svh ${color(0.25).bright};
      }
    }

    &__character {
      img {
        width: 21svw;
      }
    }
  }

  button {
    ${buttonNormal}
  }
`;
