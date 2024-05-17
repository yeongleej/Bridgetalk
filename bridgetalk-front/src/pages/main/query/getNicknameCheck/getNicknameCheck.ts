import { customAxios } from '@/shared';

export async function getNicknameCheck(nickname: string) {
  return await customAxios.get(`/auth/nickname-duplicate/${nickname}`).catch((err) => {
    throw err;
  });
}
