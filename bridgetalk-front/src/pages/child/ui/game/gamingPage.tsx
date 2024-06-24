import { useNavigate } from 'react-router-dom';
import * as S from '@/styles/child/game/game.style';

export function GamingPage() {
  const navigate = useNavigate();

  return (
    <S.Container>
      <div className="gamingPage">
        <div className="gamingPage__header">
          <div className="gamingPage__header-toHome">
            <img
              src="/assets/img/child/game/toHome.svg"
              alt=""
              onClick={() => {
                navigate('/child');
              }}
            />
          </div>
          <div className="gamingPage__header-title">
            <img src="/assets/img/child/game/gameTitle.svg" alt="" />
          </div>
          <div className="gamingPage__header-toHelp">
            <img src="/assets/img/child/game/toHelp.svg" alt="" />
          </div>
        </div>
        <div className="gamingPage__container">
          <div className="gamingPage__container-toPuzzle">
            <img
              src="/assets/img/child/game/toPuzzle.svg"
              alt=""
              onClick={() => {
                navigate('/stage');
              }}
            />
          </div>
          <div className="gamingPage__container-toPuzzle">
            <img
              src="/assets/img/child/game/toDress.svg"
              alt=""
              onClick={() => {
                navigate('/dress');
              }}
            />
          </div>
        </div>
      </div>
    </S.Container>
  );
}
