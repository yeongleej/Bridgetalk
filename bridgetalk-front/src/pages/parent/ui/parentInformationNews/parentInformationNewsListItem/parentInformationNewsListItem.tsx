interface Props {
    newsId: number;
    newsTitle: string;
    newsDescription: string;
}

export function ParentInformationNewsListItem({ newsId, newsTitle, newsDescription }: Props) {
    return (
        <div>
            <div>{newsTitle}</div>
            <div>{newsDescription}</div>
        </div>
    );
}
