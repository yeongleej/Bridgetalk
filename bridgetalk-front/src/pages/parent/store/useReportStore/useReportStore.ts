import { create } from 'zustand';

interface Store {
  reportList: Report[];
  setReportList: (reportList: Report[]) => void;
}

interface Report {
  reportsId: number;
  reportsSummary: string;
  reportsKeywords: string[];
  createdAt: string;
}

export const useReportStore = create<Store>()((set) => ({
  reportList: [],
  setReportList: (reportList: Report[]) => set({ reportList: reportList }),
}));
