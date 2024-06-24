import styled, { css, keyframes } from 'styled-components';
import { color, insetShadow, textShadowRed } from './common.style';

const BUTTON_RADIUS = css`
  border-radius: 7svw;
`;

const fadein = keyframes`
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
`;

const fadeout = keyframes`
0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
`;

export const Container = styled.div`
  display: flex;

  .invisible {
    opacity: 0;
  }

  .title {
    position: fixed;
    top: 11.2svh;
    right: 8svw;

    img {
      width: 38.9svw;
    }

    animation: ${fadein} 0.8s ease-in-out;
  }

  .buttons {
    position: fixed;
    top: 70svh;
    left: 50svw;
    transform: translateX(-50%);

    display: flex;
    gap: 2svw;

    button {
      background-color: ${color(1).main};

      border: none;

      color: ${color(1).white};
      font-size: 3.3svw;
      font-family: 'DNF';

      img {
        width: 4svw;
      }

      width: 28.8svw;
      height: 17.8svh;

      display: flex;
      align-items: center;
      justify-content: center;
      gap: 1.5svw;

      ${BUTTON_RADIUS}

      position: relative;
      box-shadow: 0 0.5svh 0.4svh ${color(0.5).black};
      cursor: pointer;
      &::after {
        ${insetShadow}

        ${BUTTON_RADIUS}
        box-shadow: inset 0 1svh 0.8svh ${color(0.5).white};
      }

      &::before {
        ${insetShadow}

        ${BUTTON_RADIUS}
        box-shadow: inset 0 -1svh 0.8svh ${color(0.25).black};
      }

      &:hover {
        transform: scale(1.02);
      }

      &:active {
        transform: scale(1);
      }
    }
  }
`;
