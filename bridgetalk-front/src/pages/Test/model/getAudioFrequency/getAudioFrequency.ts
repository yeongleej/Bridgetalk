export function getAudioFrequency(dataArray: Uint8Array, bufferLength: number) {
    let total = 0;
    for (let i = 0; i < bufferLength; i++) {
        total += dataArray[i];
    }
    return total / bufferLength;
}
