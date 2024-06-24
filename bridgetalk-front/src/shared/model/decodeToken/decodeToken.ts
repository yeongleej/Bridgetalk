export function decodeToken(tokenType: 'access' | 'refresh', isParent: boolean = false) {
  if (localStorage.getItem(btoa(`${tokenType}` + process.env.REACT_APP_SECURE_CODE)) === null) {
    return null;
  }

  if (isParent) {
    return atob(sessionStorage.getItem(btoa(`${tokenType}` + process.env.REACT_APP_SECURE_CODE))!).split(
      `${process.env.REACT_APP_SECURE_CODE}`,
    )[0];
  }

  return atob(localStorage.getItem(btoa(`${tokenType}` + process.env.REACT_APP_SECURE_CODE))!).split(
    `${process.env.REACT_APP_SECURE_CODE}`,
  )[0];
}
