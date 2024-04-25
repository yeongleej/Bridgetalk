import * as S from '@/styles/parent/parentReportDetailRecorder.style';
import { memo } from 'react';

export const ParentReportDetailRecorder = memo(() => {
    return (
        <S.Container>
            <div>음량표시</div>
            <div>음량표시공룡</div>
            <div>녹음시간</div>
            <button>녹음버튼</button>
        </S.Container>
    );
});
