import { customAxios } from '@/shared';

export async function postProfileLogin(UUID: string, password: string) {
  const requestDto = {
    profileId: UUID,
    password: password,
  };

  return customAxios.post(`/auth/profileLogin`, requestDto).catch((err) => {
    throw err;
  });
}
