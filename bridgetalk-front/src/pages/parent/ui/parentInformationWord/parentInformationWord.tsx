import { ParentBackButton } from '@/shared';
import * as S from '@/styles/parent/common.style';
import { useNavigate } from 'react-router-dom';

export function ParentInformationWord() {
    const navigate = useNavigate();

    return (
        <div>
            <ParentBackButton path="../information" navigate={navigate} />
            ParentInformationWord
        </div>
    );
}
