import styled, { keyframes } from 'styled-components';
import { color } from './common.style';

const fadein = keyframes`
  0% {
    transform: translateX(2svw);
    opacity: 0;
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
`;

export const Background = styled.div`
  /* background-image: url('/assets/img/parent_bg.png'); */
  /* background-size: cover;
  background-position-x: 30%; */
  background-color: ${color(0.8).sub};
  width: 100svw;
  height: 100svh;

  display: flex;
  justify-content: start;

  overflow: hidden;

  .outline {
    width: 90svw;
    height: 100%;

    overflow: hidden;

    animation: ${fadein} 1s;

    background-color: rgb(240, 240, 240);
    border-left: 0.5svw solid ${color(1).bright};

    border-top-left-radius: 3svw;
    border-bottom-left-radius: 3svw;

    box-shadow: -0.5svw 0 0.8svw ${color(0.3).black};

    background-image: url('/assets/img/parent_bg.png');
    background-size: cover;
    background-position-x: 30%;

    position: relative;

    /* padding: 2svh 2svw; */

    display: flex;
    justify-content: center;
    align-items: center;
  }

  .lang {
    position: fixed;
    top: 3svh;
    right: 2svw;

    background-color: transparent;
    border: none;

    cursor: pointer;

    img {
      width: 5svw;
    }
  }
`;

export const Navbar = styled.div`
  height: 100svh;
  width: 10svw;

  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 6svh;

  button {
    background-color: transparent;
    border: none;

    width: 100%;
    height: 8svh;

    img {
      cursor: pointer;

      transition: all 0.2s;

      width: 3.8svw;
      &:hover {
        transform: scale(1.1);
      }
    }
  }
`;
