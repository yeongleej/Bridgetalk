import { customAxios } from '@/shared';

export async function getSlang(page: number = 0, size: number = 10) {
  return customAxios
    .get('/slang', {
      params: {
        page: page,
        size: size,
      },
    })
    .catch((err) => err);
}
