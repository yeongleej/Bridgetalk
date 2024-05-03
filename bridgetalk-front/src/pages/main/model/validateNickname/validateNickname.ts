export function validateNickname(nickname: string) {
  const regex = /^[a-zA-Z0-9가-힣\s]{1,20}$/;
  return regex.test(nickname);
}
