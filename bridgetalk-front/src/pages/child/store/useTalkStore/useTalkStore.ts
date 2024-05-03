import { create } from 'zustand';

export const useTalkStore = create<any>()((set) => ({
  reportsId: 0,
  setReportsId: (reportsId: number) => set({ reportsId }),
}));
