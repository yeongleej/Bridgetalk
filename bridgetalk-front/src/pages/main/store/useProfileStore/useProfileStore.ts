import { create } from 'zustand';

interface Store {
  deleteModalOpenState: any;
  setDeleteModalOpenState: any;
}

export const useProfileStore = create<Store>()((set) => ({
  deleteModalOpenState: false,
  setDeleteModalOpenState: (deleteModalState: any) =>
    set({
      deleteModalOpenState: deleteModalState,
    }),
}));
