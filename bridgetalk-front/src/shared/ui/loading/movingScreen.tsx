import React, { useEffect, useState } from 'react';

const images = [
  '/assets/img/child/shot1.svg',
  '/assets/img/child/shot2.svg',
  '/assets/img/child/shot3.svg',
  '/assets/img/child/shot4.svg',
  '/assets/img/child/shot5.svg',
  '/assets/img/child/shot6.svg',
  '/assets/img/child/shot7.svg',
  '/assets/img/child/shot8.svg',
];

export function MovingScreen({ onClose }: { onClose: () => void }) {
  const [currentImage, setCurrentImage] = useState(0);
  const [fadeOut, setFadeOut] = useState(false);

  useEffect(() => {
    const imageTimers = images.map(
      (_, index) =>
        setTimeout(() => {
          setCurrentImage(index);
        }, index * 200), // 0.5초 간격으로 이미지 변경
    );

    const fadeOutTimer = setTimeout(() => {
      setFadeOut(true);
    }, images.length * 200); // 마지막 이미지 후 1초 후에 페이드 아웃 시작

    const closeModalTimer = setTimeout(() => {
      onClose(); // 모달 닫기
    }, images.length * 200 + 200); // 페이드 아웃 완료 후 모달 닫기

    // 컴포넌트 언마운트 시 타이머 정리
    return () => {
      imageTimers.forEach(clearTimeout);
      clearTimeout(fadeOutTimer);
      clearTimeout(closeModalTimer);
    };
  }, [onClose]);

  return (
    <div
      className="movingScreen"
      style={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh',
        flexDirection: 'column',
        position: 'fixed',
        top: 0,
        left: 0,
        width: '100%',
        backgroundColor: '#FF9F99',
        zIndex: 1000,
      }}
    >
      <img
        src={images[currentImage]}
        alt={`image${currentImage + 1}`}
        style={{
          width: '20%',
          transition: 'opacity 1s ease-out',
          opacity: fadeOut ? 0 : 1,
        }}
      />
    </div>
  );
}
