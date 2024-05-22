import { customAxios } from '@/shared';

/**
 * 답글 수정
 * @param commentsId
 * @returns
 */
export async function patchComment(commentsId: number) {
  return customAxios.patch(`/comments/${commentsId}`).catch((err) => {
    throw err;
  });
}
