import * as S from '@/styles/parent/parentInformationNurturDetail.style';
import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { handleNurtureInfoDetail } from '../../model';
import { useReportStore } from '../../store';
import { BackButton } from '@/shared';

export function ParentInformationNurtureDetail() {
  const { nurtureId } = useParams();
  const [infoDetail, setInfoDetail] = useState<any>();

  const language = useReportStore((state) => state.language);

  const navigate = useNavigate();

  useEffect(() => {
    handleNurtureInfoDetail(Number(nurtureId), language, setInfoDetail);

    // console.log(infoDetail && infoDetail.content.split('■').join('\n'));
  }, [language]);

  return (
    <>
      <BackButton path="../information/nurture" navigate={navigate} />
      <S.Container>
        {infoDetail && (
          <div className="main">
            <div className="main__title">{infoDetail.title}</div>
            <div className="main__content">
              {infoDetail.content.split('■').map((it: any, idx: number) =>
                it.trim() ? (
                  <div key={idx + 1}>
                    {`${it.trim()}`}
                    <br />
                  </div>
                ) : null,
              )}
              <div>
                {`출처 | `}
                <a href={`${infoDetail.link}`} target="_blank">
                  {infoDetail.link}
                </a>
              </div>
            </div>
          </div>
        )}
      </S.Container>
    </>
  );
}
