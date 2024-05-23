import { ReplyList } from './components/replyList';
import * as S from '@/styles/parent/boardDetailPage.style';
import { useEffect, useMemo, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useBoardStore, useReportStore } from '../../store';
import { deleteBoardLike, getBoardDetail, getBoardLikeCheck, postBoardLike } from '../../query';
import { dateToString } from '@/shared';
import { ReplyRegist } from './boardPage/replyRegist';

interface BoardContent {
  boardsContent: string;
  boardsId: number;
  boardsTitle: string;
  createdAt: string;
  likes: number;
  reportsKeywords: string[];
  reportsSummary: string;
  parentsNickname: string;
}

export function BoardDetailPage() {
  const navigate = useNavigate();

  const params = useParams();

  const boardStore = useBoardStore();
  const language = useReportStore((state) => state.language);
  const [like, setLike] = useState(false);
  const [likeCnt, setLikeCnt] = useState(0);
  const [board, setBoard] = useState<BoardContent>();

  useEffect(() => {
    async function boardLikeCheck() {
      try {
        const res: any = await getBoardLikeCheck(Number(params.boardId));

        setLike(res.data);
      } catch (err) {}
    }
    boardLikeCheck();
  }, []);

  useEffect(() => {
    async function fetchData() {
      try {
        const data = await getBoardDetail(Number(params.boardId), language);

        if (data.status === 200) {
          setBoard(data.data);
          setLikeCnt(data.data.likes);
        }
      } catch (err) {}
    }
    fetchData();
  }, [language]);

  const title = useMemo(
    () => ({
      kor: '부모 커뮤니티', // 한국어: "부모 커뮤니티"
      viet: 'Cộng đồng phụ huynh', // 베트남어: "Cộng đồng phụ huynh"
      ph: 'Komunidad ng mga magulang', // 필리핀어: "Komunidad ng mga magulang"
    }),
    [],
  );

  return (
    <S.Container>
      <div className="boardDetailPage">
        <div className="boardDetailPage__header">
          <button
            className="boardDetailPage__header-btn"
            onClick={() => {
              navigate('/parent/board');
            }}
          >
            <img src={'/assets/img/parent/community/back.svg'} />
          </button>
          <div
            className="boardDetailPage__header-title"
            style={{ fontFamily: language === 'kor' ? 'DNF' : 'Pretendard' }}
          >
            {title[language]}
          </div>
          <button className="boiardDetailPage__header-share">
            <img src={'/assets/img/parent/community/share.svg'} />
          </button>
        </div>
        <hr className="sky" />
        <div className="scroll">
          <div className="boardDetailPage__container">
            <div className="boardDetailPage__container-article">
              <div className="boardDetailPage__container-article-header">
                <div className="boardDetailPage__container-article-header-title">{board?.boardsTitle}</div>
                <div className="boardDetailPage__container-article-header-icons">
                  <button
                    className="boiardDetailPage__container-article-header-icons-like"
                    onClick={() => {
                      if (like) {
                        deleteBoardLike(Number(params.boardId))
                          .then(() => {
                            setLike(false);
                            setLikeCnt((cnt) => cnt - 1);
                          })
                          .catch((err) => {
                            setLike(true);
                          });
                      } else {
                        postBoardLike(Number(params.boardId))
                          .then(() => {
                            setLike(true);
                            setLikeCnt((cnt) => cnt + 1);
                          })
                          .catch((err) => {
                            setLike(false);
                          });
                      }
                    }}
                  >
                    <img src={`/assets/img/parent/community/${like ? 'like_solid.png' : 'like_empty.svg'}`} />
                  </button>
                  <div className="boiardDetailPage__container-article-header-icons-like-cnt">{likeCnt}</div>
                </div>
              </div>
              <div className="boardDetailPage__container-article-sub">
                <p>{board?.parentsNickname}</p>
                <p>{board && dateToString(board?.createdAt)}</p>
              </div>
              <div className="boardDetailPage__container-article-report">
                <div className="boardDetailPage__container-article-report-title">
                  <div>R E P O R T</div>
                  <div className="boardDetailPage__container-article-report-title-keywords">
                    {board?.reportsKeywords.map((keyword: string, idx: number) => (
                      <div key={idx} className="boardDetailPage__container-article-report-title-keywords-keyword">
                        # {keyword}
                      </div>
                    ))}
                  </div>
                </div>
                <div className="boardDetailPage__container-article-report-content">
                  {board?.reportsSummary ?? '요약된 리포트 정보가 없습니다.'}
                </div>
              </div>
              <div className="boardDetailPage__container-article-content">
                {board?.boardsContent.split('</br>').join('\n')}
              </div>
              <div className="boardDetailPage__container-article-keywords">
                {board?.reportsKeywords.map((keyword: string, idx: number) => (
                  <div key={idx + 1} className="boardDetailPage__container-article-keywords-keyword">
                    # {keyword}
                  </div>
                ))}
              </div>
            </div>
          </div>
          <div className="boardDetailPage__container-reply">
            <div className="boardDetailPage__container-reply-wrapper">
              <ReplyRegist boardsId={board?.boardsId} />
            </div>
            <ReplyList boardId={board?.boardsId} />
          </div>
        </div>
      </div>
    </S.Container>
  );
}
