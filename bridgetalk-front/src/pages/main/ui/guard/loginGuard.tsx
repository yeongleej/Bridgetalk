import { decodeToken } from '@/shared';
import { ReactNode, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

interface Props {
  children: ReactNode;
}

/**
 * 로그인시 첫 화면을 건너뛰게 하는 가드
 * @param param0
 */
export function LoginGuard({ children }: Props) {
  const navigate = useNavigate();
  const { pathname } = useLocation();

  const loginLimitPath = ['/', '/start', '/signin', '/signup'];

  useEffect(() => {
    if (decodeToken('access') === null) {
      if (loginLimitPath.some((it) => it === pathname)) {
        setTimeout(() => {
          navigate(pathname);
        });
      } else {
        setTimeout(() => {
          navigate('/start');
        }, 0);
      }
    } else {
      if (loginLimitPath.some((it) => it === pathname)) {
        setTimeout(() => {
          navigate('/profile');
        }, 0);
      } else {
        setTimeout(() => {
          navigate(pathname);
        }, 0);
      }
    }
  }, [pathname]);

  return <>{children}</>;
}
