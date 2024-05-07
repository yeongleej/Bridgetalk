import { customAxios } from '@/shared';

interface Dto {
  nickname: string;
  dino: string;
}

export async function patchEditProfile(requestDto: Dto, UUID: string) {
  return customAxios.patch(`/profile/${UUID}`, requestDto).catch((err) => err);
}
