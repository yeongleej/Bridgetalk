export const setCookie = (name: string, value: string) => {
  document.cookie = name + '=' + (value || '') + '; path=/';
};

export const getCookie = (name: 'memberId' | 'memberToken' | 'memberNickname') => {
  const nameEQ = name + '=';
  const ca = document.cookie.split(';');

  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];

    while (c.charAt(0) === ' ') c = c.substring(1, c.length);

    if (c.indexOf(nameEQ) === 0) {
      if (name === 'memberId') return parseInt(c.substring(nameEQ.length, c.length), 10);
      return c.substring(nameEQ.length, c.length);
    }
  }

  return null;
};

export const deleteCookie = (name: string) => {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/';
};
