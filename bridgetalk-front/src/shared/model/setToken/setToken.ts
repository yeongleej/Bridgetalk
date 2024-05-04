export function setToken(accessToken: string, refreshToken?: string) {
  sessionStorage.setItem(
    btoa('access' + process.env.REACT_APP_SECURE_CODE),
    btoa(accessToken + process.env.REACT_APP_SECURE_CODE),
  );

  if (refreshToken) {
    sessionStorage.setItem(
      btoa('refresh' + process.env.REACT_APP_SECURE_CODE),
      btoa(refreshToken + process.env.REACT_APP_SECURE_CODE),
    );
  }
}
