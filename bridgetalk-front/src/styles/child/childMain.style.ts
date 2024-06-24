import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100svw;
  height: 100svh;

  background-image: url('/assets/img/pic/childBackground.png');
  background-size: cover;
  background-repeat: no-repeat;

  .childMain {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
    position: relative;

    &__container {
      position: fixed;
      top: 25svh;

      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 5svh;

      width: 100%;
      height: 100%;

      &-title {
        position: fixed;
        top: 10.5svh;

        img {
          width: 70svw;
        }
      }

      &-content {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
        gap: 2svw;

        width: 100%;
        height: 60%;
        padding-bottom: 10svh;

        &-toMessage {
          display: flex;
          align-items: center;
          justify-content: center;

          width: 30%;

          img {
            margin-bottom: 0.5svh;
            width: 78%;
            transition: all 0.5s;
            &:hover {
              cursor: pointer;
              width: 98%;
            }
          }
        }

        &-toTalk {
          display: flex;
          align-items: center;
          justify-content: center;

          width: 30%;

          img {
            width: 81%;
            transition: all 0.5s;
            &:hover {
              cursor: pointer;
              width: 101%;
            }
          }
        }

        &-toGame {
          display: flex;
          align-items: center;
          justify-content: center;

          width: 30%;

          img {
            margin-top: 1svh;
            width: 65%;
            transition: all 0.5s;
            &:hover {
              cursor: pointer;
              width: 85%;
            }
          }
        }
      }
    }

    &__footer {
      display: flex;
      align-items: center;
      justify-content: space-between;
      width: 100%;
      height: 25svh;
      position: fixed;
      bottom: 0;
      padding: 0 3svw;
      // z-index: -100;

      p {
        background-color: white;
        font-family: 'DNF';
        text-align: center;
        padding: 1svh;
        border-radius: 5px 5px 0 5px;
        box-shadow 3px 3px 3px #00000050;
      }
    }
  }
`;
