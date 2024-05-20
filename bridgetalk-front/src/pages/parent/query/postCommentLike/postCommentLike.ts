import { customAxios } from '@/shared';

export async function postCommentLike(commentsId: Number) {
  return customAxios.post(`/comments/likes/${commentsId}`).catch((err) => {
    throw err;
  });
}
