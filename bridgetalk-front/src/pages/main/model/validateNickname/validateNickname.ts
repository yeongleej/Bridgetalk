/**
 * 영어, 한글, 숫자를 포함한 1자 ~ 20자 이내 문자
 * @param nickname
 * @returns
 */
export function validateNickname(nickname: string) {
  const regex = /^[a-zA-Z0-9가-힣\s]{1,20}$/;
  return regex.test(nickname);
}
