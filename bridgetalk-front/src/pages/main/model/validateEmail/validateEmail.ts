/**
 * 영어(소문자), 숫자. 5자 ~ 30자. 공백 입력 불가
 * @param email
 * @returns
 */
export function validateEmail(email: string) {
  const regex = /^(?=.{5,30}$)[a-z0-9]+@[a-z0-9]+\.[a-z]{2,}$/;
  return regex.test(email);
}
