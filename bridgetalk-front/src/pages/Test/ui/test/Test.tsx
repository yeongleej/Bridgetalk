import { TestMenuButtons } from '@/pages/Test/ui/testMenuButtons/testMenuButtons';
import { TestPage } from '@/pages/Test/ui/testPage/testPage';
import { Link, Outlet } from 'react-router-dom';
import * as S from '@/styles/test/test.style';

export function Test() {
  return (
    <S.TestContainer>
      <TestMenuButtons>
        <Link to="/camera">카메라</Link>
        <Link to="/draw">그림그리기</Link>
        <Link to="/puzzle">퍼즐</Link>
        <Link to="/voice">음량 테스트</Link>
        <Link to="/wordcloud">워드 클라우드</Link>
        <Link to="/zustand">Zustand</Link>
        <Link to="/character">캐릭터</Link>
        <Link to="/parent/main">부모 페이지</Link>
      </TestMenuButtons>
      <TestPage>
        <Outlet />
      </TestPage>
    </S.TestContainer>
  );
}
