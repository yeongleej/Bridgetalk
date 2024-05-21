import { customAxios } from '@/shared';

/**
 * 답글 삭제
 * @param commentsId number
 * @returns
 */
export async function deleteComment(commentsId: number) {
  return customAxios.delete(`/comments/${commentsId}`).catch((err) => {
    throw err;
  });
}
