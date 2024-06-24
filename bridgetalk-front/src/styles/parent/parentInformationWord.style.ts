import styled, { css } from 'styled-components';
import { CommonContainer, color } from './common.style';

const insetShadow = css`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  content: '';
`;

export const Container = styled.div`
  align-items: center;
  padding: 2svh 2svw;

  * {
    font-family: 'Pretendard';
  }
  .main {
    display: grid;
    width: 75svw;
    height: 75svh;
    grid-template: repeat(3, 1fr) / repeat(3, 1fr);
    gap: 1svh 1svw;

    &__worditem {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      width: 23svw;
      height: 20svh;

      gap: 1svh;

      box-shadow: 0 0 1svh ${color(0.5).black};
      border-radius: 1svw;
      padding: 1vw;

      background-color: ${color(1).bright};
      font-size: 1svw;

      padding: 1svh 1svw;

      cursor: pointer;

      transition: all 0.2s;
      &:hover {
        transform: scale(1.05);
      }

      &-top {
        &-title {
          font-size: 1.5svw;
          font-weight: bold;
        }
        width: 100%;

        display: flex;
        align-items: end;

        gap: 1svw;
      }

      &-bottom {
        width: 100%;

        text-align: start;
      }
    }
  }

  .pagenation {
    display: flex;
    gap: 1svw;
    justify-content: center;

    .active {
      background-color: ${color(1).sub};
    }

    button {
      width: 3svw;
      height: 3svw;

      border: none;
      border-radius: 1svw;

      font-family: 'Pretendard';
      font-size: 1.5svw;

      cursor: pointer;

      box-shadow: 0 0 1svh ${color(1).black};

      transition: all 0.2s;

      &:hover {
        background-color: ${color(1).sub};
      }
    }
  }
`;

export const Header = styled.div`
  display: flex;
  gap: 2svw;
`;

export const ItemWrapper = styled.div`
  width: 100%;
  text-align: left;
  padding: 1svh 3svw;
  background-color: ${color(0.8).sub};
`;
