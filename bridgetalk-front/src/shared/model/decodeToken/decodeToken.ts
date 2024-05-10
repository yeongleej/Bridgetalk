export function decodeToken(tokenType: 'access' | 'refresh') {
  if (localStorage.getItem(btoa(`${tokenType}` + process.env.REACT_APP_SECURE_CODE)) === null) {
    return null;
  }

  return atob(localStorage.getItem(btoa(`${tokenType}` + process.env.REACT_APP_SECURE_CODE))!).split(
    `${process.env.REACT_APP_SECURE_CODE}`,
  )[0];
}
