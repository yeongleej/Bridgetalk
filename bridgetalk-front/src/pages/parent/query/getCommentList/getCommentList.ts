import { customAxios } from '@/shared';

export async function getCommentList(language: any) {
  return customAxios.get(`/comments/read/${language}`).catch((err) => {
    throw err;
  });
}
