import styled from 'styled-components';
import { color, insetShadow } from '../main/common.style';

export const ChildWrapper = styled.div`
  font-family: 'DNF';
  font-size: 3svw;
  color: ${color(1).white};
  background-color: ${color(1).sub};

  width: 18svw;

  text-align: center;

  border-radius: 2svw;
  padding: 2svh 2svw;

  position: fixed;
  left: 50%;
  transform: translateX(-50%);

  box-shadow: 0 0.5svh 0.4svh ${color(0.5).black};

  &::after {
    ${insetShadow}

    border-radius: 2svw;
    box-shadow: inset 0 0.5svh 0.4svh ${color(0.5).white};
  }

  &::before {
    ${insetShadow}

    border-radius: 2svw;
    box-shadow: inset 0 -0.5svh 0.4svh ${color(0.25).black};
  }
`;
