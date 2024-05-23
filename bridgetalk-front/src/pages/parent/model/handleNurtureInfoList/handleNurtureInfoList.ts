import { getNurtureInfoList } from '../../query';

export async function handleNurtureInfoList(
  language: 'kor' | 'viet' | 'ph',
  setInfoList: any,
  page: number,
  setLastPage: any,
  searchCategory?: '' | 'prospective' | 'infant_and_toddler' | 'school' | 'puberty',
) {
  try {
    const fetchData = await getNurtureInfoList(language, page, searchCategory);

    setInfoList(fetchData.data.parentingInfoList);
    setLastPage(fetchData.data.pageInfo.totalPages);
    // console.log('{handleNurtureInfoList: fetchData', fetchData);
  } catch (err) {
    // console.log(err);
  }
}
