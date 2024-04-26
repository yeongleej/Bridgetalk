import { MutableRefObject, SetStateAction, Dispatch } from 'react';

export function stopRecordVideo(
    recordRef: MutableRefObject<MediaRecorder | undefined>,
    setIsRecording: Dispatch<SetStateAction<boolean>>,
) {
    if (recordRef.current) {
        recordRef.current.stop();
        setIsRecording(false);
    }
}
