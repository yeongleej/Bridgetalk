import { customAxios } from '@/shared';

/**
 * 게시글 수정
 * @param boardsId
 * @returns
 */
export async function patchBoard(boardsId: number) {
  return customAxios.patch(`/boards/${boardsId}`).catch((err) => {
    throw err;
  });
}
