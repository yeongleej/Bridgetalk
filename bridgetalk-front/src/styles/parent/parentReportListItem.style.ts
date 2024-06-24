import styled from 'styled-components';
import { color } from '@/styles/parent/common.style';

export const Container = styled.div`
  display: flex;

  background-color: ${color(1).sub};
  padding: 2svh 1svw;
  border-radius: 2svw;

  box-shadow: 0 0.5svh 0.4svh ${color(0.5).black};

  position: relative;
  cursor: pointer;

  transition: all 0.2s;
  &:hover {
    transform: scale(1.02);
  }

  * {
    font-family: 'Cookie';
    font-size: 1.5svw;
    color: ${color(1).bright};
  }

  &::after {
    position: absolute;
    top: 0;
    left: 0;
    content: '';
    width: 100%;
    height: 100%;
    border-radius: 2svw;
    box-shadow: inset 0 0.5svh 1.5svh ${color(0.5).bright};
    z-index: 0;
    pointer-events: none;
  }

  &::before {
    position: absolute;
    top: 0;
    left: 0;
    content: '';
    width: 100%;
    height: 100%;
    border-radius: 2svw;
    box-shadow: inset 0 -0.5svh 1.5svh ${color(0.5).dark};
    z-index: 0;
    pointer-events: none;
  }

  transition: all 0.1s;
`;

export const Content = styled.div`
  display: grid;
  grid-template-columns: 1fr 5fr;
  align-items: center;
  gap: 1svw;
  width: 100%;
  /* height: 18svh; */
  height: 100%;

  .left {
    width: 100%;
    height: 100%;

    display: flex;
    align-items: center;

    img {
      width: 100%;
    }
  }

  .right {
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    gap: 1svh;

    height: 100%;

    &__date {
      font-family: 'Cookie';

      font-size: 2svw;
    }

    &__content {
      display: grid;
      grid-template-columns: 1fr 3fr;
      gap: 2svw;

      height: 100%;

      align-items: center;

      &-title {
        text-align: center;
      }

      &-tags {
        display: flex;
        flex-direction: column;
        gap: 1svh;
      }

      &-title,
      &-tags {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;

        &-tag {
          background-color: ${color(1).bright};
          border-radius: 1svw;
          padding: 0.5svh 0.5svw;
          color: ${color(1).black};

          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
        }
      }
    }
  }

  .view {
    background-color: ${color(1).black};
    border-radius: 1svw;
    height: 4.8svh;
    width: 7svw;

    color: ${color(1).bright};
    box-shadow: 0 0.5svh 0.4svh ${color(0.5).black};

    cursor: pointer;
    font-family: 'Pretendard';

    &:hover {
      transform: translateY(0.25svh);
      box-shadow: 0 0.25svh 0.4svh ${color(0.5).black};
    }

    &:active {
      transform: translateY(0.5svh);
      box-shadow: 0 0svh 0.4svh ${color(0.5).black};
    }
  }
`;
