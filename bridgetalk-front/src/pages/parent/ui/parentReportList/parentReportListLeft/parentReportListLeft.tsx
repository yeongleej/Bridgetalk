import * as S from '@/styles/parent/parentInformationListLeft.style';
import { ParentReportListGraph } from '../parentReportListGraph/parentReportListGraph';
import { ParentReportListWordcloud } from '../parentReportListWordcloud/parentReportListWordcloud';

export function ParentReportListLeft() {
    return (
        <S.Container>
            <ParentReportListWordcloud />
            <ParentReportListGraph />
        </S.Container>
    );
}
