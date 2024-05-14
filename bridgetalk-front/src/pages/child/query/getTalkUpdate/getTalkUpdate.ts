import { customAxios } from '@/shared';

export async function getTalkUpdate() {
  return customAxios.get('/reports/talk-update').catch((err) => err);
}
