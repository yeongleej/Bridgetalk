import styled, { css, keyframes } from 'styled-components';
import { color, embossing } from '../parent/common.style';
import * as main from '../main/common.style';

const fadeInWithMove = keyframes`
    0% {
        transform: translateY(3svh);
        opacity: 0;
    }
    100% {
        transform: translateY(0);
        opacity: 1;
    }
`;
const fadeOutWithMove = keyframes`
    0% {
        transform: translateY(0);
        opacity: 1;
    }
    100% {
        transform: translateY(-3svh);
        opacity: 0;
    }
`;

const fadeIn = keyframes`
    0% {
        opacity: 0;
    }
    100%{
        opacity: 1;
    }
`;

const fadeOut = keyframes`
    0% {
        opacity: 1;
    }
    100%{
        opacity: 0;
    }
`;

export const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;

  width: 100%;
  height: 100%;

  z-index: 2;

  display: flex;
  justify-content: center;
  align-items: center;
  animation: ${fadeIn} 0.5s ease-in-out;

  background-color: ${color(0.7).dark};
`;

const shadow = css`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  content: '';
  pointer-events: none;
`;

export const AudioContainer = styled.div`
  width: 60svw;
  /* height: 30svh; */
  background-color: ${color(1).sub};
  border-radius: 1svw;
  gap: 2svh;
  padding: 2svw 2svw;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  animation: ${fadeInWithMove} 0.5s ease-in-out;

  box-shadow: 0 0.5svh 0.4svh ${color(0.5).dark};

  position: relative;

  &::after {
    ${shadow}
    border-radius: 1svw;
    box-shadow: inset 0 0.5svh 0.4svh ${color(0.5).bright};
  }

  &::before {
    ${shadow}
    border-radius: 1svw;
    box-shadow: inset 0 -0.5svh 0.4svh ${color(0.25).dark};
  }

  .title {
    font-family: 'DNF';
    color: ${color(1).bright};
    font-size: 3svw;
  }

  .audio {
    width: 100%;
    height: 10svh;
  }

  .buttons {
    button {
      font-family: 'CherryBomb';
      padding: 2svh 2svw;
      font-size: 5svh;
      border-radius: 2svw;
      cursor: pointer;
      color: ${color(1).bright};

      box-shadow: 0 0.5svh 0.4svh ${color(1).dark};
      position: relative;
      border: none;

      &::before {
        ${shadow}
        border-radius: 2svw;
        box-shadow: inset 0 0.5svh 0.4svh ${color(0.5).bright};
      }

      &::after {
        ${shadow}
        border-radius: 2svw;
        box-shadow: inset 0 -0.5svh 0.4svh ${color(0.25).dark};
      }
    }

    .close {
      background-color: ${color(1).red};

      &::after {
        ${shadow}
        border-radius: 2svw;
        /* box-shadow: ; */
      }
    }

    .send {
      background-color: ${color(1).main};
    }
  }
`;

const errorModalFadeInOut = keyframes`
    0% {
        opacity: 0;
        transform: translate(-50%, 10%);
    }
    20% {
        opacity: 1;
        transform: translate(-50%, 0);
    }
    80% {
        opacity: 1;
        transform: translate(-50%, 0);
    }
    100% {
        opacity: 0;
        transform: translate(-50%, 10%);
    }
`;

export const ErrorModalContainer = styled.div`
  position: fixed;

  bottom: 10%;
  left: 50%;
  transform: translateX(-50%);

  background-color: ${color(0.5).black};
  border-radius: 2svw;

  width: 80svw;
  height: 10svh;
  z-index: 3000;

  display: flex;
  justify-content: center;
  align-items: center;

  font-size: 2svw;
  font-family: 'DNF';
  color: ${color(1).bright};

  animation: ${errorModalFadeInOut} 2s ease-in-out;
`;

export const PasswordCheckModaContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  gap: 4svh;

  width: 84svw;
  height: 46.6svh;

  background-color: ${color(0.5).black};

  border-radius: 2.6svw;

  text-align: center;

  .title {
    color: ${color(1).bright};
    font-family: 'DNF';
    font-size: 3svw;
  }
  .content {
    display: flex;
    gap: 1svw;

    img {
      width: 23.4svw;
    }

    input {
      width: 43.9svw;
      height: 13.8svh;
      border-radius: 5.2svw;
      border: 1svw solid ${main.color(1).main};

      padding: 1svh 1.5svw;
      font-size: 2.5svw;
    }
  }

  .buttons {
    display: flex;
    gap: 2svw;

    button {
      width: 17svw;
      height: 11.2svh;
      border-radius: 5.2svw;
      background-color: ${main.color(1).main};
      position: relative;

      font-size: 2.5svw;

      color: ${color(1).bright};
      font-family: 'DNF';

      cursor: pointer;

      &::after {
        ${main.insetShadow}
        border-radius: 5.2svw;
        box-shadow: inset 0 0.5svh 0.4svh ${color(0.5).bright};
      }

      &::before {
        ${main.insetShadow}
        border-radius: 5.2svw;
        box-shadow: inset 0 -0.5svh 0.4svh ${color(0.5).black};
      }
    }
  }
`;
