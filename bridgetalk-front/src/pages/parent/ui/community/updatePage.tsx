export function UpdatePage() {
  return (
    <div className="updatePage">
      <div className="updatePage__header">
        <div className="updatePage__header-toBack">목록으로 돌아가기</div>
      </div>
      <div className="updatePage__container">
        <div className="updatePage__container-title">
          <input type="text" required />
          {/* 원래 제목 */}
        </div>
        <div className="updatePage__container-content">
          <textarea name="article" id="article" cols={40} rows={30}>
            (원래 내용)
          </textarea>
        </div>
        <div className="updatePage__container-btns">
          <button>수정 취소</button>
          <button>수정 완료</button>
        </div>
      </div>
    </div>
  );
}
