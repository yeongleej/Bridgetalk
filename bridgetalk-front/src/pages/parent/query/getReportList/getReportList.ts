import { customAxios } from '@/shared';

interface Language {
  type: 'kor' | 'viet' | 'ph';
}

/**
 * getReportList: 아이 속마음 분석 리포트 리스트 가져오기
 * @param kidsId 'string'
 * @param language 'kor' | 'viet'
 * @returns
 */
export async function getReportList(kidsId: string, language: Language['type']) {
  return customAxios.get(`/reports/${kidsId}/${language}`).catch((err) => console.log(err));
}
