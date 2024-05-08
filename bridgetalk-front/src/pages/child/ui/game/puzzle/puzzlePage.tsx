import { JigsawPuzzle } from 'react-jigsaw-puzzle/lib';
import 'react-jigsaw-puzzle/lib/jigsaw-puzzle.css';

export function PuzzlePage() {
  return (
    <JigsawPuzzle imageSrc="/assets/img/pic/halongbay.jpg" rows={4} columns={5} onSolved={() => alert('Solved!')} />
  );
}
