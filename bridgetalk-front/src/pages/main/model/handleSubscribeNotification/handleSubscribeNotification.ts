import { decodeToken } from '@/shared';
import { EventSourcePolyfill } from 'event-source-polyfill';

export function handleSubscribeNotification() {
  return new EventSourcePolyfill(`${process.env.REACT_APP_API_URL}/sse/subscribe`, {
    headers: { Authorization: `Bearer ${decodeToken('access')}` },
  });
}
