const getPixelPos = (x: number, y: number, width: number): number => {
  return (y * width + x) * 4;
};

const matchStartColor = (data: Uint8ClampedArray, pos: number, startColor: number[]): boolean => {
  return (
    data[pos] === startColor[0] &&
    data[pos + 1] === startColor[1] &&
    data[pos + 2] === startColor[2] &&
    data[pos + 3] === startColor[3]
  );
};

const colorPixel = (data: Uint8ClampedArray, pos: number, color: number[]): void => {
  data[pos] = color[0];
  data[pos + 1] = color[1];
  data[pos + 2] = color[2];
  data[pos + 3] = color[3];
};

export const fill = (context: CanvasRenderingContext2D, startX: number, startY: number, fillColor: number[]): void => {
  const imageData = context.getImageData(0, 0, context.canvas.width, context.canvas.height);
  const data = imageData.data;
  const width = imageData.width;
  const height = imageData.height;
  const startPos = getPixelPos(startX, startY, width);
  const startColor = [data[startPos], data[startPos + 1], data[startPos + 2], data[startPos + 3]];
  const stack = [[startX, startY]];

  // console.log('fill start');

  while (stack.length) {
    const [startX, startY] = stack.pop()!;
    let x = startX;
    let y = startY;
    let pos = getPixelPos(x, y, width);

    while (y >= 0 && matchStartColor(data, pos, startColor)) {
      pos -= width * 4;
      y--;
    }
    pos += width * 4;
    y++;

    let reachLeft = false;
    let reachRight = false;

    while (y < height && matchStartColor(data, pos, startColor)) {
      // console.log('paint');

      colorPixel(data, pos, fillColor);

      if (x > 0) {
        if (matchStartColor(data, pos - 4, startColor)) {
          if (!reachLeft) {
            stack.push([x - 1, y]);
            reachLeft = true;
          }
        } else if (reachLeft) {
          reachLeft = false;
        }
      }

      if (x < width - 1) {
        if (matchStartColor(data, pos + 4, startColor)) {
          if (!reachRight) {
            stack.push([x + 1, y]);
            reachRight = true;
          }
        } else if (reachRight) {
          reachRight = false;
        }
      }

      pos += width * 4;
      y++;
    }
  }

  context.putImageData(imageData, 0, 0);
};
