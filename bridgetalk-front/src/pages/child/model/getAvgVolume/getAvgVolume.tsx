/**
 * getAvgVolume: 클로저(getAvg)를 활용해서 작성한 함수
 * 녹음이 새로 시작될 때 마다 새로 호출해줘야함
 */
export const getAvgVolume = function getAvgVolume() {
  let count = 0;
  let volumes = 0;

  function getAvg(volume: number) {
    volumes += volume;
    count++;
    return Math.round(volumes / count);
  }

  return getAvg;
};
