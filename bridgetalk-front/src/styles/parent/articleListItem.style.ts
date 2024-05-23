import styled, { css } from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  background-color: #fff; 
  border-radius: 10px; 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
  margin: 10px; 
  overflow: hidden; 

  .articleListItem__main {
    padding: 15px; 
    display: flex;
    justify-content: space-between;

    &-title {
      font-size: 1.2rem; 
      color: #333; 
      font-weight: bold; 
    }

    &-like {
      display: flex;
      align-items: center;
      font-size: 1rem; 
      color: #666;

      svg {
        color: red; 
        margin-right: 5px;
      }
    }
  }

  .articleListItem__sub {
    background-color: #f1f1f1; 
    padding: 10px 15px;
    display: flex;
    justify-content: space-between;

    &_keywords {
      display: flex;

      &_keyword {
        background-color: #e0e0e0; 
        padding: 5px 10px;
        border-radius: 5px;
        margin-right: 5px;
        font-size: 0.8rem; 
      }
    }

    &_date {
      font-size: 0.8rem; 
      color: #666; 
    }
  }
`;
