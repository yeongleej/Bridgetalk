import { useMutation } from 'react-query';
import { customAxios } from '@/shared';
import Cookies from 'js-cookie';

interface LoginCredentials {
  parents_email: string;
  parents_password: string;
}

export function useLogin() {
  return useMutation((credentials: LoginCredentials) => customAxios.post('/api/auth/login', credentials), {
    onSuccess: (data) => {
      // 쿠키에 accessToken과 refreshToken 저장
      Cookies.set('accessToken', data.data.accessToken, { expires: 1 / 24 }); // 1시간 유효
      Cookies.set('refreshToken', data.data.refreshToken, { expires: 14 }); // 14일 유효
    },
  });
}
