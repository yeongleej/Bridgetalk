import { customAxios } from '@/shared';

interface Language {
  type: 'kor' | 'viet';
}

/**
 * getReportDetail: 아이 속마음 분석 리포트 상세정보 가져오기
 * @param kidsId: number
 * @param reportsId: number
 * @param language: 'kor' | 'viet'
 * @returns
 */
export async function getReportDetail(kidsId: number, reportsId: number, language: Language['type']) {
  return customAxios.get(`/reports/${kidsId}/${reportsId}/${language}`).then((err) => err);
}
