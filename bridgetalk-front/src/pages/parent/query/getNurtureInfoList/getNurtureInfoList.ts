import { customAxios } from '@/shared';

export async function getNurtureInfoList(language: 'kor' | 'viet') {
  return customAxios.get(`/parentinginfo/${language}`).catch((err) => err);
}
