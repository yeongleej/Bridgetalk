export function messageList() {
  return (
    <div className="messageList">
      <div className="messageList__header">
        <span>받은 편지함</span>
      </div>
      <div className="messageList__container">
        <div className="messageList__container-list"></div>
        <div className="messageList__container-scrollbar"></div>
      </div>
    </div>
  );
}
