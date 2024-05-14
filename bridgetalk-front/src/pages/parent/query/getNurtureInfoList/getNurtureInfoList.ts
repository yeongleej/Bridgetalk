import { customAxios } from '@/shared';

export async function getNurtureInfoList(language: 'kor' | 'viet', page: number = 0, searchCategory: string = '') {
  return customAxios
    .get(`/parentingInfo/${language}`, {
      params: {
        page: page,
        searchCategory: searchCategory,
      },
    })
    .catch((err) => err);
}
