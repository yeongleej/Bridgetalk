import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { customAxios } from '@/shared';
import { StageItem } from './item/stageItem';
import * as S from '@/styles/child/game/stage.style';

interface Puzzle {
  puzzleId: number;
  puzzleNation: string;
  puzzleImageUrl: string;
  puzzleLandmarkName: string;
  puzzleLandmarkContent: string;
}

export function StagePage() {
  const [puzzles, setPuzzles] = useState<Puzzle[]>([]);
  const [nation, setNation] = useState<'viet' | 'ph'>('viet');
  const navigate = useNavigate();

  const fetchPuzzles = async (nation: 'viet' | 'ph') => {
    try {
      const response = await customAxios.get(`/puzzle/list/${nation}`);
      setPuzzles(response.data.puzzleList);
    } catch (error) {
      console.error('Failed to fetch puzzles:', error);
    }
  };

  useEffect(() => {
    fetchPuzzles(nation);
  }, [nation]);

  const toggleNation = () => {
    const newNation = nation === 'viet' ? 'ph' : 'viet';
    setNation(newNation);
  };

  return (
    <S.Container>
      <div className="stagePage">
        <div className="stagePage__header">
          <img
            src="/assets/img/child/icon/toBack.svg"
            alt="뒤로 가기"
            onClick={() => {
              navigate('/game');
            }}
          />
          <img src="/assets/img/child/icon/stages.svg" alt="" style={{ width: '30svw' }} />
          <div className="stagePage__header-lang" onClick={toggleNation}>
            <img src={`/assets/flag/${nation}.png`} alt={nation} />
          </div>
        </div>
        <div className="stagePage__container">
          {puzzles.map((puzzle) => (
            <div className="stageItem" key={puzzle.puzzleId}>
              <StageItem id={puzzle.puzzleId.toString()} img={puzzle.puzzleImageUrl} name={puzzle.puzzleLandmarkName} />
            </div>
          ))}
        </div>
      </div>
    </S.Container>
  );
}
