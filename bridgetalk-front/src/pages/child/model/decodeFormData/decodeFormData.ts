import axios from 'axios';
import { webmToMp3 } from '../webmToMp3/webmToMp3';

export async function decodeFormData(data: any) {
  const responseFormData = await data.data.text();

  const key = responseFormData.split('\r\n')[0];

  /** key 를 기준으로 split한 배열 */
  const parsedArray_1 = responseFormData.split(key);

  const subtitleArray = parsedArray_1[1].split('\r\n');
  const subtitleValue = subtitleArray[5].trim();

  const emotionArray = parsedArray_1[2].split('\r\n');
  const splitedEmotionValue = emotionArray[5].split('"');
  let emotionValue = '';

  for (let word of splitedEmotionValue) {
    emotionValue += word.trim();
  }

  const audioArray = parsedArray_1[3];
  let [_, disposition, type, length, header, ...audioData] = audioArray.split('\r\n');

  const audioValue = URL.createObjectURL(data.data);

  return {
    subtitleValue,
    emotionValue,
    audioValue,
  };
}
