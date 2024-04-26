export async function getAudioInputList(inputType: string) {
    return (await navigator.mediaDevices.enumerateDevices()).filter((device) => device.kind === inputType);
}
