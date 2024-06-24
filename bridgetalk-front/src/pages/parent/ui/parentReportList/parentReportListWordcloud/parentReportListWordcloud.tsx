import { useReportStore } from '@/pages/parent/store';
import * as S from '@/styles/parent/parentReportListWordcloud.style';
import { useEffect, useMemo, useState } from 'react';
import ReactWordcloud from 'react-wordcloud';

export function ParentReportListWordcloud() {
  const { reportList, language } = useReportStore((state) => ({
    reportList: state.reportList,
    language: state.language,
  }));
  const [keywords, setKeywords] = useState<any>([]);
  const keywordTitle = useMemo(
    () => ({
      kor: '키워드 분석',
      viet: 'Phân tích từ khoá',
      ph: '',
    }),
    [],
  );

  useEffect(() => {
    const keywordsMap = new Map();

    reportList.forEach((report: any) => {
      if (!report.value) return;

      const list = report.value.data;

      list.forEach((it: any) => {
        it.reportsKeywords.forEach((keyword: any) => {
          if (keywordsMap.has(keyword)) {
            keywordsMap.set(keyword, keywordsMap.get(keyword) + 1);
          } else {
            keywordsMap.set(keyword, 1);
          }
        });
      });
    });

    const newKeywords = [];
    // for (let i = 0; i < 3; i++) {
    //   newKeywords.push({
    //     text: `r${i}`,
    //     value: i * i,
    //   });
    // }

    for (let i of keywordsMap.keys()) {
      newKeywords.push({
        text: i,
        value: keywordsMap.get(i),
      });
    }

    setKeywords(newKeywords);
  }, [reportList]);

  const wordCloud =
    keywords.length > 0 ? (
      <ReactWordcloud
        words={keywords}
        options={{
          fontFamily: 'DNF',
          rotations: 2, // 단어 회전을 두 가지 방향으로 제한
          rotationAngles: [0, 90],
        }}
      />
    ) : (
      <div>
        <img src={'/assets/img/parent/empty.svg'} />
      </div>
    );

  return (
    <S.Wrapper>
      <div className="title" style={{ fontFamily: language === 'kor' ? 'DNF' : 'Pretendard' }}>
        {keywordTitle[language]}
      </div>
      <div className="cloud">{wordCloud}</div>
    </S.Wrapper>
  );
}
