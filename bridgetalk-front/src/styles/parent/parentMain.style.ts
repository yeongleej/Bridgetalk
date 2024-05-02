import styled from 'styled-components';
import { buttonNormal } from './common.style';

export const Container = styled.div`
  display: flex;
  justify-content: center;
  gap: 20svw;

  button {
    ${buttonNormal}
  }
`;
