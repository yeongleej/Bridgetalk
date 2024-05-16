import { customAxios } from '@/shared';

export async function getNicknameCheck(nickname: string) {
  console.log(nickname);

  return customAxios.get(`/auth/nickname-duplicate/${nickname}`).catch((err) => {
    console.log('에러 던지기');
    throw err;
  });
}
