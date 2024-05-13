import { customAxios } from '@/shared';

export async function postSendTalk(formData: any) {
  return customAxios
    .patch(`/reports/talk-send`, formData, {
      responseType: 'blob',
    })
    .catch((err) => {
      throw err;
    });
}
