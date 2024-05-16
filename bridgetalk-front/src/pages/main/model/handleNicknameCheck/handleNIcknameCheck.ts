import { errorCatch } from '@/shared';
import { getNicknameCheck } from '../../query';

export async function handleNicknameCheck(nickname: string, setErrorModalState: any) {
  try {
    const response = await getNicknameCheck(nickname);

    if (response.status == 200) {
      return true;
    }
    return false;
  } catch (err) {
    console.log(err);
    if (err instanceof Error) {
      console.log(err);
      errorCatch(err, setErrorModalState);
      return false;
    }
  }
}
