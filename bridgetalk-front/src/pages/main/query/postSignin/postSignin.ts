import { customAxios } from '@/shared';

export async function postSignin(signinDto: any) {
  return customAxios.post(`/auth/login`, signinDto).catch((err) => console.log(err));
}
