import styled from 'styled-components';
import { color, textShadowBlue } from './common.style';

export const ContentContainer = styled.div`
  display: grid;
  grid-template-columns: 2fr 0.5fr;
  gap: 2svw;
  position: relative;

  width: 80svw;
  height: 70svh;

  * {
    ::-webkit-scrollbar {
      display: none;
    }
  }
  .menu {
    position: absolute;
    top: 0;
    left: 2svw;
    z-index: 1;

    transform: translateY(-100%);

    display: flex;
    gap: 1svw;

    button {
      background-color: ${color(1).sub};
      border: none;
      font-family: 'Pretendard-Black';

      border-top-left-radius: 1svw;
      border-top-right-radius: 1svw;

      color: ${color(0.5).bright};

      font-size: 1.5svw;

      width: 5svw;
      height: 5svh;

      cursor: pointer;
    }

    .active {
      color: ${color(1).bright};
    }
  }

  .leftside {
    display: flex;
    flex-direction: column;
    gap: 2svh;
    background-color: ${color(1).sub};
    box-shadow: 0 1svh 0.4svh ${color(0.5).dark};
    border-radius: 1svw;
    max-height: 70svh;

    position: relative;

    padding: 4svh 1svw 1svh 1svw;

    height: 100%;

    .title {
      position: absolute;
      top: 0;
      right: 2svw;
      transform: translateY(-50%);

      font-family: 'Pretendard-Black';
      font-size: 2.5svw;
      color: ${color(1).bright};
      z-index: 0;
      ${textShadowBlue}
      text-align: center;

      display: flex;
      gap: 1svw;
      align-items: center;

      .button {
        background-color: transparent;
        border: none;
        z-index: 1;
        background-color: ${color(1).sub};
        padding: 1svh 1svw;
        border-radius: 2svw;
        box-shadow: 0 0.5svh 0.4svh ${color(0.5).dark};
        cursor: pointer;

        img {
          height: 3svw;
        }
      }
    }

    .content-container {
      display: flex;
      flex-direction: column;
      height: 100%;
      background-color: ${color(1).sub2};
      border-radius: 1svw;
      padding: 2svh 1svw;

      width: 100%;
      height: 100%;

      gap: 2svh;

      .content,
      .solution {
        height: 100%;
        background-color: ${color(1).sub};
        border-radius: 1svw;
        padding: 1.5svh 1.5svw;

        font-size: 1.6svw;

        color: ${color(1).bright};

        box-shadow: 0 0.5svh 0.4svh ${color(0.5).dark};
        position: relative;
        overflow-y: auto;

        &::after {
          position: absolute;
          top: 0;
          left: 0;

          width: 100%;
          height: 100%;

          content: '';
          border-radius: 1svw;

          box-shadow: inset 0 0.5svh 0.4svh ${color(0.5).bright};
        }
      }

      .replys {
        display: flex;
        flex-direction: column;
        gap: 1svh;
        padding-left: 1svw;

        font-size: 1.2svw;

        &__wrapper {
          height: 40svh;
          overflow-y: scroll;

          display: flex;
          flex-direction: column;
          gap: 1svh;
        }

        &-header {
          display: flex;
          gap: 1svw;
        }

        &-none {
          width: 100%;
          height: 100%;

          display: flex;
          justify-content: center;
          align-items: center;

          font-size: 2svw;
        }

        &-body {
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
      }
    }

    div {
      font-family: 'Pretendard-Black';
    }
  }
`;

export const Keywords = styled.div`
  display: flex;
  gap: 1svw;
  font-family: 'Pretendard-Black';
`;

export const Summary = styled.div`
  width: 100%;
  font-family: 'Pretendard-Black';
`;

export const Solution = styled.div`
  width: 100%;
  font-family: 'Pretendard-Black';
`;
