import { useNavigate } from 'react-router-dom';

export function GamingPage() {
  const navigate = useNavigate();

  return (
    <>
      <button
        onClick={() => {
          navigate('/puzzle');
        }}
      >
        puzzle
      </button>
      <button>dress</button>
    </>
  );
}
