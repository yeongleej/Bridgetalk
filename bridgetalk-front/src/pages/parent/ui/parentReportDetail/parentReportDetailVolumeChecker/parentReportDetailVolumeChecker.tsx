import { useVoiceStore } from '@/pages/parent/store';
import { useEffect } from 'react';

export function ParentReportDetailVolumeChecker() {
    const volume = useVoiceStore((state) => state.volume);

    useEffect(() => {
        console.log(volume);
    }, [volume]);

    return (
        <>
            <div>음량표시</div>
            <div>음량표시공룡</div>
        </>
    );
}
