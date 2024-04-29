import { useNavigate } from 'react-router-dom';

interface Props {
    newsId: number;
    newsTitle: string;
    newsDescription: string;
}

export function ParentInformationNewsListItem({ newsId, newsTitle, newsDescription }: Props) {
    const navigate = useNavigate();

    return (
        <button onClick={() => navigate(`${newsId}`)}>
            <div>{newsTitle}</div>
            <div>{newsDescription}</div>
        </button>
    );
}
