import { useBoardStore, useReportStore } from '@/pages/parent/store';
import { useMemo } from 'react';

export function SearchTypes() {
  const boardStore = useBoardStore();
  const language = useReportStore((state) => state.language);

  const categories = useMemo(
    () => ({
      kor: ['제목', '작성자', '내용'],
      viet: ['Tiêu đề', 'Tác giả', 'Nội dung'],
      ph: ['Pamagat', 'May-akda', 'Nilalaman'],
    }),
    [],
  );

  function activeCategory(category: string, searchType: string) {
    const map: any = {
      제목: '제목',
      작성자: '작성자',
      내용과레포트요약: '내용과레포트요약',
    };

    return map[category] === searchType ? 'active' : '';
  }

  return (
    <div className="boardPage__top-categories">
      <button
        className={`boardPage__top-categories-title ${activeCategory(boardStore.searchType, '제목')}`}
        onClick={() => {
          boardStore.setSearchType('제목');
        }}
      >
        {categories[language][0]}
      </button>
      <button
        className={`boardPage__top-categories-writer ${activeCategory(boardStore.searchType, '작성자')} `}
        onClick={() => {
          boardStore.setSearchType('작성자');
        }}
      >
        {categories[language][1]}
      </button>
      <button
        className={`boardPage__top-categories-content ${activeCategory(boardStore.searchType, '내용과레포트요약')}`}
        onClick={() => {
          boardStore.setSearchType('내용과레포트요약');
        }}
      >
        {categories[language][2]}
      </button>
    </div>
  );
}
