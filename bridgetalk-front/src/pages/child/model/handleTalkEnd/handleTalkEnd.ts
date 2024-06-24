import { errorCatch } from '@/shared';
import { getTalkStop, getTalkUpdate } from '../../query';
import { decodeFormData } from '../decodeFormData/decodeFormData';

export async function handleTalkEnd(
  setReply: any,
  setIsTalking: any,
  setIsEnd: any,
  setIsRecording: any,
  isRecording: any,
  devounceTimerRef: any,
  isEnd?: any,
  isTalking?: any,
  navigate?: any,
  setEmotion?: any,
  setSubtitle?: any,
  isStart?: any,
) {
  // 대화 시작 이전에 나가기 가능
  if (!isStart) {
    navigate('/child');
    return;
  }

  // 대화가 모두 끝났을 땐 나가기 가능
  if (!isTalking && isEnd) {
    navigate('/child');
    return;
  }

  // 대화 마치기 누르고 마무리 멘트 나오는 동안 다시 실행되는 것 방지
  if (!isRecording && !isTalking) return;

  try {
    const stopRes = await getTalkStop(setReply);
    const data = await decodeFormData(stopRes);

    setReply(data.audioValue);
    setEmotion(data.emotionValue);
    setSubtitle(data.subtitleValue);
    setIsRecording(false);
    setIsTalking(false);

    if (devounceTimerRef.current !== null) {
      clearInterval(devounceTimerRef.current);
    }
  } catch (err) {
    // errorCatch(err, setErrorModal);
  }

  try {
    const updateRes = await getTalkUpdate();
  } catch (err) {}
}
