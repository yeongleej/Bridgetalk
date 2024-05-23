// import { useState, useEffect, useRef, useContext } from 'react';
// import { LineWidthType, ToolType } from '../../util/toolType';
// import { Pen, Tool, Eraser, ColorExtract, ColorFill } from '../../util/tool';
// import { DispatcherContext } from '../../context';
// import { CLEAR_EVENT, REDO_EVENT, UNDO_EVENT } from '../../util/dispatcher/event';
// import SnapShot from '../../util/snapshot';
// import Snapshot from '../../util/snapshot';

// export function Canvas(props) {
//   const { toolType, lineWidthType, setColor } = props;
//   const [tool, setTool] = useState(null);
//   const canvasRef = useRef(null);
//   const dispatcherContext = useContext(DispatcherContext);
//   const [snapshot] = useState(new SnapShot());

//   useEffect(() => {
//     switch (toolType) {
//       case ToolType.PEN:
//         setTool(new Pen());
//         break;
//       case ToolType.ERASER:
//         setTool(new Eraser());
//         break;
//       case ToolType.COLOR_EXTRACT:
//         setTool(new ColorExtract(setColor));
//         break;
//       case ToolType.COLOR_FILL:
//         setTool(new ColorFill());
//         break;
//       default:
//         break;
//     }
//   }, [toolType]);

//   useEffect(() => {
//     switch (lineWidthType) {
//       case LineWidthType.THIN:
//         Tool.lineWidthFactor = 1;
//         break;
//       case LineWidthType.MIDDLE:
//         Tool.lineWidthFactor = 2;
//         break;
//       case LineWidthType.BOLD:
//         Tool.lineWidthFactor = 3;
//         break;
//       case LineWidthType.MAXBOLD:
//         Tool.lineWidthFactor = 4;
//         break;
//       default:
//         break;
//     }
//   }, [lineWidthType]);

//   useEffect(() => {
//     const canvas = canvasRef.current;
//     if (canvas) {
//       canvas.height = canvas.clientHeight;
//       canvas.width = canvas.clientWidth;

//       Tool.ctx = canvas.getContext('2d');

//       const ctx = canvas.getContext('2d');
//       if (ctx) {
//         ctx.fillStyle = 'white';
//         ctx.fillRect(0, 0, canvas.width, canvas.height);

//         snapshot.add(ctx.getImageData(0, 0, canvas.width, canvas.height));
//       }

//       const dispatcher = dispatcherContext.dispatcher;
//       const callback = () => {
//         const ctx = canvas.getContext('2d');
//         if (ctx) {
//           ctx.fillStyle = 'white';
//           ctx.fillRect(0, 0, canvas.width, canvas.height);
//         }
//       };
//       dispatcher.on(CLEAR_EVENT, callback);

//       const forward = () => {
//         const ctx = canvas.getContext('2d');
//         if (ctx) {
//           const imageData = snapshot.forward();
//           if (imageData) {
//             ctx.clearRect(0, 0, canvas.width, canvas.height);
//             ctx.putImageData(imageData, 0, 0);
//           }
//         }
//       };
//       dispatcher.on(REDO_EVENT, forward);

//       const back = () => {
//         const ctx = canvas.getContext('2d');
//         if (ctx) {
//           const imageData = snapshot.back();
//           if (imageData) {
//             ctx.clearRect(0, 0, canvas.width, canvas.height);
//             ctx.putImageData(imageData, 0, 0);
//           }
//         }
//       };
//       dispatcher.on(UNDO_EVENT, back);

//       const handleResize = () => {
//         const canvasData = Tool.ctx.getImageData(0, 0, canvas.width, canvas.height);
//         canvas.height = canvas.clientHeight;
//         canvas.width = canvas.clientWidth;
//         Tool.ctx = canvas.getContext('2d');
//         Tool.ctx.fillStyle = 'white';
//         Tool.ctx.fillRect(0, 0, canvas.width, canvas.height);
//         Tool.ctx.putImageData(canvasData, 0, 0);
//       };

//       window.addEventListener('resize', handleResize);

//       return () => {
//         dispatcher.off(CLEAR_EVENT, callback);
//         window.removeEventListener('resize', handleResize);
//       };
//     }
//   }, [canvasRef, dispatcherContext, snapshot]);

//   const onMouseDown = (event) => {
//     if (tool) {
//       tool.onMouseDown(event);
//     }
//   };

//   const onMouseMove = (event) => {
//     if (tool) {
//       tool.onMouseMove(event);
//     }
//   };

//   const onMouseUp = (event) => {
//     if (tool) {
//       tool.onMouseUp(event);
//       snapshot.add(Tool.ctx.getImageData(0, 0, Tool.ctx.canvas.width, Tool.ctx.canvas.height));
//     }
//   };

//   const onTouchStart = (event) => {
//     if (tool) {
//       tool.onTouchStart(event);
//     }
//   };

//   const onTouchMove = (event) => {
//     if (tool) {
//       tool.onTouchMove(event);
//     }
//   };

//   const onTouchEnd = (event) => {
//     if (tool) {
//       tool.onTouchEnd(event);
//       snapshot.add(Tool.ctx.getImageData(0, 0, Tool.ctx.canvas.width, Tool.ctx.canvas.height));
//     }
//   };

//   useEffect(() => {
//     const canvas = canvasRef.current;
//     if (canvas) {
//       canvas.addEventListener('mousedown', onMouseDown);
//       canvas.addEventListener('mousemove', onMouseMove);
//       canvas.addEventListener('mouseup', onMouseUp);

//       canvas.addEventListener('touchstart', onTouchStart);
//       canvas.addEventListener('touchmove', onTouchMove);
//       canvas.addEventListener('touchend', onTouchEnd);

//       return () => {
//         canvas.removeEventListener('mousedown', onMouseDown);
//         canvas.removeEventListener('mousemove', onMouseMove);
//         canvas.removeEventListener('mouseup', onMouseUp);

//         canvas.removeEventListener('touchstart', onTouchStart);
//         canvas.removeEventListener('touchmove', onTouchMove);
//         canvas.removeEventListener('touchend', onTouchEnd);
//       };
//     }
//   }, [canvasRef, onMouseDown, onMouseMove, onMouseUp, onTouchStart, onTouchMove, onTouchEnd, tool]);

//   return <canvas className="canvas" ref={canvasRef} />;
// }
