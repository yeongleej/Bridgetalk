import { customAxios } from '@/shared';

export function postVoiceBlob(reportId: number, voice: Blob) {
  const formData = new FormData();

  formData.append('reportsId', String(reportId));
  formData.append('lettersFile', voice);

  customAxios
    .post('/letters/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })
    .then((res) => {
      console.log(res);
    })
    .catch((err) => console.log(err));
}
