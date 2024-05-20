import { customAxios } from '@/shared';

export async function getCommentLikeCheck(commentsId: number) {
  return customAxios.get(`/comments/likes/${commentsId}`).catch((err) => {
    throw err;
  });
}
