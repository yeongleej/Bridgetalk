import { customAxios } from '@/shared';

export async function postMakeReport(setReportsId: any) {
  customAxios
    .post(`/reports`, {}, {})
    .then((res) => setReportsId(res.data.reportsId))
    .catch((err) => {
      // console.log(err);
    });
}
