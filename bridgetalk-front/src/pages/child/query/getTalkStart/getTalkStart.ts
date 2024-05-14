import { customAxios } from '@/shared';
import axios from 'axios';

export async function getTalkStart() {
  return customAxios
    .get(`/reports/talk-start-multipart`, {
      responseType: 'blob',
    })
    .catch((err) => {
      throw err;
    });
}
