import styled from 'styled-components';
import { buttonNormal, color } from './common.style';

export const Container = styled.div`
  display: flex;
  justify-content: center;
  gap: 10svw;

  button {
    border: none;
    border-radius: 1svw;

    background-color: ${color(1).sub};

    box-shadow: 0 0.5svh 0.4svh ${color(0.5).black};

    font-size: 3svw;
    font-family: 'Pretendard';

    padding: 2svh 2svw;

    color: ${color(1).bright};

    cursor: pointer;
  }
`;
