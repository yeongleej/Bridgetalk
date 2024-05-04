export function decodeToken(tokenType: 'access' | 'refresh') {
  if (sessionStorage.getItem(btoa(`${tokenType}` + process.env.REACT_APP_SECURE_CODE)) === null) {
    return null;
  }

  return atob(sessionStorage.getItem(btoa(`${tokenType}` + process.env.REACT_APP_SECURE_CODE))!).split(
    `${process.env.REACT_APP_SECURE_CODE}`,
  )[0];
}
