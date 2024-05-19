import { customAxios } from '@/shared';

export async function getBoardLikeCheck(boardsId: number) {
  return customAxios.get(`/boards/likes/${boardsId}`).catch((err) => console.log(err));
}
