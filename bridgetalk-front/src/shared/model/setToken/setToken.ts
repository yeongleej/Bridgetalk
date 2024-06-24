export function setToken(accessToken: string, refreshToken?: string) {
  localStorage.setItem(
    btoa('access' + process.env.REACT_APP_SECURE_CODE),
    btoa(accessToken + process.env.REACT_APP_SECURE_CODE),
  );

  if (refreshToken) {
    localStorage.setItem(
      btoa('refresh' + process.env.REACT_APP_SECURE_CODE),
      btoa(refreshToken + process.env.REACT_APP_SECURE_CODE),
    );
  }
}
