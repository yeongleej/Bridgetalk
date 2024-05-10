import { NavigateFunction } from 'react-router-dom';
import * as S from '@/styles/shared/homeButton.style';
interface Props {
  navigate: NavigateFunction;
}

export function HomeButton({ navigate }: Props) {
  return (
    <S.Wrapper
      className="back"
      onClick={() => {
        navigate('../start');
      }}
    >
      <img src={'/assets/img/homeicon.svg'} />
    </S.Wrapper>
  );
}
