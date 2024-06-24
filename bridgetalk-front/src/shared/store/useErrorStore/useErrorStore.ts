import { create } from 'zustand';

interface Store {
  errorModalState: string;
  setErrorModalState: (state: string) => void;
}
export const useErrorStore = create<Store>()((set) => ({
  errorModalState: '',
  setErrorModalState: (state) => set({ errorModalState: state }),
}));
