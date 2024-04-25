import { useNavigate } from 'react-router-dom';

export function ParentInformationMain() {
    const navigate = useNavigate();

    return (
        <div>
            ParentInformationMain
            <button onClick={() => navigate('news')}>News</button>
            <button onClick={() => navigate('word')}>
                tiếng lóng
                <br />
                sự viết tắt
            </button>
        </div>
    );
}
