import styled from 'styled-components';

export const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  z-index: 10;

  display: flex;
  align-items: center;
  justify-content: center;
  gap: 1svh;

  width: 100%;
  height: 100%;

  background-color: #ff6161;

  .loadingScreen {
    &__character {
      
    }

    &__text {
      font-size: 3svh;
      font-family: 'DNF';
      color: white;
    }
  }
`;
