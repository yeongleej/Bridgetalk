import create from 'zustand';

interface PuzzlePiece {
  id: number;
  x: number;
  y: number;
  rotation: number; // Optional: if you want to add rotation mechanics
}

interface PuzzleStore {
  pieces: PuzzlePiece[];
  setPieces: (pieces: PuzzlePiece[]) => void;
}

export const usePuzzleStore = create<PuzzleStore>((set) => ({
  pieces: [],
  setPieces: (pieces) => set({ pieces }),
}));
