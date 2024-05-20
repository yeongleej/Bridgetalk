import { customAxios } from '@/shared';

/**
 * 게시글 삭제
 * @param boardsId
 * @returns
 */
export async function deleteBoard(boardsId: number) {
  return customAxios.delete(`/boards/${boardsId}`).catch((err) => {
    throw err;
  });
}
