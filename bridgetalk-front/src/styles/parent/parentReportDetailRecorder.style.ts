import styled, { css } from 'styled-components';
import { color, textShadowBlue } from './common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;

  padding: 1svh 1svw;
  justify-content: space-evenly;
  align-items: center;

  width: 100%;
  height: 100%;
  background-color: ${color(1).sub};

  border-radius: 1svw;

  box-shadow: 0 0.5svh 0.4svh ${color(0.5).dark};

  position: relative;

  .title {
    position: absolute;
    top: 0;
    transform: translateY(-50%);

    text-align: center;
    width: 100%;

    font-family: 'DNF';
    color: ${color(1).bright};
    font-size: 2.7svw;

    ${textShadowBlue}
  }

  position: relative;
  &::after {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border-radius: 1svw;

    content: '';
    pointer-events: none;
    box-shadow: inset 0 0.5svh 0.4svh ${color(0.5).bright};
  }
  &::before {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border-radius: 1svw;

    content: '';
    pointer-events: none;
    box-shadow: inset 0 -0.5svh 0.4svh ${color(0.25).dark};
  }

  .volume-checker {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: ${color(1).sub2};
    width: 20svw;
    padding: 1svh 1svw;
    border-radius: 2svw;
    box-shadow: 0 0.5svh 0.4svh ${color(0.5).dark};
    .time {
      font-size: 1svw;
      font-family: 'Pretendard-Black';
    }
    .dino {
      img {
        width: 10svw;
      }
    }
  }
`;

const virtual = css`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  content: '';
  border-radius: 50%;
`;

export const ButtonWrapper = styled.button<{ $isRecording: boolean }>`
  background-color: ${(props) => (props.$isRecording ? color(1).red : color(1).main)};
  width: 10svw;
  height: 10svw;
  border-radius: 50%;
  border: none;
  box-shadow: 0 1svh 0.5svw ${color(0.8).dark};

  position: relative;
  color: ${color(1).bright};
  transition: all 0.2s ease-in-out;

  div {
    font-family: 'CherryBomb';
    font-size: 2svw;
  }

  &::before {
    ${virtual}
    box-shadow: inset 0 1svh 1svw ${color(1).shadowTop};
  }

  &::after {
    ${virtual}
    box-shadow: inset 0 -1svh 1svw ${color(0.3).dark};
  }

  &:hover {
    cursor: pointer;

    transform: translateY(0.25svh);
    box-shadow: 0 0.75svh 0.5svw ${color(0.8).dark};
  }

  &:active {
    transform: translateY(0.5svh);
    box-shadow: 0 0.5svh 0.5svw ${color(0.8).dark};
  }
`;

export const Volume = styled.div`
  display: flex;
  align-items: center;
  gap: 0.1svw;
  height: 6svh;
`;

export const VolumeBar = styled.div`
  width: 0.3svw;
  background-color: ${color(0.7).dark};
  transition: height 0.2s;
`;
