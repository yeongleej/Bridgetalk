import { drawConnectors, drawLandmarks } from '@mediapipe/drawing_utils';
import { POSE_CONNECTIONS, Results } from '@mediapipe/pose';

/**
 * 긴팔 드레스를 사용자의 관절 위치에 맞게 변형하여 캔버스에 그립니다.
 * @param ctx - 캔버스의 2D 컨텍스트
 * @param results - Mediapipe의 Pose 솔루션 결과
 * @param dressImage - 드레스 이미지
 */
export const drawDress = (ctx: CanvasRenderingContext2D, results: Results, dressImage: HTMLImageElement) => {
  const width = ctx.canvas.width;
  const height = ctx.canvas.height;

  ctx.save();
  ctx.clearRect(0, 0, width, height);
  // canvas의 좌우 반전
  ctx.scale(-1, 1);
  ctx.translate(-width, 0);
  // capture image 그리기
  ctx.drawImage(results.image, 0, 0, width, height);
  // 포즈 묘사
  if (results.poseLandmarks) {
    const landmarks = results.poseLandmarks;

    // 관절 포인트 예시 (어깨, 팔꿈치, 손목)
    const leftShoulder = landmarks[11];
    const rightShoulder = landmarks[12];
    const leftElbow = landmarks[13];
    const rightElbow = landmarks[14];
    const leftWrist = landmarks[15];
    const rightWrist = landmarks[16];
    const leftHip = landmarks[23];
    const rightHip = landmarks[24];
    const leftKnee = landmarks[25];
    const rightKnee = landmarks[26];

    // 드레스 이미지를 해당 좌표에 맞게 그리기
    if (leftShoulder && rightShoulder && leftHip && rightHip) {
      const dressWidth = rightShoulder.x * width - leftShoulder.x * width;
      const dressHeight = leftHip.y * height - leftShoulder.y * height;

      // 드레스 이미지의 중심 좌표
      const centerX = ((leftShoulder.x + rightShoulder.x) / 2) * width;
      const centerY = ((leftShoulder.y + leftHip.y) / 2) * height;

      // 드레스 이미지를 회전 및 스케일하여 그리기
      ctx.translate(centerX, centerY);
      ctx.rotate(Math.atan2(rightShoulder.y - leftShoulder.y, rightShoulder.x - leftShoulder.x));
      ctx.drawImage(dressImage, -dressWidth / 2, -dressHeight / 2, dressWidth, dressHeight);
    }
  }
  ctx.restore();
};
