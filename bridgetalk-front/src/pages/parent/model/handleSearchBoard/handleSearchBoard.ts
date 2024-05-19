import { getBoardList } from '../../query';

export async function handleSearchBoard(
  language: any,
  page: number,
  searchType: string,
  searchWord: string,
  sort: string,
) {
  try {
    const data = await getBoardList(language, page, searchType, searchWord, sort);
    return data;
  } catch (err) {}
}
