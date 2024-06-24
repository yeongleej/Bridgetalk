import { customAxios } from '@/shared';

interface DeleteBoardDto {
  boardsTitle: string;
  boardsContent: string;
  language: any;
}

/**
 *
 * @param boardsId
 * @param requestDto boardsTitle, boardsContent, language
 * @returns
 */
export async function deleteBoard(boardsId: number, requestDto: DeleteBoardDto) {
  return customAxios
    .delete(`/boards/${boardsId}`, {
      data: requestDto,
    })
    .catch((err) => {
      throw err;
    });
}
