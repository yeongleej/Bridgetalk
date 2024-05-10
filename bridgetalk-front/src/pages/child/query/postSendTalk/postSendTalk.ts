import { customAxios } from '@/shared';

export async function postSendTalk(reportsId: number, audio: Blob, setReply: any) {
  const formData = new FormData();

  formData.append('reportsFile', audio);

  return customAxios
    .patch(`/reports/talk-send/${reportsId}`, formData, {
      responseType: 'blob',
    })
    .then((res) => {
      setReply(res.data);
    })
    .catch((err) => console.log(err));
}
