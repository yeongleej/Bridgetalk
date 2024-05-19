import { getBoardList } from '../../query';

export async function handleSearchBoard(
  searchWord: string,
  language: any,
  page: number,
  searchType: string,
  sort: string,
) {
  console.log(searchWord, language, page, searchType, sort);
  try {
    const data = await getBoardList(searchWord, language, page, searchType, sort);
    return data;
  } catch (err) {
    throw err;
  }
}
