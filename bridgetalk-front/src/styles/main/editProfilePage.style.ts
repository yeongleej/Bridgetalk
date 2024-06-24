import styled from 'styled-components';
import { button, color } from './common.style';
import { darken } from 'polished';

export const Container = styled.div`
  width: 100svw;
  height: 100svh;

  background-color: ${color(0.2).black};

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 2.6svh;

  .back {
    position: fixed;
    top: 3svh;
    left: 2svw;

    border: none;
    background-color: transparent;

    img {
      width: 7svw;
    }
  }

  .main {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 4.6svh;

    &__content {
      display: flex;
      flex-direction: column;
      align-items: center;

      &-title {
        img {
          width: 50.3svw;
        }
      }

      &-box {
        background-color: ${color(0.5).black};
        border-radius: 2.6svw;

        width: 71.8svw;
        height: 30svw;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        gap: 2.8svh;

        &-title {
          font-family: 'DNF';
          color: ${color(1).white};
          font-size: 3.3svw;
        }

        &-password {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          gap: 2.6svw;

          .flex {
            display: flex;
            gap: 2svw;
          }

          &-input {
            width: 100%;
            height: 13.9svh;
            border: 1svw solid ${color(1).main};
            border-radius: 5.2svw;
            font-family: 'DNF';
            font-size: 2.5svw;
            padding: 1.2svh 2svw;
          }

          &-title {
            img {
              width: 24svw;
            }
          }
        }

        &-passwordcheck {
          &-title {
            img {
              width: 24svw;
            }
          }
        }

        &-nickname {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          gap: 2.6svw;

          .flex {
            display: flex;
            gap: 2svw;
          }

          &-title {
            img {
              width: 24svw;
            }
          }

          &-input {
            width: 100%;
            height: 13.9svh;
            border: 1svw solid ${color(1).main};
            border-radius: 5.2svw;
            font-family: 'DNF';
            font-size: 2.5svw;
            padding: 1.2svh 2svw;
          }
        }

        &-name {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          gap: 2.6svw;
          padding: 0 2svw;

          .flex {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 2.6svw;
          }

          &-title {
            img {
              width: 14svw;
            }
          }

          &-input {
            width: 100%;
            height: 13.9svh;
            border: 1svw solid ${color(1).main};
            border-radius: 5.2svw;
            font-family: 'DNF';
            font-size: 3.3svw;
            padding: 1.2svh 2svw;
          }
        }
      }
    }

    &__button {
      background-color: transparent;
      border: none;

      cursor: pointer;

      color: ${color(1).white};
      font-family: 'DNF';
      font-size: 2.5svw;

      display: flex;
      align-items: center;
      gap: 1.6svw;
      ${button}

      width: 20svw;
      height: 12svh;

      img {
        width: 3svw;
      }

      &:hover {
        background-color: ${color(1)._main};
      }
      &:active {
        background-color: ${color(1).main};
      }
    }
  }

  .selectDino {
    display: flex;
    flex-direction: column;
    gap: 1.5svh;
    justify-items: center;
    align-items: center;

    .title {
      img {
        width: 66svw;
      }
    }

    .selectbox {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-around;

      width: 70svw;
      height: 40svh;

      background-color: ${color(0.5).black};
      border-radius: 5svw;

      position: relative;

      &__content {
        display: grid;
        grid-template-columns: repeat(6, 1fr);
      }

      &__title {
        color: ${color(1).white};
        font-family: 'DNF';
        font-size: 3.2svw;
      }

      &__content {
        &-dino {
          img {
            width: 14svw;
          }
        }
      }
    }
  }
  .buttons {
    display: flex;
    gap: 3svw;

    button {
      ${button}

      cursor: pointer;

      &:hover {
        background-color: ${color(1)._main};
      }

      width: 19.2svw;
      height: 12svh;
      font-size: 2.5svw;

      img {
        width: 3svw;
      }
    }
  }
`;
