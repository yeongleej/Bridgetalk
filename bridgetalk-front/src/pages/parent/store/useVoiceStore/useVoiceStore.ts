import { create } from 'zustand';

interface Store {
    audioURL: string;
    setAudioURL: (audioURL: string) => void;
}

export const useVoiceStore = create<Store>()((set) => ({
    audioURL: '',
    setAudioURL: (audioURL: string) => set(() => ({ audioURL: audioURL })),
}));
