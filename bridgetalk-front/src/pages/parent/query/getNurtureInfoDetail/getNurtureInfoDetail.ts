import { customAxios } from '@/shared';

export async function getNurtureInfoDetail(infoId: number, language: any) {
  return customAxios.get(`/parentingInfo/${infoId}/${language}`).catch((err) => err);
}
