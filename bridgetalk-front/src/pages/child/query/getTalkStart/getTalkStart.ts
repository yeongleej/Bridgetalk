import { customAxios } from '@/shared';

export async function getTalkStart(setReply: any) {
  return customAxios
    .get(`/reports/talk-start`, {
      responseType: 'blob',
    })
    .then((res) => {
      setReply(URL.createObjectURL(res.data));
    })
    .catch((err) => console.log(err));
}
