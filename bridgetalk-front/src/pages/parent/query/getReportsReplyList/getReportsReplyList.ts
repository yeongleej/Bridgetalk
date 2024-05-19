import { customAxios } from '@/shared';

export async function getReportsReplyList(reportsId: number, langauge: any) {
  return customAxios.get(`/comments/read/reports/${reportsId}/${langauge}`).catch((err) => {
    console.log(err);
  });
}
