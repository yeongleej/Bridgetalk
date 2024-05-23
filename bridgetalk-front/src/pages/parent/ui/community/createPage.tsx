import * as S from '@/styles/parent/createPage.style';
import { useEffect, useMemo, useRef, useState } from 'react';
import { useReportStore } from '../../store';
import { getReportList, postBoardCreate } from '../../query';
import { errorCatch } from '@/shared';
import { useErrorStore } from '@/shared/store';
import { useNavigate } from 'react-router-dom';

export function CreatePage() {
  const navigate = useNavigate();

  const language = useReportStore((state) => state.language);
  const setErrorModalState = useErrorStore((state) => state.setErrorModalState);
  const reportList = useReportStore((state) => state.reportList);
  const setReportList = useReportStore((state) => state.setReportList);
  const setReports_UUID = useReportStore((state) => state.setReports_UUID);
  const childrenList = useReportStore((state) => state.childrenList);

  const titleRef = useRef<HTMLInputElement>(null);
  const contentRef = useRef<HTMLTextAreaElement>(null);

  const [reportsId, setReportsId] = useState<number>(0);

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
  }, [language]);

  async function handleBoardCreate(reportsId: number, boardsTitle: string, boardsContent: string, language: any) {
    const DTO = {
      reportsId,
      boardsTitle,
      boardsContent,
      language,
    };

    try {
      return await postBoardCreate(DTO);
    } catch (err) {
      if (err instanceof Error) {
        return errorCatch(err, setErrorModalState);
      }
    }
  }

  const placeholderTitle = useMemo(
    () => ({
      kor: '제목을 입력해주세요',
      viet: 'Vui lòng nhập tiêu đề',
      ph: 'Paki-type ang keyword',
    }),
    [],
  );

  const placeholderReport = useMemo(
    () => ({
      kor: '리포트를 선택해주세요',
      viet: 'Vui lòng chọn báo cáo',
      ph: 'Paki-type ang keyword',
    }),
    [],
  );

  const placeholderContent = useMemo(
    () => ({
      kor: '내용을 입력해주세요',
      viet: 'Vui lòng nhập nội dung',
      ph: 'Paki-type ang keyword',
    }),
    [],
  );

  const placeholderComplete = useMemo(
    () => ({
      kor: '등록하기', // 한국어: "등록하기"
      viet: 'Đăng ký', // 베트남어: "Đăng ký"
      ph: 'Irehistro', // 필리핀어: "Irehistro"
    }),
    [],
  );

  const maintitle = useMemo(
    () => ({
      kor: '글 작성하기', // 한국어: "글 작성하기"
      viet: 'Viết bài', // 베트남어: "Viết bài"
      ph: 'Sumulat ng post', // 필리핀어: "Sumulat ng post"
    }),
    [],
  );

  return (
    <S.Container>
      <div className="createPage">
        <div className="createPage__header">
          <button
            className="createPage__header-toBack"
            onClick={() => {
              navigate('../board');
            }}
          >
            <img src={'/assets/img/parent/community/back.svg'} />
          </button>
          <div className="createPage__header-main" style={{ fontFamily: language === 'kor' ? 'DNF' : 'Pretendard' }}>
            {maintitle[language]}
          </div>
          <button
            className="createPage__header-btn"
            onClick={() => {
              handleBoardCreate(
                reportsId,
                titleRef.current!.value,
                contentRef.current!.value.split('\n').join('</br>'),
                language,
              ).then((res) => {
                // console.log(res);
                if (!res) return;

                setErrorModalState('게시글이 성공적으로 등록됐습니다.');

                setTimeout(() => {
                  navigate('../board');
                }, 500);
              });
            }}
          >
            {placeholderComplete[language]}
          </button>
        </div>
        <hr />
        <div className="scroll">
          <div className="createPage__container">
            <div className="createPage__container-title">
              <input type="text" placeholder={placeholderTitle[language]} required ref={titleRef} />
            </div>
            <div className="createPage__container-report">
              <div className="createPage__container-report-title">{`S E L E C T   R E P O R T`}</div>
              <div className="createPage__container-report-content">
                {reportList &&
                  reportList.map((report: any) => {
                    const reports = report.value.data;

                    return reports.map((it: any, idx: number) => {
                      const reportId = it.reportsId;
                      const repoortsSummary = it.reportsSummary;

                      return (
                        <button
                          key={idx}
                          style={{
                            backgroundColor: reportId === reportsId ? 'rgba(108, 149, 255)' : 'rgba(255, 255, 255)',
                            color: reportId === reportsId ? 'white' : 'black',
                          }}
                          className="createPage__container-report-content-btn"
                          onClick={() => {
                            setReportsId(reportId);
                          }}
                        >
                          <p>
                            <img
                              src={`/assets/img/parent/community/list_${
                                reportId === reportsId ? 'empty' : 'solid'
                              }.svg`}
                            />

                            {repoortsSummary}
                          </p>
                        </button>
                      );
                    });
                  })}
              </div>
            </div>
            <div className="createPage__container-content">
              <textarea
                name="article"
                id="article"
                placeholder={placeholderContent[language]}
                ref={contentRef}
              ></textarea>
            </div>
          </div>
        </div>
      </div>
    </S.Container>
  );
}
