import { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import * as S from '@/styles/child/talk/messagePage.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretLeft } from '@fortawesome/free-solid-svg-icons';

export function MessagePage() {
  const navigate = useNavigate();

  useEffect(() => {
    navigate('list');
  }, []);

  return (
    <S.Container>
      <div className="messagePage">
        <div className="messagePage__header">
          <div
            className="messagePage__header-toBack"
            onClick={() => {
              navigate('/child');
            }}
          >
            <FontAwesomeIcon icon={faCaretLeft} />
            <span>돌아가기</span>
          </div>
        </div>
        <div className="messagePage__container">
          <Outlet />
        </div>
      </div>
    </S.Container>
  );
}
