import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 2svh;

  .name,
  .nickname,
  .password,
  .passwordcheck,
  .buttons {
    display: flex;
    gap: 2svw;
  }
`;
