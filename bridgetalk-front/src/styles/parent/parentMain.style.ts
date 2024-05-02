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
    img {
      width: 40svw;
    }
  }

  button {
    ${buttonNormal}
  }
`;
