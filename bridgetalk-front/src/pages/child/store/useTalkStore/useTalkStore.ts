import { create } from 'zustand';

interface Store {
  reportsId: number;
  setReportsId: (reportsId: number) => void;
  isRecording: boolean;
  setIsRecording: (state: boolean) => void;
  isSend: boolean;
  setIsSend: (state: boolean) => void;
  isTalking: boolean;
  setIsTalking: (state: boolean) => void;
  isWaiting: boolean;
  setIsWaiting: (state: boolean) => void;
  isEnd: boolean;
  setIsEnd: (state: boolean) => void;
  subtitle: string;
  setSubtitle: (state: string) => void;
  emotion: string;
  setEmotion: (state: string) => void;
  resetStore: () => void;
  isStart: boolean;
  setIsStart: (state: boolean) => void;
}

const initialState: Omit<
  Store,
  | 'setReportsId'
  | 'setIsRecording'
  | 'setIsSend'
  | 'setIsTalking'
  | 'setIsWaiting'
  | 'setIsEnd'
  | 'setSubtitle'
  | 'setEmotion'
  | 'resetStore'
  | 'setIsStart'
> = {
  reportsId: 0,
  isRecording: false,
  isSend: false,
  isTalking: false,
  isWaiting: false,
  isEnd: false,
  subtitle: '',
  emotion: 'idle',
  isStart: false,
};

export const useTalkStore = create<Store>()((set) => ({
  ...initialState,
  setReportsId: (reportsId) => set({ reportsId }),
  setIsRecording: (state) => set({ isRecording: state }),
  setIsSend: (state) => set({ isSend: state }),
  setIsTalking: (state) => set({ isTalking: state }),
  setIsWaiting: (state) => set({ isWaiting: state }),
  setIsEnd: (state) => set({ isEnd: state }),
  setSubtitle: (state: string) => set({ subtitle: state }),
  setEmotion: (state: string) => set({ emotion: state }),
  resetStore: () => set({ ...initialState }),
  setIsStart: (state) => set({ isStart: state }),
}));
