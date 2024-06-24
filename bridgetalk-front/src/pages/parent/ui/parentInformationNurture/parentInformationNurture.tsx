import * as S from '@/styles/parent/parentInformationNews.style';

import { useNavigate } from 'react-router-dom';
import { useEffect, useMemo, useState } from 'react';
import { BackButton } from '@/shared';
import { handleNurtureInfoList } from '../../model';
import { useReportStore } from '../../store';

interface Info {
  parentingInfoId?: number;
  title?: string;
  content?: string;
  link?: string;
  category?: string;
}

interface Category {
  [key: string]: number;
}

export function ParentInformationNurture() {
  const navigate = useNavigate();
  const [infoList, setInfoList] = useState<Info[]>([]);
  const [page, setPage] = useState<number>(0);
  const [lastPage, setLastPage] = useState<number>(0);
  const [searchCategory, setSearchCategory] = useState<any>('prospective');

  const language = useReportStore((state) => state.language);

  const category: Category = useMemo(
    () => ({
      prospective: 0,
      infant_and_toddler: 1,
      school: 2,
      puberty: 3,
    }),
    [],
  );

  const categories = useMemo(
    () => ({
      kor: ['예비', '영유아기', '학령기', '사춘기'],
      viet: ['Chuẩn bị', 'Thời kỳ ấu thơ', 'Thời kỳ đến trường', 'Thời kỳ dậy thì'],
      ph: ['Paghahanda', 'Sanggol na Panahon', 'Edad ng Paaralan', 'Pagdadalaga/Pagbibinata'],
    }),
    [],
  );

  const header = useMemo(
    () => ({
      kor: ['번호', '제목', ''],
      viet: ['Số', 'Tiêu đề', ''],
      ph: ['Numero', 'Pamagat', ''],
    }),
    [],
  );

  const title = useMemo(
    () => ({
      kor: '육아 정보',
      viet: 'thông tin nuôi dạy con cái',
      ph: 'Impormasyon sa Pag-aalaga ng Bata',
    }),
    [],
  );

  useEffect(() => {
    handleNurtureInfoList(language, setInfoList, page, setLastPage, searchCategory);
  }, [page, language]);

  useEffect(() => {
    setPage(0);
    handleNurtureInfoList(language, setInfoList, 0, setLastPage, searchCategory);
  }, [searchCategory]);

  const startIndex = Math.floor(page / 5) * 5;
  const endIndex = Math.min(startIndex + 5, lastPage);

  return (
    <S.Container>
      <div
        className="title"
        style={{
          fontFamily: language === 'kor' ? 'DNF' : 'Pretendard-Black',
        }}
      >
        {title[language]}
      </div>
      <div className="categories">
        {['prospective', 'infant_and_toddler', 'school', 'puberty'].map((it, idx) => (
          <button
            key={idx + 1}
            className={`${searchCategory === it ? 'active' : ''}`}
            style={{
              fontFamily: language === 'kor' ? 'DNF' : 'Pretendard-Black',
              fontSize: language === 'kor' ? '2svw' : '1.3svw',
            }}
            onClick={() => {
              setSearchCategory(it);
            }}
          >
            {categories[language][idx]}
          </button>
        ))}
      </div>
      <div className="main">
        <table>
          <thead className="thead">
            <tr className="main__header">
              {header[language].map((it, idx) => (
                <td key={idx + 1}>{it}</td>
              ))}
            </tr>
          </thead>
          <tbody className="tbody">
            {infoList.length > 0 &&
              infoList.map((it) => (
                <tr
                  className="main__item"
                  key={it.parentingInfoId}
                  onClick={() => {
                    navigate(`${it.parentingInfoId!}`);
                  }}
                >
                  <td className="main__item-num">{it.parentingInfoId}</td>
                  <td className="main__item-title">{it.title}</td>
                  <td className="main__item-category">{categories[language][category[it.category!]]}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
      <div className="pagenation">
        <button
          onClick={() => {
            setPage(0);
          }}
        >{`<<`}</button>
        <button
          onClick={() => {
            setPage((page) => {
              if (page > 0) {
                return page - 1;
              }
              return page;
            });
          }}
        >{`<`}</button>
        {infoList.length > 0 &&
          Array(lastPage)
            .fill(0)
            .slice(startIndex, endIndex)
            .map((_, idx) => (
              <button
                key={idx + 1}
                className={`${page === startIndex + idx ? 'active' : ''}`}
                onClick={() => {
                  setPage(Math.floor(startIndex + idx));
                }}
              >
                {startIndex + idx + 1}
              </button>
            ))}
        <button
          onClick={() => {
            setPage((page) => {
              if (page < lastPage - 1) {
                return page + 1;
              }
              return page;
            });
          }}
        >{`>`}</button>
        <button
          onClick={() => {
            setPage(lastPage - 1);
          }}
        >{`>>`}</button>
      </div>
    </S.Container>
  );
}
