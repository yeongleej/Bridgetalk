import { customAxios } from '@/shared';

export async function getMyBoardList(language: any, sort?: string) {
  return customAxios
    .get(`/boards/my/${language}`, {
      params: {
        sort,
      },
    })
    .catch((err) => {
      throw err;
    });
}
