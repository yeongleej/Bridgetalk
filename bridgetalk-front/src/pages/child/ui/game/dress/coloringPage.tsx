// import React, { useRef, useEffect, useState, useCallback } from 'react';
// import hexToRgba from 'hex-to-rgba';

// const ctxRef = useRef<CanvasRenderingContext2D | null>(null);

// const convertHexToRgba = (color: string) => {
//   const rgbaStr = hexToRgba(color);
//   const rgba = rgbaStr
//     .substring(5, rgbaStr.length - 1)
//     .split(',')
//     .map((str: string) => Number(str));
//   rgba[3] = rgba[3] * 255;
//   return new Uint8ClampedArray(rgba);
// };

// const isValidSquare = (imageData: any, x: number, y: number) => {
//   return x >= 0 && x < imageData.width && y >= 0 && y < imageData.height;
// };

// const getPixelOffset = (imageData: any, x: number, y: number) => {
//   return (y * imageData.width + x) * 4;
// };

// const getPixelColor = (imageData: any, x: number, y: number) => {
//   if (isValidSquare(imageData, x, y)) {
//     const offset = getPixelOffset(imageData, x, y);
//     return imageData.data.slice(offset, offset + 4);
//   } else {
//     return [-1, -1, -1, -1]; // invalid color
//   }
// };

// const isSameColor = (a: Uint8ClampedArray, b: Uint8ClampedArray) => {
//   return a[0] === b[0] && a[1] === b[1] && a[2] === b[2] && a[3] === b[3];
// };

// const setPixel = (imageData: any, x: number, y: number, color: Uint8ClampedArray) => {
//   const offset = (y * imageData.width + x) * 4;
//   imageData.data[offset + 0] = color[0];
//   imageData.data[offset + 1] = color[1];
//   imageData.data[offset + 2] = color[2];
//   imageData.data[offset + 3] = color[3];
// };

// const [drawState, setDrawState] = useState(); // 상태 초기화 필요
// const curColor = useRef('#000000'); // 현재 색상을 저장하는 ref

// // 이벤트로부터 마우스 좌표를 추출하는 함수
// const getCoordinates = (event: MouseEvent): { x: number; y: number } | null => {
//   const canvas = canvasRef.current;
//   if (canvas) {
//     const rect = canvas.getBoundingClientRect();
//     return {
//       x: event.clientX - rect.left,
//       y: event.clientY - rect.top,
//     };
//   }
//   return null;
// };

// const floodFill = (x: number, y: number, fillColor: Uint8ClampedArray) => {
//   const imageData = ctxRef.current.getImageData(0, 0, 800, 600);
//   const visited = new Uint8Array(imageData.width, imageData.height);
//   const targetColor = getPixelColor(imageData, x, y);

//   if (!isSameColor(targetColor, fillColor)) {
//     const stack = [{ x, y }];
//     while (stack.length > 0) {
//       const child = stack.pop();
//       if (!child) return;
//       const currentColor = getPixelColor(imageData, child.x, child.y);
//       if (!visited[child.y * imageData.width + child.x] && isSameColor(currentColor, targetColor)) {
//         setPixel(imageData, child.x, child.y, fillColor);
//         visited[child.y * imageData.width + child.x] = 1;
//         stack.push({ x: child.x + 1, y: child.y });
//         stack.push({ x: child.x - 1, y: child.y });
//         stack.push({ x: child.x, y: child.y + 1 });
//         stack.push({ x: child.x, y: child.y - 1 });
//       }
//     }
//     ctxRef.current.putImageData(imageData, 0, 0);
//   }
// };

// const paintCanvas = useCallback(
//   (event: MouseEvent) => {
//     if (drawState.current === CanvasState.PAINT) {
//       const curPos = getCoordinates(event);
//       if (!curPos) return;
//       floodFill(curPos.x, curPos.y, curColor.current);
//     }
//   },
//   [drawState],
// );

export function ColoringPage() {
  return <></>;
}
