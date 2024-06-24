import { useReportStore } from '@/pages/parent/store';
import React, { useMemo } from 'react';

export const Input = React.forwardRef((props: any, ref: any) => {
  const language = useReportStore((state) => state.language);

  const placeholder = useMemo(
    () => ({
      kor: '검색어를 입력해주세요',
      viet: 'Paki-type ang keyword',
      ph: '',
    }),
    [],
  );

  return (
    <div className="boardPage__top-search">
      <form className="boardPage__top-search-form">
        <input
          className="boardPage__top-search-input"
          type="text"
          placeholder={placeholder[language]}
          ref={ref}
        ></input>
        <button
          className="boardPage__top-search-button"
          onClick={(e) => {
            e.preventDefault();
            props.fetchData();
          }}
        >
          <img src={`/assets/img/parent/community/search_solid.svg`} />
        </button>
      </form>
    </div>
  );
});
