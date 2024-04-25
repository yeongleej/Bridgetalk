import { useNavigate } from 'react-router-dom';

export function ParentMain() {
    const navigate = useNavigate();

    return (
        <div>
            <button onClick={() => navigate('../reportlist')}>nỗi lòng con cái</button>
            <button onClick={() => navigate('../information')}>lấy thông tin</button>
        </div>
    );
}
