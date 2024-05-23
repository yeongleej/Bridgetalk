import React, { useState, useEffect, useRef } from 'react';
import { useParams } from 'react-router-dom';
import { customAxios } from '@/shared';
import { Canvas, extend } from '@react-three/fiber';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import { CameraControls } from '@/pages/child/ui/talk/components/cameraControl';
import { Dino } from '@/pages/child/ui/talk/components/dino';
import * as S from '@/styles/child/talk/message.style';

extend({ OrbitControls });

interface ImageResponse {
  lettersImgUrl: string;
}

export function Message() {
  const { id } = useParams();
  const [message, setMessage] = useState('');
  const [imageUrls, setImageUrls] = useState<string[]>([]);
  const [voiceData, setVoiceData] = useState('');
  const [isPlaying, setIsPlaying] = useState(false);
  const audioRef = useRef<HTMLAudioElement>(null);

  useEffect(() => {
    const fetchMessage = async () => {
      try {
        const response = await customAxios.get(`/letters/text/${id}`);
        setMessage(response.data.lettersTranslationContent);
      } catch (error) {
        console.error('Failed to fetch letters:', error);
      }
    };

    const fetchImageUrls = async () => {
      try {
        const response = await customAxios.get(`/letters/img/${id}`);
        setImageUrls(response.data.map((item: ImageResponse) => item.lettersImgUrl));
      } catch (error) {
        console.error('Failed to fetch image URLs:', error);
      }
    };

    fetchMessage();
    fetchImageUrls();
  }, [id]);

  useEffect(() => {
    const fetchVoiceData = async () => {
      try {
        const response = await customAxios.get(`/letters/${id}`, {
          responseType: 'blob',
        });

        if (response.status === 200) {
          const audioURL = URL.createObjectURL(response.data);
          setVoiceData(audioURL);
          // console.log('API 응답 상태 코드:', response.status);
        } else {
          // console.error('오디오 데이터를 가져오는 데 실패했습니다:', response.statusText);
        }
      } catch (error) {
        // console.error('오디오 데이터를 가져오는 중 오류 발생:', error);
      }
    };

    if (id) {
      fetchVoiceData();
    }
  }, [id]);

  const handleCanvasClick = () => {
    if (audioRef.current) {
      audioRef.current
        .play()
        .then(() => {
          setIsPlaying(true);
        })
        .catch((error) => {
          // console.error('오디오 재생 중 오류 발생:', error);
        });
      // console.log('오디오 재생');
    }
  };

  return (
    <S.Container>
      <div className="message">
        <div className="message__reader">
          <div
            className="message__reader-icons"
            style={{
              visibility: isPlaying ? 'visible' : 'hidden',
              opacity: isPlaying ? 1 : 0,
              transition: 'opacity 3s',
            }}
          >
            {imageUrls.slice(0, 2).map((url, index) => (
              <div className="message__reader-icons-icon" key={index}>
                <img src={url} alt={`letters image ${index + 1}`} />
              </div>
            ))}
          </div>
          <div className="message__reader-dino">
            <Canvas camera={{ position: [0, 0, 1.2], fov: 50 }} onClick={handleCanvasClick}>
              <ambientLight intensity={2} />
              <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} />
              <pointLight position={[-10, -10, -10]} />
              <Dino />
              <CameraControls />
            </Canvas>
            <audio ref={audioRef} src={voiceData} controls />
          </div>
          <div
            className="message__reader-icons"
            style={{
              visibility: isPlaying ? 'visible' : 'hidden',
              opacity: isPlaying ? 1 : 0,
              transition: 'opacity 3s',
            }}
          >
            {imageUrls.slice(2).map((url, index) => (
              <div className="message__reader-icons-icon" key={index + 2}>
                <img src={url} alt={`letters image ${index + 3}`} />
              </div>
            ))}
          </div>
          <div className="message__reader-talk">
            <div className="message__reader-talk-content">
              <h3>다이노의 편지</h3>
              <p>(다이노를 눌러 음성 편지를 시작하세요!)</p>
              <hr />
              {message}
            </div>
          </div>
        </div>
      </div>
    </S.Container>
  );
}
