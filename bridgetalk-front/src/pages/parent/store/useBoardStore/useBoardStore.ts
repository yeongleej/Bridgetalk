import { create } from 'zustand';

interface Store {
  boardList: any;
  setBoardList: (boardList: any[]) => void;
  language: Language['type'];
  setLangauge: (language: Language['type']) => void;
  board_UUID: any;
  setBoard_UUID: (board_UUID: any) => void;
  resultPage: number;
  setResultPage: (resultPage: number) => void;
  searchType: string;
  setSearchType: (searchType: string) => void;
  sortType: string;
  setSortType: (sortType: string) => void;
  refresh: boolean;
  setRefresh: () => void;
}

interface Language {
  type: 'kor' | 'viet' | 'ph';
}

export const useBoardStore = create<Store>()((set) => ({
  boardList: [],
  setBoardList: (boardList: any) => set({ boardList: boardList }),
  language: 'kor',
  setLangauge: (language: Language['type']) => set({ language: language }),
  board_UUID: new Map(),
  setBoard_UUID: (board_UUID: any) => set({ board_UUID: board_UUID }),
  resultPage: 0,
  setResultPage: (resultPage: number) => set({ resultPage: resultPage }),
  searchType: '제목',
  setSearchType: (searchType: string) => set({ searchType: searchType }),
  sortType: '최신순',
  setSortType: (sortType: string) => set({ sortType: sortType }),
  refresh: false,
  setRefresh: () => set((state) => ({ refresh: !state.refresh })),
}));
