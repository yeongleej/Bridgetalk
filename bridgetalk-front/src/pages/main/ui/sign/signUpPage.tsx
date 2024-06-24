import { useState } from 'react';
import * as S from '@/styles/main/signup.style';
import { InputEmail } from './components/inputEmail';
import { InputName } from './components/inputName';
import { SelectCountry } from './components/selectCountry';
import { useNavigate } from 'react-router-dom';
import { HomeButton } from '@/shared';

export function SignUpPage() {
  const navigate = useNavigate();

  const [page, setPage] = useState<number>(0);

  return (
    <S.Container>
      <HomeButton navigate={navigate} />
      {page === 0 && <InputEmail setPage={setPage} />}
      {page === 1 && <InputName setPage={setPage} />}
      {page === 2 && <SelectCountry setPage={setPage} />}
    </S.Container>
  );
}
