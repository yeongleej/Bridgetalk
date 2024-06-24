import styled from 'styled-components';
import { backButton, button, color } from './common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 5.5svh;

  width: 100svw;
  height: 100svh;
  background-color: ${color(0.2).black};

  input {
    border-radius: 15svw;
    border: 1svw solid ${color(1).main};
    font-size: 2.5svw;
    font-family: 'DNF';
    padding: 1.2svh 2svw;
  }

  .title {
    img {
      width: 22.4svw;
    }
  }
  .email,
  .password {
    display: flex;
    gap: 2svw;
  }

  .email {
    &__title {
      img {
        width: 13.6svw;
      }
    }
    input {
      width: 47.2svw;
    }
  }

  .password {
    &__title {
      img {
        width: 22.9svw;
      }
    }
    input {
      width: 37.6svw;
    }
  }

  .button {
    ${button}

    font-size: 2.5svw;
    width: 19.8svw;
    height: 12svh;

    img {
      width: 3svw;
    }
  }
`;
