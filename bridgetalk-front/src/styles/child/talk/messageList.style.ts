import styled from 'styled-components';

export const Container = styled.div`
  .messageList {
    &__header {
      display: flex;
      align-item: center;

      img {
        width: 8svw;
      }
    }

    &__container {
      &-list {
      }

      &-info {
        img {
          height: 20svh;
        }
      }
    }
  }
`;
