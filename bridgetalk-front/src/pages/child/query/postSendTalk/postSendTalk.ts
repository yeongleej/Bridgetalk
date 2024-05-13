import { customAxios } from '@/shared';

export async function postSendTalk(formData: any) {
  return customAxios
    .patch(`/reports/talk-send-multipart`, formData, {
      responseType: 'blob',
    })
    .catch((err) => console.log(err));
}
