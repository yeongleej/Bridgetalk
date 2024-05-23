import styled, { css } from 'styled-components';
import { color, textShadowBlue } from './common.style';
import { insetShadow } from '../main/common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 7svh;

  width: 100%;
  height: 100%;

  background-color: ${color(1).sub};
  border-radius: 2svw;
  box-shadow: 0 0 2svw ${color(0.25).black};
  padding: 1svh 1svw;

  position: relative;

  ::-webkit-scrollbar {
    display: none;
  }

  &::after {
    ${insetShadow}
    border-radius: 2svw;
    box-shadow: inset 0 -0.5svh 1svh ${color(0.5).black};
  }

  &::before {
    ${insetShadow}
    border-radius: 2svw;
    box-shadow: inset 0 0.5svh 1svh ${color(0.5).bright};
  }

  padding-top: 10svh;

  .title {
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: start;

    width: 100%;

    top: 0;
    left: 2svw;

    transform: translate(0, -50%);

    z-index: 1;

    div {
      font-family: 'Pretendard-Black';
      font-size: 3svw;
      color: ${color(1).bright};
      position: relative;

      ${textShadowBlue}
    }

    img {
      width: 8svw;
      text-shadow: 1svw 0.5svh 0.4svh ${color(1).black};
    }
  }

  .children {
    display: flex;
    gap: 1svw;

    width: 100%;

    border-radius: 2svw;

    &__wrapper {
      width: 100%;
      padding: 0 1svw;
    }

    &__child {
      color: ${color(0.5).black};
      background-color: ${color(0.5).light};
      border-radius: 1svw;
      padding: 0.5svh 0.5svw;

      font-size: 2svw;
      cursor: pointer;

      font-family: 'Pretendard';

      border: none;
    }

    .active {
      color: ${color(1).black};
      background-color: ${color(1).light};
    }
  }

  .content {
    width: 100%;
    height: 60svh;
    overflow-y: scroll;
    border-radius: 1svw;

    position: relative;

    &__container {
      width: 100%;

      display: flex;
      flex-direction: column;
      gap: 1svh;
    }

    .list {
      width: 100%;
      min-height: 100%;

      display: flex;
      flex-direction: column;

      gap: 4svh;

      padding: 1svh 1svw;
      border-radius: 1svw;
      background-color: ${color(1).light};

      &__noReport {
        width: 100%;
        height: 100%;

        display: flex;
        justify-content: center;
        align-items: center;

        img {
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
          width: 35.8svw;
        }
      }
    }
  }
`;
