import { create } from 'zustand';

interface Store {
  audioBlob: Blob | null;
  setAudioBlob: (audioBlob: Blob) => void;

  volume: number;
  setVolume: (volume: number) => void;

  isRecordFinished: boolean;
  setIsRecordFinished: (status: boolean) => void;
}

export const useVoiceStore = create<Store>()((set) => ({
  audioBlob: null,
  setAudioBlob: (audioBlob: Blob) => set({ audioBlob: audioBlob }),

  volume: 0,
  setVolume: (volume: number) => set({ volume: volume }),

  isRecordFinished: false,
  setIsRecordFinished: (status: boolean) => set({ isRecordFinished: status }),
}));
