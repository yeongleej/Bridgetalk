import { deleteCommentLike, getCommentLikeCheck, postCommentLike } from '@/pages/parent/query';
import { dateToString } from '@/shared';
import { useEffect, useState } from 'react';

interface Reply {
  commentsId: number;
  parentsNickname: string;
  commentsContent: string;
  likes: number;
  createdAt: string;
}

interface Props {
  reply: Reply;
}

export function ReplyListItem({ reply }: Props) {
  const [likes, setLikes] = useState(reply.likes);
  const [isLike, setIsLike] = useState(false);

  useEffect(() => {
    async function fetchData() {
      try {
        const data = await getCommentLikeCheck(reply.commentsId);

        setIsLike(data.data);
      } catch (err) {
        // console.log(err);
      }
    }
    fetchData();
  }, []);

  return (
    <div className="replyListItem">
      <div className="replyListItem__left">
        <div className="replyListItem__left-like">
          <button
            style={{ backgroundColor: isLike ? 'black' : 'white' }}
            className="replyListItem__left-like-btn"
            onClick={() => {
              if (isLike) {
                deleteCommentLike(reply.commentsId)
                  .then(() => {
                    setIsLike(false);
                    setLikes((cnt) => cnt - 1);
                  })
                  .catch((err) => {
                    setIsLike(true);
                  });
              } else {
                postCommentLike(reply.commentsId)
                  .then(() => {
                    setIsLike(true);
                    setLikes((cnt) => cnt + 1);
                  })
                  .catch((err) => {
                    setIsLike(false);
                  });
              }
            }}
          >
            <img src={`/assets/img/parent/community/favor_${isLike ? 'empty' : 'solid'}.svg`} />
          </button>
        </div>
        <div className="replyListItem__left-cnt">{likes}</div>
      </div>
      <div className="replyListItem__right">
        <div className="replyListItem__right-top">
          <div className="replyListItem__right-top-writer">{reply.parentsNickname}</div>
          <div className="replyListItem__right-top-date">{dateToString(reply.createdAt)}</div>
        </div>
        <div className="replyListItem__right-bottom">{reply.commentsContent}</div>
      </div>
    </div>
  );
}
