import { customAxios } from '@/shared';

export async function deleteDeleteProfile(UUID: string, password: string) {
  const requestDto = { profileId: UUID, password };
  return customAxios
    .delete(`/profile`, {
      data: requestDto,
    })
    .catch((err) => {
      throw err;
    });
}
