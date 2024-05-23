import styled from 'styled-components';
import { color } from './common.style';

export const Container = styled.div`
  width: 80svw;
  height: 70svh;

  ::-webkit-scrollbar {
    display: none;
  }

  overflow-y: auto;

  background-color: ${color(1).light};

  border-radius: 2svw;
  border: none;

  padding: 3svh 2svw;

  .main {
    display: flex;
    flex-direction: column;
    gap: 1svw;

    font-family: 'Pretendard';

    &__title,
    &__link,
    &__content {
      /* background-color: ${color(1).bright}; */
      border-radius: 1svw;
      padding: 1svh 1svw;

      font-size: 1.1svw;
    }

    &__title {
      font-weight: bold;
      font-size: 1.5svw;
    }
    &__content {
      display: flex;
      flex-direction: column;
      gap: 0.5svw;
      height: 100%;
      font-size: 1.2svw;
    }
  }
`;
