import { NavigateFunction, useNavigate } from 'react-router-dom';

interface Props {
    path: string;
    navigate: NavigateFunction;
}

export function ParentBackButton({ path, navigate }: Props) {
    return <button onClick={() => navigate(path)}>뒤로가기</button>;
}
