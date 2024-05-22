import { customAxios } from '@/shared';

export async function postSignup(signupDto: any) {
  return customAxios.post(`/auth/signup`, signupDto).catch((err) => {
    // console.log(err);
  });
}
