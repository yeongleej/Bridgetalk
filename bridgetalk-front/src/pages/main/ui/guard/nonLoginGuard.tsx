import { decodeToken } from '@/shared';
import { ReactNode } from 'react';
import { useNavigate } from 'react-router-dom';

interface Props {
  child: ReactNode;
}

export function NonLoginGuard({ child }: Props) {
  const navigate = useNavigate();

  if (decodeToken('access') === null) {
    navigate('/');
  }

  return { child };
}
