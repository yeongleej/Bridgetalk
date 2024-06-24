import axios from 'axios';
import { postSendTalk } from '../../query';
import { decodeFormData } from '../decodeFormData/decodeFormData';
import { webmToMp3 } from '../webmToMp3/webmToMp3';
import { errorCatch } from '@/shared';

export async function handleTalkSend(
  audio: Blob,
  setReply: any,
  setEmotion: any,
  setSubtitle: any,
  setErrorModalState: any,
  setIsRecording: any,
) {
  // console.log('{handleTalkSend: 한마디 전송 함수 호출');

  const mp3Blob = await webmToMp3(audio);
  // console.log('{handleTalkSend: mp3 blob 생성 완료');

  const formData = new FormData();
  formData.append('reportsFile', mp3Blob);

  try {
    const response = await postSendTalk(formData);
    // console.log(`{handleTalkSend: 한마디 전송 API 호출 완료 }`);
    const data = await decodeFormData(response);

    setSubtitle(data.subtitleValue);
    setEmotion(data.emotionValue);
    setReply(data.audioValue);

    setTimeout(() => {
      URL.revokeObjectURL(data.audioValue);
    }, 20000);
  } catch (err) {
    if (err instanceof Error) {
      setIsRecording(true);
      errorCatch(err, setErrorModalState);
    }
  }
  // if (response) {
  //   const parsedData = await decodeFormData(response);
  //   console.log(parsedData);

  //   setSubtitle(parsedData.subtitleValue);
  //   setEmotion(parsedData.emotionValue);
  //   setReply(URL.createObjectURL(parsedData.audioValue));
  //   console.log(parsedData.audioValue);
  //   console.log(URL.createObjectURL(parsedData.audioValue));
  // }
}
