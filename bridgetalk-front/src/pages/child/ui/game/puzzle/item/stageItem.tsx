import { useNavigate } from 'react-router-dom';

interface StageItemProps {
  id: string;
  img: string;
  name: string;
}

export function StageItem({ id, img, name }: StageItemProps) {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate(`/puzzle/${id}`);
  };

  return (
    <div className="stageItem" onClick={handleClick}>
      <span>{name}</span>
      <img src={img} alt={name} />
    </div>
  );
}
