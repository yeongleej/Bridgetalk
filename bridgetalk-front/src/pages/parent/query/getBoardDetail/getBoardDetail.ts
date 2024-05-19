import { customAxios } from '@/shared';

export async function getBoardDetail(boardsId: number, language: any) {
  return customAxios.get(`/boards/read/${boardsId}/${language}`).catch((err) => {
    throw err;
  });
}
