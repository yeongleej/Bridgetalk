import * as S from '@/styles/test/testDraw.style';
import { useEffect, useRef, useState } from 'react';

export function TestDraw() {
    const canvasRef = useRef<HTMLCanvasElement>(null);
    const isDrawing = useRef<boolean>(false);
    const [canvasSize, setCanvasSize] = useState<number[]>([0, 0]);
    const [drawingData, setDrawingData] = useState<any>(null);

    let ctx: CanvasRenderingContext2D | null;

    useEffect(() => {
        ctx = canvasRef.current!.getContext('2d');

        function setCanvasArea() {
            if (canvasRef.current) {
                const { offsetWidth, offsetHeight } = canvasRef.current;
                setCanvasSize([offsetHeight, offsetWidth]);
                canvasRef.current.width = offsetWidth;
                canvasRef.current.height = offsetHeight;
                const prevDrawingData = ctx!.getImageData(0, 0, offsetWidth, offsetHeight);
                ctx!.putImageData(prevDrawingData, 0, 0);
            }
        }

        function draw(x: number, y: number) {
            if (ctx) {
                ctx.fillStyle = 'yellow';
                ctx.beginPath();
                ctx.arc(x, y, 5, 0, Math.PI * 2); // 중심 좌표가 (x, y)이고 반지름이 5인 원 그리기
                ctx.fill();
                setDrawingData(ctx.getImageData(0, 0, canvasSize[1], canvasSize[0]));
            }
        }

        function drawStart(e: MouseEvent | TouchEvent) {
            e.preventDefault();
            isDrawing.current = true;
            const clientX = 'touches' in e ? e.touches[0].clientX : e.clientX;
            const clientY = 'touches' in e ? e.touches[0].clientY : e.clientY;
            draw(clientX - canvasRef.current!.offsetLeft, clientY - canvasRef.current!.offsetTop);
        }

        function drawing(e: MouseEvent | TouchEvent) {
            e.preventDefault();
            if (isDrawing.current) {
                const clientX = 'touches' in e ? e.touches[0].clientX : e.clientX;
                const clientY = 'touches' in e ? e.touches[0].clientY : e.clientY;
                draw(clientX - canvasRef.current!.offsetLeft, clientY - canvasRef.current!.offsetTop);
            }
        }

        function stopDraw() {
            isDrawing.current = false;
        }

        if (canvasRef.current) {
            canvasRef.current.addEventListener('mousedown', drawStart);
            canvasRef.current.addEventListener('mousemove', drawing);
            canvasRef.current.addEventListener('mouseup', stopDraw);
            canvasRef.current.addEventListener('touchstart', drawStart);
            canvasRef.current.addEventListener('touchmove', drawing);
            canvasRef.current.addEventListener('touchend', stopDraw);
        }

        window.addEventListener('resize', setCanvasArea);

        return () => {
            if (canvasRef.current) {
                canvasRef.current.removeEventListener('mousedown', drawStart);
                canvasRef.current.removeEventListener('mousemove', drawing);
                canvasRef.current.removeEventListener('mouseup', stopDraw);
                canvasRef.current.removeEventListener('touchstart', drawStart);
                canvasRef.current.removeEventListener('touchmove', drawing);
                canvasRef.current.removeEventListener('touchend', stopDraw);
            }
            window.removeEventListener('resize', setCanvasArea);
        };
    }, []);

    useEffect(() => {
        if (drawingData) {
            ctx!.putImageData(drawingData, 0, 0);
        }
    }, [drawingData]);

    return (
        <S.Wrapper>
            <canvas ref={canvasRef} width={canvasSize[1]} height={canvasSize[0]} />
        </S.Wrapper>
    );
}
