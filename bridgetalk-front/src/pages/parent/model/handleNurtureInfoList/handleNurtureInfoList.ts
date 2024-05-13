import { getNurtureInfoList } from '../../query';

export async function handleNurtureInfoList(language: 'kor' | 'viet', setInfoList: any) {
  try {
    const fetchData = await getNurtureInfoList(language);

    setInfoList(fetchData.parentingInfoList);
  } catch (err) {
    console.log(err);
  }
}
