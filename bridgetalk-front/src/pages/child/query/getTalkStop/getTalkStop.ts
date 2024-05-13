import { customAxios } from '@/shared';
import { decodeFormData } from '../../model';

export async function getTalkStop(setReply: any) {
  return await customAxios
    .get(`/reports/talk-stop-multipart`, {
      responseType: 'blob',
    })
    // .then((res) => {
    //   setReply(URL.createObjectURL(res.data));
    // })
    .catch((err) => {
      throw err;
    });
}
