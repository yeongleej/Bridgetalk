export function generateVolumeCheckInterval(
    analyser: AnalyserNode,
    dataArray: Uint8Array,
    bufferLength: number,
    setVolume: (volume: number) => void,
) {
    // 일정 주기마다 볼륨체크하는 인터벌 생성

    let interval = setInterval(() => {
        analyser.getByteFrequencyData(dataArray);
        setVolume(Math.floor((getAudioFrequency(dataArray, bufferLength) / 256) * 100));
    }, 100);

    return interval;
}

function getAudioFrequency(dataArray: Uint8Array, bufferLength: number): number {
    let total = 0;
    for (let i = 0; i < bufferLength; i++) {
        total += dataArray[i];
    }
    return total / bufferLength;
}
