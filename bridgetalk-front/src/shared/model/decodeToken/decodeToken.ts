export function decodeToken(tokenType: 'access' | 'refresh') {
  return atob(sessionStorage.getItem(btoa('access' + process.env.REACT_APP_SECURE_CODE))!).split(
    `${process.env.REACT_APP_SECURE_CODE}`,
  )[0];
}
