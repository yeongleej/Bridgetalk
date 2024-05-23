import ReactWordcloud from 'react-wordcloud';
import * as S from '@/styles/test/testWordcloud.style';
import { useEffect, useState } from 'react';

interface Word {
    text: string;
    value: number;
}

interface Options {
    rotations: number;
    rotationAngles: [number, number];
    padding: number;
    scale: any;
}

export function TestWordcloud() {
    const [words, setWords] = useState<Word[]>([]);

    const options: Options = {
        rotations: 2,
        rotationAngles: [-90, 0],
        padding: 0,
        scale: 'sqrt',
    };
    return (
        <S.Wrapper>
            <button
                onClick={() => {
                    const arr = [];
                    for (let i = 0; i < 10; i++) {
                        arr.push({
                            text: `글자${i}`,
                            value: i * Math.random() * Math.random() * 20,
                        });
                    }
                    setWords(arr);
                }}
            >
                asdf
            </button>
            <ReactWordcloud words={words} size={[50, 50]} options={options} />
        </S.Wrapper>
    );
}
