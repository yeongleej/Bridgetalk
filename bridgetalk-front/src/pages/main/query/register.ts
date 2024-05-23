// src/hooks/useRegister.ts
import { useMutation } from 'react-query';
import { customAxios } from '@/shared';
import { useUserStore } from '@/pages/main/store/user';

interface RegisterData {
  parentsEmail: string;
  parentsPassword: string;
  parentsName: string;
  parentsNickname: string;
  parentsDino: string;
}

export function useRegister() {
  const setUserData = useUserStore((state) => state.setUserData);

  return useMutation((data: RegisterData) => customAxios.post(`/api/auth/signup`, data), {
    onSuccess: (response) => {
      const { userId, userName, userEmail, userNickname, userDino } = response.data;
      setUserData({
        userId,
        userName,
        userEmail,
        userNickname,
        userDino,
      });
    },
  });
}
