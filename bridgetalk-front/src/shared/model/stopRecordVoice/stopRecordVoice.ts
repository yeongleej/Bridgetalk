import { MutableRefObject } from 'react';

export function stopRecordVoice(recorderRef: MutableRefObject<MediaRecorder | null>) {
    if (recorderRef.current && recorderRef.current.state === 'recording') {
        recorderRef.current.stop();
    }
}
