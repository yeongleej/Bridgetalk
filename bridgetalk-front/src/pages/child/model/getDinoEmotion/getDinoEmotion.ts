export function getDinoEmotion(emotion: string) {
  let result = 'idle';

  if (emotion.includes('긍정')) {
    result = 'cute';
  } else if (emotion.includes('부정')) {
    result = 'no';
  } else if (emotion.includes('슬픔')) {
    result = 'sick';
  } else if (emotion.includes('행복')) {
    result = 'happy';
  } else if (emotion.includes('화남')) {
    result = 'yes';
  }
  console.log(emotion);
  return result;
}
