import axios from 'axios';
import { getTalkStart } from '../../query';
import { errorCatch } from '@/shared';

export async function handleTalkStart(setReply: any, setErrorModalState: any) {
  try {
    const data = await getTalkStart(setReply);
    console.log(data);
  } catch (err) {
    if (err instanceof Error) {
      errorCatch(err, setErrorModalState);
    }
  }
}
