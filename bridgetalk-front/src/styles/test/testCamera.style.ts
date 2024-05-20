import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  height: 100%;
`;

export const VideoWrapper = styled.div`
  background-color: orange;
  width: 100%;
  height: 50%;

  video {
    width: 100%;
    height: 100%;
  }
`;

export const ButtonWrapper = styled.div`
  width: 100%;
  height: 10%;

  display: flex;
  gap: 1%;
`;

export const CanvasWrapper = styled.div`
  width: 100%;
  height: 40%;

  canvas {
    width: 100%;
    height: 100%;
    z-index: 1;
  }
`;
