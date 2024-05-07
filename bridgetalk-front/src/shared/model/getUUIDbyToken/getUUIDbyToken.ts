import { decodeToken } from '../decodeToken/decodeToken';

/**
 *
 * @returns uuid | null
 */
export function getUUIDbyToken() {
  const token = decodeToken('access');

  if (token) {
    return JSON.parse(atob(token.split('.')[1])).uuid;
  } else {
    return null;
  }
}
