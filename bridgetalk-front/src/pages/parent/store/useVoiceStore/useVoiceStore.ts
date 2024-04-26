import { create } from 'zustand';

interface Store {
    audioURL: string;
    setAudioURL: (audioURL: string) => void;
    volume: number;
    setVolume: (volume: number) => void;
}

export const useVoiceStore = create<Store>()((set) => ({
    audioURL: '',
    setAudioURL: (audioURL: string) => set({ audioURL: audioURL }),
    volume: 0,
    setVolume: (volume: number) => set({ volume: volume }),
}));
