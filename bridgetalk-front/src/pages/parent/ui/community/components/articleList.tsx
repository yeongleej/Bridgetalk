import { useEffect, useMemo, useState } from 'react';
import { ArticleListItem } from './items/articleListItem';
import { customAxios } from '@/shared';
import { useNavigate } from 'react-router-dom';
import { useBoardStore } from '@/pages/parent/store/useBoardStore/useBoardStore';

interface Board{
  boardId?: number;
  boardsTitle?: string;
  boardsContent?: string;
  likes?: number;
  createdAt?: string;
  reportsSummary?: string;
  reportsKeyword?: string[];
  writer?: string;
};

export function ArticleList() {

  const navigate = useNavigate();
  const [boardList, setBoardList] = useState<Board[]>([]);
  const [page, setPage] = useState<number>(0);
  const [lastPage, setLastPage] = useState<number>(0);
  const [searchSort, setSearcSort] = useState<any>("최신순");
  const [searchType , setsearchType ] = useState<any>('prospective');
  const [searchWord  , setsearchWord  ] = useState<any>("");  // 이거 다른 방법


  const language = useBoardStore((state) => state.language);

  // const sort : Sort = useMemo(
  //   () => ({
  //     "최신순": 0,
  //     "좋아요순": 1,
  //   }),
  //   [],
  // );
  
  // const type : Type = useMemo(
  //   () => ({

  //   })
  // )


  // 변수, set변수 
  useEffect(() => { // 실행시 사용하는 함수
    const fetchArticles = async () => {
      try {
        const response = await customAxios.get('/board');
        setBoardList(response.data);
      } catch (error) {
        console.error('게시글을 불러오지못했습니다.', error);
      }
    };

    fetchArticles();
  }, []);



  
  return (
    <div className="articleList">
      <ArticleListItem />
    </div>
  );
}
