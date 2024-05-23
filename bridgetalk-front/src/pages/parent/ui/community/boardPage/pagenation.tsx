export function Pagenation({ page, setPage, list, lastPage }: any) {
  const startIndex = Math.floor(page / 5) * 5;
  const endIndex = Math.min(startIndex + 5, lastPage);

  if (!list) return;

  return (
    <div className="pagenation">
      <button
        onClick={() => {
          setPage(0);
        }}
      >{`<<`}</button>
      <button
        onClick={() => {
          setPage((page: number) => {
            if (page > 0) {
              return page - 1;
            }
            return page;
          });
        }}
      >{`<`}</button>
      {list.length > 0 &&
        Array(lastPage)
          .fill(0)
          .slice(startIndex, endIndex)
          .map((_, idx) => (
            <button
              key={idx + 1}
              className={`${page === startIndex + idx ? 'active' : ''}`}
              onClick={() => {
                setPage(Math.floor(startIndex + idx));
              }}
            >
              {startIndex + idx + 1}
            </button>
          ))}
      <button
        onClick={() => {
          setPage((page: number) => {
            if (page < lastPage - 1) {
              return page + 1;
            }
            return page;
          });
        }}
      >{`>`}</button>
      <button
        onClick={() => {
          setPage(lastPage - 1);
        }}
      >{`>>`}</button>
    </div>
  );
}
