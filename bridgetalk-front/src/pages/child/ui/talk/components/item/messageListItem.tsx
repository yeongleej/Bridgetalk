import { useNavigate } from 'react-router-dom';
import * as S from '@/styles/child/talk/messageListItem.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';

interface MessageListItemProps {
  id: string;
  date: string;
}

export function MessageListItem({ id, date }: MessageListItemProps) {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate(`/message/${id}`); // ID를 경로에 포함하여 이동
  };

  const dateObj = new Date(date);

  const formattedDate = new Date(date).toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });

  const formattedTime = dateObj.toLocaleTimeString('ko-KR', {
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: false,
  });

  return (
    <S.Container>
      <div className="messageListItem" onClick={handleClick}>
        <div className="messageListItem__icon">
          <FontAwesomeIcon icon={faEnvelope} />
        </div>
        <div className="messageListItem__date">{formattedDate}의 편지</div>
        <div className="messageListItem__time">{formattedTime}</div>
      </div>
    </S.Container>
  );
}
