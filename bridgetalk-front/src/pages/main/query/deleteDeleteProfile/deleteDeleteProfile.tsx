import { customAxios } from '@/shared';

export async function deleteDeleteProfile(UUID: string) {
  return customAxios.delete(`/profile/${UUID}`).catch((err) => err);
}
