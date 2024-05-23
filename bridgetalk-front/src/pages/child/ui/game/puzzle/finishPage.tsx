import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { customAxios } from '@/shared';
import * as S from '@/styles/child/game/finish.style';

interface Landmark {
  puzzleId: number;
  puzzleNation: string;
  puzzleImageUrl: string;
  puzzleLandmarkName: string;
  puzzleLandmarkContent: string;
}

export function FinishPage() {
  const { id } = useParams();
  const [landmark, setLandmark] = useState<Landmark>();

  useEffect(() => {
    const fetchLandmark = async () => {
      try {
        const response = await customAxios.get(`/puzzle/${id}`, {});
        setLandmark(response.data);
        // console.log('API Response Status Code:', response.status);
      } catch (error) {
        // console.error('Failed to fetch landmark data:', error);
      }
    };

    if (id) {
      fetchLandmark();
    }
  }, [id]);

  const navigate = useNavigate();

  const handleSavePicture = () => {
    if (landmark?.puzzleImageUrl) {
      const link = document.createElement('a');
      link.href = landmark.puzzleImageUrl;
      link.download = 'puzzle_image.jpg'; // 다운로드될 파일 이름
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    }
  };

  return (
    <S.Container>
      <div className="finishPage">
        <div className="finishPage__header">
          <div className="finishPage__header-title"></div>
        </div>
        <div className="finishPage__container">
          <img src="/assets/img/child/game/finishTitle.svg" alt="" />
          <div className="finishPage__container-img">
            <img src={landmark?.puzzleImageUrl} alt="landmark" />
          </div>
          <div className="finishPage__container-side">
            <div className="finishPage__container-side-title">{landmark?.puzzleLandmarkName}</div>
            <div className="finishPage__container-side-description">{landmark?.puzzleLandmarkContent}</div>
            <div className="finishPage__container-side-savePic" onClick={handleSavePicture}>
              <img src="/assets/child/save.svg" alt="" />
              <span>저장하기</span>
            </div>
            <div className="finishPage__container-side-toBack">
              <img src="/assets/child/back.svg" alt="" />
              <span
                onClick={() => {
                  navigate('/stage');
                }}
              >
                목록으로
              </span>
            </div>
          </div>
        </div>
      </div>
    </S.Container>
  );
}
