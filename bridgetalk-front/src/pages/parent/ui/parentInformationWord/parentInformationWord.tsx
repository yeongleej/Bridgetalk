import { BackButton } from '@/shared';
import * as S from '@/styles/parent/parentInformationWord.style';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ParentInformationWordListItem } from './parentInformationWordListItem/parentInformationWordListItem';
import { getSlang } from '../../query';

interface Word {
  meaning: string;
  originalWord: string;
  slangId: number;
  slangWord: string;
  vietnamesePronunciation: string;
  vietnameseTranslation: string;
}

export function ParentInformationWord() {
  const navigate = useNavigate();
  const [words, setWords] = useState<Word[]>([]);
  const [page, setPage] = useState<number>(0);
  const [lastPage, setLastPage] = useState<number>(0);
  const [wordDetail, setWordDetail] = useState<Word | null>(null);

  useEffect(() => {
    getSlang(page, 9).then((res) => {
      if (res.status === 200) {
        setWords(res.data.list);
        setLastPage(res.data.endPage);
      }
    });
  }, [page]);

  const startIndex = Math.floor(page / 5) * 5;
  const endIndex = Math.min(startIndex + 5, lastPage);

  return (
    <S.Container>
      <div className="main">
        {words.length > 0 ? words.map((word, idx) => <WordItem key={idx + 1} word={word} />) : null}
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
        {words.length > 0 &&
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

function WordItem({ word }: any) {
  const [clicked, setClicked] = useState(false);

  return (
    <div
      className="main__worditem"
      onClick={() => {
        setClicked((clicked) => !clicked);
      }}
    >
      <div className="main__worditem-top">
        <div className="main__worditem-top-title">{word.slangWord}</div>
        <div>{word.vietnamesePronunciation}</div>
        <div>{word.originalWord}</div>
      </div>
      <div className="main__worditem-bottom">{!clicked ? word.meaning : word.vietnameseTranslation}</div>
    </div>
  );
}
