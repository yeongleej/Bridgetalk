import { useState, useEffect } from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';
import { LoadingScreen } from '@/shared/ui/loading/loadingScreen';

import {
  Main,
  StartPage,
  ProfilePage,
  EditProfilePage,
  SignInPage,
  SignUpPage,
  LoginGuard,
  ErrorPage,
  ParentInformationNurture,
  ParentInformationNurtureDetail,
  CreatePage,
  BoardDetailPage,
} from '@/pages';
import {
  ChildPage,
  TalkingPage,
  MessagePage,
  MessageList,
  Message,
  GamingPage,
  StagePage,
  PuzzlePage,
  FinishPage,
  ColoringPage,
  DressingPage,
} from '@/pages';
import {
  Parent,
  ParentInformationMain,
  ParentInformationWord,
  ParentMain,
  ParentReportDetail,
  ParentReportList,
} from '@/pages';
import {
  TeestZustand,
  Test,
  TestColoring,
  TestCamera,
  TestDraw,
  // TestPuzzle,
  TestVoice,
  TestWordcloud,
  // TestCharacter,
} from '@/pages';
import { BoardPage } from '@/pages/parent/ui/community/boardPage';

export function AppRoutes() {
  const [loading, setLoading] = useState(false);
  const location = useLocation();

  useEffect(() => {
    const targetPaths = [
      '/child',
      '/talk',
      '/message',
      '/message/list',
      '/message/:id',
      '/game',
      '/puzzle',
      '/color',
      '/dress',
    ];

    if (targetPaths.includes(location.pathname)) {
      setLoading(true);
      setTimeout(() => setLoading(false), 2000);
    }
  }, [location]);

  return (
    <>
      {/* {loading && (
        <LoginGuard>
          <LoadingScreen />
        </LoginGuard>
      )} */}
      <Routes>
        {/* <Route path="/home" element={<WelcomeScreen />} /> */}

        {/* 메인화면 관련 */}
        <Route path="/" element={<Main />}>
          <Route
            path="start"
            element={
              <LoginGuard>
                <StartPage />
              </LoginGuard>
            }
          />
          <Route
            path="signin"
            element={
              <LoginGuard>
                <SignInPage />
              </LoginGuard>
            }
          />
          <Route
            path="signup"
            element={
              <LoginGuard>
                <SignUpPage />
              </LoginGuard>
            }
          />
          <Route
            path="profile"
            element={
              <LoginGuard>
                <ProfilePage />
              </LoginGuard>
            }
          />
          <Route
            path="addProfile"
            element={
              <LoginGuard>
                <EditProfilePage type="new" />
              </LoginGuard>
            }
          />
          <Route
            path="editProfile"
            element={
              <LoginGuard>
                <EditProfilePage type="edit" />
              </LoginGuard>
            }
          />
        </Route>

        {/* 아이 관련 */}
        <Route
          path="/child"
          element={
            <LoginGuard>
              <ChildPage />
            </LoginGuard>
          }
        />
        <Route
          path="/talk"
          element={
            <LoginGuard>
              <TalkingPage />
            </LoginGuard>
          }
        />
        <Route
          path="/message"
          element={
            <LoginGuard>
              <MessagePage />
            </LoginGuard>
          }
        >
          <Route path="list" element={<MessageList />} />
          <Route path=":id" element={<Message />} />
        </Route>
        <Route path="/game" element={<GamingPage />} />
        <Route path="/stage" element={<StagePage />} />
        <Route path="/puzzle/:id" element={<PuzzlePage />} />
        <Route path="/finish/:id" element={<FinishPage />} />
        <Route path="/color" element={<ColoringPage />} />
        <Route path="/dress" element={<DressingPage />} />

        {/* 부모 관련 */}
        <Route path="/parent" element={<Parent />}>
          <Route path="main" element={<ParentMain />} />
          <Route path="report" element={<ParentReportList />} />
          <Route path="report/:reportsId" element={<ParentReportDetail />} />
          <Route path="information" element={<ParentInformationMain />} />
          <Route path="information/nurture" element={<ParentInformationNurture />} />
          <Route path="information/nurture/:nurtureId" element={<ParentInformationNurtureDetail />} />
          <Route path="information/word" element={<ParentInformationWord />} />
          <Route path="board" element={<BoardPage />} />
          <Route path="board/:boardId" element={<BoardDetailPage />} />
          <Route path="board/write" element={<CreatePage />} />
          <Route path="*" element={<ErrorPage />} />
        </Route>

        {/* 기능 테스트용 페이지 - 추후 삭제 */}
        <Route path="/test" element={<Test />}>
          <Route path="camera" element={<TestCamera />} />
          <Route path="draw" element={<TestDraw />} />
          {/* <Route path="puzzle" element={<TestPuzzle />} /> */}
          <Route path="voice" element={<TestVoice />} />
          <Route path="wordcloud" element={<TestWordcloud />} />
          <Route path="zustand" element={<TeestZustand />} />
          {/* <Route path="character" element={<TestCharacter />} /> */}
          <Route path="coloring" element={<TestColoring />} />
        </Route>

        {/* 에러 페이지 */}
        <Route path="*" element={<ErrorPage />}></Route>
      </Routes>
    </>
  );
}
