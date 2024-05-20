import { customAxios } from '@/shared';

export async function getProfileList(token: string) {
  return customAxios
    .get('/profile', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then((res) => res)
    .catch((err) => {
      // console.log(err);
    });
}
