import React, { useEffect, useState } from 'react';
import { customAxios } from '@/shared';
import { MessageListItem } from './item/messageListItem';
import * as S from '@/styles/child/talk/messageList.style';

interface Letter {
  lettersId: number;
  lettersOriginalContent: string; 
  lettersTranslationContent: string; 
  lettersRegDate: string;
}

export function MessageList() {
  const [letters, setLetters] = useState<Letter[]>([]);
  // 변수, set변수 
  useEffect(() => { // 실행시 사용하는 함수
    const fetchLetters = async () => {
      try {
        const response = await customAxios.get('/letters');
        setLetters(response.data);
      } catch (error) {
        console.error('Failed to fetch letters:', error);
      }
    };

    fetchLetters();
  }, []);

  return ( // 실행시 나오는 화면
    <S.Container> 
      <div className="messageList">
        <div className="messageList__list">
          <div className="messageList__list-header">
            <img src={'/assets/img/pic/envelop.svg'} />
            <img src={'/assets/img/pic/mailbox.svg'} />
          </div>
          <div className="messageList__list-content">
            {letters.map((letter) => (
              <MessageListItem key={letter.lettersId} id={letter.lettersId.toString()} date={letter.lettersRegDate} />
            ))}
          </div>
        </div>
        <div className="messageList__info">
          <span>
            익명의 다이노 친구가
            <br />
            보낸 편지들이야!
          </span>
          <img src={'/assets/img/pic/pink.svg'} />
        </div>
      </div>
    </S.Container>
  );
}
