import { customAxios } from '@/shared';

export async function getCommentList(language: any, boardId: number) {
  return customAxios.get(`/comments/read/${boardId}/${language}`).catch((err) => {
    throw err;
  });
}
