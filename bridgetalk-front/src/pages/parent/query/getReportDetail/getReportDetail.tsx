import { customAxios } from '@/shared';

interface Language {
  type: 'kor' | 'viet' | 'ph';
}

/**
 * getReportDetail: 아이 속마음 분석 리포트 상세정보 가져오기
 * @param kidsId string
 * @param reportsId number
 * @param language 'kor' | 'viet'
 * @returns
 */
export async function getReportDetail(kidsId: string, reportsId: number, language: Language['type']) {
  return customAxios.get(`/reports/${kidsId}/${reportsId}/${language}`).then((err) => err);
}
