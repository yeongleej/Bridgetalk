import { postSignin } from '../../query';

export function handleSignin(requestDto: any) {
  postSignin(requestDto)
    .then((res: any) => {
      if (res.status === 200) {
        console.log(res.data);
        sessionStorage.setItem('token', res.data.accessToken);
      } else {
        alert('로그인 정보가 일치하지 않습니다.');
      }
    })
    .catch((err) => alert('로그인 정보가 일치하지 않습니다.'));
}
