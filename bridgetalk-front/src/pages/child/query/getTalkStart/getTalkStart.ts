import { customAxios } from '@/shared';
import axios from 'axios';

export async function getTalkStart(setReply: any) {
  return customAxios
    .get(`/reports/talk-start`, {
      responseType: 'blob',
    })
    .then((res) => {
      setReply(URL.createObjectURL(res.data));
    })
    .catch((err) => {
      throw err;
    });
}
