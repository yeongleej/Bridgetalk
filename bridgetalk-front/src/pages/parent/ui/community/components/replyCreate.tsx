export function ReplyCreate() {
  return (
    <div className="replyCreate">
      <textarea name="reply" id="reply" cols={30} rows={20} placeholder="여기에 답글 작성"></textarea>
      <button>답글 등록하기</button>
    </div>
  );
}
