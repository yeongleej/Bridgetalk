import { create } from 'zustand';

interface Store {
  deleteModalOpenState: any;
  setDeleteModalOpenState: any;
  passwordCheckModalState: any;
  setPasswordCheckModalState: any;
  editProfileModalState: any;
  setEditProfileModalState: any;
  deleteProfileModalState: any;
  setDeleteProfileModalState: any;
}

export const useProfileStore = create<Store>()((set) => ({
  deleteModalOpenState: false,
  setDeleteModalOpenState: (deleteModalState: any) =>
    set({
      deleteModalOpenState: deleteModalState,
    }),
  passwordCheckModalState: false,
  setPasswordCheckModalState: (state: []) => set({ passwordCheckModalState: state }),
  editProfileModalState: false,
  setEditProfileModalState: (state: any) => set({ editProfileModalState: state }),
  deleteProfileModalState: false,
  setDeleteProfileModalState: (state: any) => set({ deleteModalOpenState: state }),
}));
