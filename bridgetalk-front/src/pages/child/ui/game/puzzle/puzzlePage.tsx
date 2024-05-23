import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { customAxios } from '@/shared';
import { JigsawPuzzle } from 'react-jigsaw-puzzle/lib';
import 'react-jigsaw-puzzle/lib/jigsaw-puzzle.css';
import * as S from '@/styles/child/game/puzzle.style';

export function PuzzlePage() {
  const navigate = useNavigate();

  const handleSolved = () => {
    alert('축하합니다! 퍼즐을 풀었어요!');
    navigate(`/finish/${id}`); // navigate inside a setTimeout if needed
  };

  const { id } = useParams();
  const [puzzle, setPuzzle] = useState('');

  useEffect(() => {
    const fetchPuzzle = async () => {
      try {
        const response = await customAxios.get(`/puzzle/${id}`, {});
        setPuzzle(response.data.puzzleImageUrl);
        // console.log('API Response Status Code:', response.status); // 콘솔에 상태 코드 출력
      } catch (error) {
        console.error('Failed to fetch puzzle data:', error);
      }
    };

    if (id) {
      fetchPuzzle();
    }
  }, [id]);

  const [time, setTime] = useState<number>(0); // 타이머의 시간을 초 단위로 저장

  useEffect(() => {
    const timerId = setInterval(() => {
      setTime((prevTime) => prevTime + 1); // 매 초마다 시간을 1초씩 증가
    }, 1000);

    return () => clearInterval(timerId); // 컴포넌트가 언마운트될 때 타이머를 정리
  }, []);

  // 시간을 MM:SS 형식으로 변환
  const formatTime = (time: number): string => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${padTime(minutes)}분 ${padTime(seconds)}초`;
  };

  // 숫자가 한 자리일 경우, 앞에 '0'을 붙여 두 자리로 만듦
  const padTime = (time: number): string => {
    return time.toString().padStart(2, '0');
  };

  return (
    <S.Container>
      <div className="puzzlePage">
        <img
          src="/assets/img/child/icon/toBack.svg"
          alt=""
          style={{ width: '10svh', position: 'fixed', top: '0', left: '0', margin: '5svh' }}
          onClick={() => {
            navigate('/stage');
          }}
        />
        <div className="puzzlePage__side">
          <img src={puzzle} alt="" />
          <span>
            퍼즐
            <br />
            맞추기!
          </span>
          <p>
            빠른 시간 안에
            <br />
            퍼즐을 맞춰보세요!
          </p>
          <div className="puzzlePage__side-timer">{formatTime(time)}</div>
        </div>
        <div className="puzzlePage__puzzle">
          <JigsawPuzzle imageSrc={puzzle} rows={3} columns={4} onSolved={handleSolved} />
        </div>
      </div>
    </S.Container>
  );
}
