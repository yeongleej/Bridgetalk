import { MutableRefObject, useEffect, useMemo, useRef, useState } from 'react';
import { ArticleList } from './components/articleList';
import * as S from '@/styles/parent/boardPage.style';
import { useBoardStore, useReportStore } from '../../store';
import { getBoardList } from '../../query';
import React from 'react';
import { handleSearchBoard } from '../../model';
import { SearchTypes } from './boardPage/searchTypes';
import { Input } from './boardPage/input';
import { BoardListItem } from './boardPage/boardListItem';
import { Pagenation } from './boardPage/pagenation';
import { useNavigate } from 'react-router-dom';
import { useErrorStore } from '@/shared/store';

interface Board {
  boardId: number;
  boardsTitle: string;
  boardsContent: string;
  likes: number;
  createdAt: string;
  reportsSummary: string;
  reportsKeywords: string[];
  parentsNickname: string;
}

// infant_and_toddler', 'school', 'puberty'
export function BoardPage() {
  const navigate = useNavigate();

  // Global state
  const language = useReportStore((state) => state.language);
  const boardStore = useBoardStore();
  const setErrorModalState = useErrorStore((state) => state.setErrorModalState);
  // State
  const [page, setPage] = useState<number>(0);
  const [lastPage, setLastPage] = useState<number>(0);

  // Ref
  const inputRef = useRef<HTMLInputElement>(null);

  async function fetchData(
    _searchWord: string = inputRef.current!.value,
    _language: any = language,
    _page: number = page,
    _boardSearchType: string = boardStore.searchType,
    _sortType: string = boardStore.sortType,
  ) {
    try {
      const data: any = await handleSearchBoard(_searchWord, _language, _page, _boardSearchType, _sortType);

      boardStore.setBoardList(data.data.boardsList);
      setLastPage(data.data.pageInfo.totalPages);
    } catch (err) {
      if (err instanceof Error) {
        setErrorModalState('예기치 못한 에러가 발생했습니다. 잠시 후 다시 시도해주세요.');
      }
    }
  }

  const latest = useMemo(
    () => ({
      kor: '최신순',
      viet: 'Pinakabago',
      ph: '',
    }),
    [],
  );
  const favor = useMemo(
    () => ({
      kor: '인기순',
      viet: 'Pinakasikat',
      ph: '',
    }),
    [],
  );

  const title = useMemo(
    () => ({
      kor: '부모 페이지',
      viet: 'Trang phụ huynh',
      ph: 'Pahina ng magulang',
    }),
    [],
  );

  useEffect(() => {
    fetchData('', language, page, boardStore.searchType, boardStore.sortType);
  }, [language, page]);

  useEffect(() => {
    fetchData(inputRef.current!.value, language, 0, boardStore.searchType, boardStore.sortType);
  }, [boardStore.sortType]);

  return (
    <S.Container>
      <div className="boardPage__header">
        <div className="boardPage__header-title" style={{ fontFamily: language === 'kor' ? 'DNF' : 'Pretendard' }}>
          {title[language]}
        </div>
        <div className="boardPage__header-sort">
          <button
            className={`boardPage__header-sort-latest ${boardStore.sortType === '최신순' && 'active'}`}
            onClick={() => {
              boardStore.setSortType('최신순');
            }}
          >
            {latest[language]}
          </button>
          <button
            className={`boardPage__header-sort-popular ${boardStore.sortType === '좋아요순' && 'active'}`}
            onClick={() => {
              boardStore.setSortType('좋아요순');
            }}
          >
            {favor[language]}
          </button>
        </div>
      </div>
      <div className="boardPage">
        <div className="boardPage__top">
          <SearchTypes />
          <Input ref={inputRef} fetchData={fetchData} />
        </div>
        <div className="boardPage__list">
          {boardStore.boardList &&
            boardStore.boardList.map((board: Board, idx: number) => <BoardListItem key={idx} board={board} />)}
        </div>
        <Pagenation page={page} setPage={setPage} list={boardStore.boardList} lastPage={lastPage} />
      </div>
      <button
        className="write"
        onClick={() => {
          navigate('write');
        }}
      >
        <img src={'/assets/img/parent/community/write_empty.svg'} />
      </button>
    </S.Container>
  );
}
