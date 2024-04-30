import styled from 'styled-components';
import { color, textShadowRed } from './common.style';

export const Container = styled.div`
  display: flex;

  .title {
    font-family: 'CherryBomb';
    font-size: 7svw;
    color: ${color(1).white};
    ${textShadowRed}

    position: fixed;
    top: 5svh;
    right: 8svw;
  }

  .buttons {
    button {
    }
    &__regist {
    }

    &__signin {
    }
  }
`;
