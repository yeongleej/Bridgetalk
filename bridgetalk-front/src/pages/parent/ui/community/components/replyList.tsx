import { useBoardStore, useReportStore } from '@/pages/parent/store';
import { ReplyListItem } from './items/replyListItem';
import { useEffect, useState } from 'react';
import { getCommentList } from '@/pages/parent/query';

export function ReplyList({ boardId }: any) {
  if (!boardId) return;

  const language = useReportStore((state) => state.language);
  const refresh = useBoardStore((state) => state.refresh);

  const [replyList, setReplyList] = useState<[] | null>(null);

  useEffect(() => {
    async function fetchData(language: any, boardId: number) {
      if (!boardId) return;

      try {
        const data = await getCommentList(language, boardId);

        if (data.status === 200) {
          // console.log(data.data.commentsList);
          setReplyList(data.data.commentsList);
        }
      } catch (err) {
        // console.log(err);
      }
    }
    fetchData(language, boardId);
  }, [language, refresh]);

  return (
    <div className="replyLIst" style={{ display: 'flex', flexDirection: 'column', gap: '2svh' }}>
      {replyList && replyList.map((reply: any, idx) => <ReplyListItem key={idx} reply={reply} />)}
    </div>
  );
}
