import { decodeToken } from '@/shared';
import { getProfileList } from '../../query';

export async function handleFetchProfileList(accessToken: string, setProfileList: any) {
  try {
    const response = await getProfileList(decodeToken('access', true)!);
    console.log(decodeToken('access', true));
    console.log(response);
    if (response && response.data) {
      setProfileList(response.data.profileList);
    }
  } catch (err) {
    console.log(err);
  }
}
