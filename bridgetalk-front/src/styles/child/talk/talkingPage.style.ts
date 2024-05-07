import styled from 'styled-components';

export const Container = styled.div`
  width: 100svw;
  height: 100svh;
  display: flex;
  justify-content: center;
  align-items: center;

  .talking {
    &__container {
      &-talk {
        display: flex;
        flex-direction: column;
        gap: 2svw;

        .record {
          display: flex;
          flex-direction: column;
        }
        button {
          width: 50svw;
        }
      }
    }
  }
`;
