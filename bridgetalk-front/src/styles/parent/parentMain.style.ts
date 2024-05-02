import styled from 'styled-components';
import { buttonNormal } from './common.style';

export const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  gap: 20svw;

  width: 100svw;
  height: 100svh;

  .logo {
    /* position: fixed; */
    top: 10.6svh;
    left: 50%;
    transform: translateX(-50%);
    width: 100%;

    img {
      width: 40svw;
      height: 40svh;
    }
  }
  button {
    ${buttonNormal}
  }
`;
