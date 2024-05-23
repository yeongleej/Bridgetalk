import { errorCatch, setToken } from '@/shared';
import { postProfileLogin } from '../../query';

export async function handleProfileLogin(uuid: string, password: string, userStore: any, setErrorStateModal: any) {
  try {
    const response = await postProfileLogin(uuid, password);

    if (response && response.data) {
      userStore.setUserDino(response.data.userDino);
      sessionStorage.setItem('dino', response.data.userDino);
      setToken(response.data.accessToken, response.data.refreshToken);

      return true;
    }
  } catch (err) {
    if (err instanceof Error) {
      errorCatch(err, setErrorStateModal);
    }
    return false;
  }
}
