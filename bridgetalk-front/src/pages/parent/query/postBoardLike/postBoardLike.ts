import { customAxios } from '@/shared';

export async function postBoardLike(boardsId: number) {
  return customAxios.post(`/boards/likes/${boardsId}`).catch((err) => {
    throw err;
  });
}
