export function WarningPage() {
  return (
    <div className="warningPage">
      <div className="warningPage__alert">
        <div className="warningPage__alert-message">
          <p>
            잠깐! 대화 도중에 나가면 대화 기록이 저장되지 않아!
            <br />
            그래도 대화를 마무리하지 않고 그냥 나갈래?
          </p>
        </div>
        <div className="warningPage__alert-dino">

        </div>
      </div>
      <div className="warningPage__select">
        <div className="warningPage__select-btn">그냥 나가기</div>
        <div className="warningPage__select-btn">대화 저장하고 나가기</div>
        <div className="warningPage__select-btn">계속 대화하기</div>
      </div>
    </div>
  );
}
