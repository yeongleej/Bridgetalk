import { customAxios } from '@/shared';

interface Dto {
  parentsId: string;
  kidsName: string;
  kidsNickname: string;
  kidsDino: string;
  kidsPassword: string;
}

export async function postAddProfile(requestDto: Dto) {
  return customAxios.post('/auth/kids', requestDto).catch((err) => err);
}
