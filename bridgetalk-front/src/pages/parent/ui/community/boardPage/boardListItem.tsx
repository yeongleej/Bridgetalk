import { dateToString } from '@/shared';
import { useNavigate } from 'react-router-dom';

interface Props {
  board: Board;
}

interface Board {
  boardId: number;
  boardsTitle: string;
  boardsContent: string;
  likes: number;
  createdAt: string;
  reportsSummary: string;
  reportsKeywords: string[];
  parentsNickname: string;
}

export function BoardListItem({ board }: Props) {
  const navigate = useNavigate();

  return (
    <div
      className="boardPage__list-item"
      onClick={() => {
        navigate(`${board.boardId}`);
      }}
    >
      <div className="boardPage__list-item-header">
        <div className="boardPage__list-item-header-first">
          <div className="boardPage__list-item-header-first-title">{board.boardsTitle}</div>
          <div className="boardPage__list-item-header-first-likes">
            <img src={`/assets/img/parent/community/like_empty.svg`} />
            {board.likes}
          </div>
        </div>
      </div>
      <div className="boardPage__list-item-body">
        <div className="boardPage__list-item-body-content">{board.boardsContent.split('</br>').join(' ')}</div>
      </div>
      <div className="boardPage__list-item-footer">
        <div className="boardPage__list-item-footer-keywords">
          {board.reportsKeywords.map((keyword, idx) => (
            <div key={idx + 1}># {keyword}</div>
          ))}
        </div>
        <div className="boardPage__list-item-footer-info">
          <div className="boardPage__list-item-footer-info-writer">{board.parentsNickname}</div>
          <div className="line">{`|`}</div>
          <div className="boardPage__list-item-footer-info-date">{dateToString(board.createdAt)}</div>
        </div>
      </div>
    </div>
  );
}
