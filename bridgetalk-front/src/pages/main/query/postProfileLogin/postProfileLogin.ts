import { customAxios } from '@/shared';

export async function postProfileLogin(UUID: string) {
  return customAxios.post(`/auth/profileLogin/${UUID}`).catch((err) => err);
}
