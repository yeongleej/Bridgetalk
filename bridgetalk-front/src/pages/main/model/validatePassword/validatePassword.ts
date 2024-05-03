/**
 * 영문 1글자, 숫자 1글자 포함. 8자 ~ 20자. 특수문자(!@#$%^&*+=-) 포함 가능
 * @param password
 * @returns
 */
export function validatePassword(password: string) {
  const regex = /^(?=.*[a-zA-Z])(?=.*\d)[A-Za-z\d!@#$%^&*+=-]{8,20}$/;
  return regex.test(password);
}
