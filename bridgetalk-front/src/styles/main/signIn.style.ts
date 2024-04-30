import styled from 'styled-components';
import { backButton } from './common.style';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  .back {
    ${backButton}
  }

  .email,
  .password {
    display: flex;
    gap: 2svw;
  }
`;
