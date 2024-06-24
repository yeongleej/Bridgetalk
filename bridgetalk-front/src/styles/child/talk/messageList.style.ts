import styled from 'styled-components';

export const Container = styled.div`
  width: 100svw;

  * {
    box-sizing: border-box;

    ::-webkit-scrollbar {
      display: none;
    }
  }

  .messageList {
    display: flex;
    align-item: center;
    justify-content: baseline;
    gap: 10svh;

    &__list {
      width: 60svw;
      height: 60svh;
      padding: 3svh;

      border-radius: 5svh;
      background-color: #ff9f99;
      box-shadow: 0 1svh 1svh #00000050;

      &-header {
        display: flex;
        align-item: center;

        img {
          width: 8svw;
        }
      }

      &-content {
        display: flex;
        flex-direction: column;
        gap: 1svh;

        height: 35svh;
        overflow: scroll;
      }
    }

    &__info {
      display: flex;
      flex-direction: column;
      align-items: end;
      justify-content: end;
      gap: 2svh;

      span {
        padding: 4svh;
        margin-right: 1svh;

        font-size: 3svh;
        font-family: 'DNF';

        border-radius: 10svh 10svh 0 10svh;
        background-color: white;
        box-shadow: 0 1svh 1svh #00000050;
      }

      img {
        height: 30svh;
      }
    }
  }
`;
