import { customAxios } from '@/shared';

interface DTO {
  boardsId: number;
  commentsContent: string;
  language: any;
}
export async function postCommentCreate(requestDto: DTO) {
  return customAxios.post(`/comments`, requestDto).catch((err) => {
    throw err;
  });
}
