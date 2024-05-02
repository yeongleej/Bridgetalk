import { customAxios } from '@/shared';

export async function postSignup(signupDto: any) {
  console.log(signupDto);
  return customAxios.post(`/auth/signup`, signupDto).catch((err) => console.log(err));
}
