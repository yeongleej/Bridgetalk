import axios from 'axios';

const PERMISSION_DENIED = 'Permission denied';

export function errorCatch(err: Error, setErrorModalState: any) {
  if (axios.isAxiosError(err)) {
    if (err.response) {
      const contentType = err.response.headers['content-type'];

      if (err.response.data instanceof Blob) {
        const reader = new FileReader();

        reader.onload = () => {
          const text: any = reader.result;

          try {
            const errorData = JSON.parse(text);
            return handleError(errorData, setErrorModalState);
          } catch (err) {
            setErrorModalState('예기치 못한 에러가 발생했습니다. 잠시 후 다시 시도해주세요.');
          }
        };

        reader.readAsText(err.response.data);
      } else if (contentType && typeof contentType === 'string' && contentType.includes('application/json')) {
        const errorData = err.response.data;
        return handleError(errorData, setErrorModalState);
      }
    }
  } else {
    if (PERMISSION_DENIED.match(err.message)) {
      setErrorModalState('마이크 권한을 허용해주세요!');
    }
  }
}

function handleError(errorData: any, setErrorModalState: any) {
  switch (errorData.errorCode) {
    case 'PARENTS_002':
      setErrorModalState('이메일 형식이 올바르지 않습니다.');
      break;
    case 'PARENTS_003':
      setErrorModalState('가입된 계정을 찾을 수 없습니다.');
      break;
    case 'AUTH_001':
      setErrorModalState('이미 존재하는 닉네임입니다.');
      break;
    case 'AUTH_005':
      setErrorModalState('비밀번호가 일치하지 않습니다.');
      break;
    case 'GPT_004':
      setErrorModalState('더 많은 이야기를 해주세요!');
      break;
    case 'REPORTS_004':
      setErrorModalState('조금 더 길게 이야기해 보아요!');
      break;
    case 'REPORTS_005':
      setErrorModalState('이미 시작된 대화가 있습니다. 이어서 이야기해 보아요!');
      break;
    default:
      setErrorModalState(errorData.message);
      break;
  }
  return false;
}
