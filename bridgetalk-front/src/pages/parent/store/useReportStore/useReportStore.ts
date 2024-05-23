import { Children } from 'react';
import { create } from 'zustand';

interface Store {
  reportList: any;
  setReportList: (reportList: any[]) => void;
  language: Language['type'];
  setLangauge: (language: Language['type']) => void;
  childrenList: any;
  setChildrenList: (childrenList: any) => void;
  reports_UUID: any;
  setReports_UUID: (reports_UUID: any) => void;
  resultPage: number;
  setResultPage: (resultPage: number) => void;
  replys: any;
  setReplys: (state: any) => void;
}

interface Language {
  type: 'kor' | 'viet' | 'ph';
}

interface Report {
  reportsId: number;
  reportsSummary: string;
  reportsKeywords: string[];
  createdAt: string;
}

export const useReportStore = create<Store>()((set) => ({
  reportList: [],
  setReportList: (reportList: any) => set({ reportList: reportList }),
  language: 'kor',
  setLangauge: (language: Language['type']) => set({ language: language }),
  childrenList: [],
  setChildrenList: (childrenList: any) => set({ childrenList: childrenList }),
  reports_UUID: new Map(),
  setReports_UUID: (reports_UUID: any) => set({ reports_UUID: reports_UUID }),
  resultPage: 0,
  setResultPage: (resultPage: number) => set({ resultPage: resultPage }),
  replys: [],
  setReplys: (state: any) => set({ replys: state }),
}));
