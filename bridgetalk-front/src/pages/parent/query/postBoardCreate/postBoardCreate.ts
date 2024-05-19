import { customAxios } from '@/shared';

interface DTO {
  reportsId: number;
  boardsTitle: string;
  boardsContent: string;
  language: any;
}

export async function postBoardCreate(requestDto: DTO) {
  return customAxios.post(`/boards`, requestDto).catch((err) => {
    throw err;
  });
}
