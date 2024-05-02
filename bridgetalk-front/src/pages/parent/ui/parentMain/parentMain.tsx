import * as S from '@/styles/parent/parentMain.style';
import { useNavigate } from 'react-router-dom';

export function ParentMain() {
  const navigate = useNavigate();

  return (
    <S.Container>
      <button onClick={() => navigate('../report')}>
        <div>nỗi lòng con cái</div>
      </button>
      <div>DINO</div>
      <button onClick={() => navigate('../information')}>
        <div>lấy thông tin</div>
      </button>
    </S.Container>
  );
}
