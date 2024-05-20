import { customAxios } from '@/shared';

export async function deleteCommentLike(commentsId: Number) {
  return customAxios.delete(`/comments/likes${commentsId}`).catch((err) => {
    throw err;
  });
}
