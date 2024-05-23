import styled, { css } from 'styled-components';
import { CommonContainer, color, textShadowBlue } from './common.style';
import { insetShadow } from '../main/common.style';

const gridLayout = css`
  display: grid;
  grid-template-columns: 2fr 10fr 2fr;
`;

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 90svh;
  width: 70svw;
  padding: 2svh 2svw;

  gap: 2svh;

  .title {
    text-align: center;
    font-size: 3.5svw;
    color: ${color(1).bright};
    ${textShadowBlue}
    height: 15svh;
  }

  .categories {
    display: flex;
    justify-content: start;
    gap: 1svw;

    button {
      width: 9svw;
      height: 9svh;

      white-space: normal;
      word-wrap: break-word;

      border: none;
      border-radius: 2svw;
      font-size: 2svw;

      background-color: ${color(1).bright};

      cursor: pointer;

      box-shadow: 0 0.5svh 0.4svh ${color(0.5).black};
      padding: 0.5svh 0.5svw;

      position: relative;
      transition: all 0.2s;

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
    }

    .active {
      background-color: ${color(1).sub};
    }
  }

  .main {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 1svh;

    padding: 2svh 2svw;

    border-radius: 1.5svw;
    border: none;

    height: 50svh;

    box-shadow: 0 0.5svh 0.4svh ${color(0.5).black};

    background-color: ${color(1).sub};
    position: relative;

    &::after {
      ${insetShadow}
      border-radius: 1svw;
      box-shadow: inset 0 -0.5svh 1svh ${color(0.5).black};
    }

    &::before {
      ${insetShadow}
      border-radius: 1svw;
      box-shadow: inset 0 0.5svh 1svh ${color(0.5).bright};
    }

    table {
      text-align: center;
      width: 100%;
    }

    &__header {
      ${gridLayout}
    }
    &__item {
      ${gridLayout}
      background-color: ${color(1).light};
      padding: 1svw;
      border-radius: 1svw;

      font-size: 1.3svw;
      font-family: 'Pretendard';

      cursor: pointer;
      position: relative;

      &-title {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        text-align: start;
      }

      &-category {
        position: absolute;
        bottom: 1svh;
        right: 1svw;

        display: flex;

        color: ${color(0.5).black};
      }

      transition: all 0.1s;
      &:hover {
        transform: scale(1.01);
      }
    }

    .thead {
      font-size: 1.3svw;
      font-family: 'Pretendard';

      tr {
        padding: 1svw;

        td {
          text-align: center;
        }
      }
    }
    .tbody {
      display: flex;
      flex-direction: column;
      gap: 1svw;
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

export const NewsList = styled.div`
  display: flex;
  align-items: center;

  width: 100%;
  height: 100%;
  gap: 2svw;
`;
