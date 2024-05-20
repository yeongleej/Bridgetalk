import { customAxios } from '@/shared';

export async function postAuthVerify(email: string, authCode: string) {
  const dto = {
    email,
    authCode,
  };
  return customAxios.post(`/mail/authcode-verify`, dto).catch((err) => {
    throw err;
  });
}
