import { customAxios } from '@/shared';

export async function getTalkStop(reportsId: number, setReply: any) {
  customAxios
    .get(`/reports/talk-stop`, {
      responseType: 'blob',
    })
    .then((res) => {
      setReply(URL.createObjectURL(res.data));
    })
    .catch((err) => console.log(err));
}
