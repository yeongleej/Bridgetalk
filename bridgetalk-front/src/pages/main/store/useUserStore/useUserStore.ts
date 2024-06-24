import { create } from 'zustand';

interface Store {
  userId: string;
  setUserId: (userId: string) => void;

  // userName: 로그인 유저 이름
  userName: string;
  setUserName: (userName: string) => void;

  // userEmail: 로그인 유저 이메일
  userEmail: string;
  setUserEmail: (userEmail: string) => void;

  // userNickname: 로그인 유저 닉네임
  userNickname: string;
  setUserNickname: (userNickname: string) => void;

  // userDino: 로그인 유저 공룡 캐릭터
  userDino: string;
  setUserDino: (userDino: string) => void;

  // refreshToken: 로그인 유저 리프레시 토큰
  refreshToken: string;
  setRefreshToken: (refreshToken: string) => void;

  // accessToken: 로그인 유저 액세스 토큰
  accessToken: string;
  setAccessToken: (accessToken: string) => void;
}

export const useUserStore = create<Store>()((set) => ({
  // userId: 로그인 유저 UUID
  userId: '',
  setUserId: (userId: string) => set({ userId }),

  // userName: 로그인 유저 이름
  userName: '',
  setUserName: (userName: string) => set({ userName }),

  // userEmail: 로그인 유저 이메일
  userEmail: '',
  setUserEmail: (userEmail: string) => set({ userEmail }),

  // userNickname: 로그인 유저 닉네임
  userNickname: '',
  setUserNickname: (userNickname: string) => set({ userNickname }),

  // userDino: 로그인 유저 공룡 캐릭터
  userDino: '',
  setUserDino: (userDino: string) => set({ userDino }),

  // refreshToken: 로그인 유저 리프레시 토큰
  refreshToken: '',
  setRefreshToken: (refreshToken: string) => set({ refreshToken }),

  // accessToken: 로그인 유저 액세스 토큰
  accessToken: '',
  setAccessToken: (accessToken: string) => set({ accessToken }),
}));
