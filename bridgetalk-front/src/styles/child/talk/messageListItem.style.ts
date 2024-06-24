import styled from 'styled-components';

export const Container = styled.div`
  &:hover {
    cursor: pointer;
  }

  .messageListItem {
    display: flex;
    align-item: center;
    justify-content: space-between;
    padding: 2svh 3svw;

    border-radius: 30px;
    background: #fff;
    box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.25) inset, 0px -10px 10px 0px rgba(0, 0, 0, 0.25) inset,
      0px 4px 4px 0px rgba(0, 0, 0, 0.25);

    * {
      font-size: 3svh;
      font-family: 'DNF';
    }
  }
`;
