import create from 'zustand';

interface Profile {
  userId: string;
  userName: string;
  userEmail: string;
  userNickname: string;
  userDino: string;
}

interface ProfileState {
  profiles: Profile[];
  setProfiles: (profiles: Profile[]) => void;
}

export const useProfileStore = create;
