import { customAxios } from '@/shared';

export async function getNurtureInfoDetail(infoId: number, language: any) {
  return customAxios.get(`/parentinginfo/${infoId}/${language}`).catch((err) => err);
}
