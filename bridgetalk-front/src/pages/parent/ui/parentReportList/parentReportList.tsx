import * as S from '@/styles/parent/parentReportList.style';
import { ParentReportListLeft } from '@/pages/parent/ui/parentReportList/parentReportListLeft/parentReportListLeft';
import { ParentReportListRight } from '@/pages/parent/ui/parentReportList/parentReportListRight/parentReportListRight';
import { BackButton, customAxios } from '@/shared';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { getReportList } from '../../query';
import { useReportStore } from '../../store';

export function ParentReportList() {
  const navigate = useNavigate();

  const setReportList = useReportStore((state) => state.setReportList);
  const setReports_UUID = useReportStore((state) => state.setReports_UUID);
  const language = useReportStore((state) => state.language);
  const childrenList = useReportStore((state) => state.childrenList);

  useEffect(() => {
    async function fetchData() {
      // childMap: {UUID: {name, nickname}}
      const childMap = new Map();

      // {reportsId: UUID}
      const reports_UUID = new Map();

      // promises: 여러개의 비동기 호출에 대한 결과를 저장하는 배열
      const promises = childrenList.map((child: any) => {
        childMap.set(child.userId, { name: child.userName, nickname: child.userNickname, dino: child.userDino });
        return getReportList(child.userId, language);
      });

      // data: promises의 비동기 호출이 모두 종료되었을 때 resolve된 응답을 저장하는 배열
      const data = await Promise.allSettled(promises);
      // console.log(data);

      data.forEach((it: any) => {
        if (!it.value) return;

        const childUUID = it.value.request.responseURL.split('/')[5];

        // child = {name, nickname}
        const child = childMap.get(childUUID);

        it.UUID = childUUID;
        it.name = child.name;
        it.nickname = child.nickname;
        it.dino = child.dino;

        it.value.data.forEach((report: any) => {
          reports_UUID.set(report.reportsId, it.UUID);
        });
      });

      setReports_UUID(reports_UUID);
      setReportList(data);
    }

    fetchData();
  });

  return (
    <>
      <S.Container>
        <S.ContentContainer>
          <ParentReportListRight />
          <ParentReportListLeft />
        </S.ContentContainer>
      </S.Container>
    </>
  );
}
