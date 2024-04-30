import create from 'zustand';

interface UserState {
  userId: string;
  userName: string;
  userEmail: string;
  userNickname: string;
  userDino: string;
  setUserData: (userData: any) => void;
}

export const useUserStore = create<UserState>((set) => ({
  userId: '',
  userName: '',
  userEmail: '',
  userNickname: '',
  userDino: '',
  setUserData: (userData) => set(userData),
}));
