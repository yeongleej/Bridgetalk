import { customAxios } from '@/shared';

/**
 * postVoiceBlob() : 녹음 파일 전송
 * @param reportId : 답장할 리포트 번호
 * @param voice : 음성 녹음 파일
 */
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
      // console.log(res);
    })
    .catch((err) => {
      // console.log(err);
    });
}
