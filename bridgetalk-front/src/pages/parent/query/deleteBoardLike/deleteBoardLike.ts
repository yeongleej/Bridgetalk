import { customAxios } from '@/shared';

export async function deleteBoardLike(boardsId: number) {
  return customAxios.delete(`/boards/likes/${boardsId}`).catch((err) => {
    throw err;
  });
}
