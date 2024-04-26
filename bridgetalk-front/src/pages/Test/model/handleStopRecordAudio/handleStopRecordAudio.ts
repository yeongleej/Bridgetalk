import { Dispatch, MutableRefObject, SetStateAction } from 'react';

export function handleStopRecordAudio(
    voiceRecorderRef: MutableRefObject<MediaRecorder | undefined>,
    setIsRecording: Dispatch<SetStateAction<boolean>>,
) {
    if (voiceRecorderRef.current && voiceRecorderRef.current.state === 'recording') {
        voiceRecorderRef.current.stop();
        setIsRecording(false);
    }
}
