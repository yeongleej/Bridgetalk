import { customAxios } from '@/shared';

export async function postAuthSend(email: string) {
  const dto = {
    email,
  };

  return customAxios.post(`/mail/authcode-send`, dto).catch((err) => {
    throw err;
  });
}
