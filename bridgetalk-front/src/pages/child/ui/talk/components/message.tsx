import * as S from '@/styles/child/talk/messagePage.style';

export function Message() {
  return (
    <S.Container>
      <div className="message">
        <div className="message__content">message</div>
        <div className="message__reader">
          <div className="message__reader-talk"></div>
          <div className="message__reader-dino">
            <img src={'/assets/img/pic/blue.svg'} />
          </div>
        </div>
      </div>
    </S.Container>
  );
}
