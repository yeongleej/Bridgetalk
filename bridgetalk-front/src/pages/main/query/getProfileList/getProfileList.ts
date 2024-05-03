import { customAxios } from '@/shared';

export async function getProfileList() {
  return customAxios
    .get('/profile')
    .then((res) => res)
    .catch((err) => console.log(err));
}
