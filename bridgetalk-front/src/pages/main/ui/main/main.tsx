import { Outlet, useNavigate } from 'react-router-dom';
import * as S from '@/styles/main/main.style';
import { useEffect, useState } from 'react';
import { WelcomeScreen } from '@/shared/ui/loading/welcomeScreen';

export function Main() {
  const navigate = useNavigate();
  const [showWelcome, setShowWelcome] = useState(true);

  useEffect(() => {
    // 페이지가 로드되자마자 /start 경로로 이동
    navigate('/start');
  }, []);

  return (
    <S.Background>
      {showWelcome && <WelcomeScreen onClose={() => setShowWelcome(false)} />}
      <Outlet />
    </S.Background>
  );
}
