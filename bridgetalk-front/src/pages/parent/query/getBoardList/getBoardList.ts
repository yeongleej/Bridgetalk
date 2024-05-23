import { customAxios } from '@/shared';

export async function getBoardList(searchWord: string, language: any, page: number, searchType: any, sort: any) {
  return customAxios
    .get(`/boards/read/${language}`, {
      params: {
        page,
        searchType,
        searchWord,
        sort,
      },
    })
    .catch((err) => {
      throw err;
    });
}

/**
 *     
 * TITLE("제목"),
    CONTENT_AND_REPORTS_SUMMARY("내용과레포트요약"),
    WRITER("작성자"),
    REPORTS_KEYWORD("레포트 키워드"),
    TITLE_AND_CONTENT_AND_REPORTS("제목과내용과레포트"),
    ;
 */
