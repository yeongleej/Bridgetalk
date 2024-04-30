import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  .title {
  }

  .selectbox {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    &__content {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }

  .buttons {
    display: flex;
  }
`;
