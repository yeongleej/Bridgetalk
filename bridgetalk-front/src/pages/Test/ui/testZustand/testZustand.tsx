import { useCountStore } from '@/pages/Test/store';

export function TeestZustand() {
    const { count, increase, decrease } = useCountStore();

    return (
        <>
            <div style={{ color: 'white' }}>{count}</div>
            <button onClick={increase}>증가</button>
            <button onClick={decrease}>감소</button>
        </>
    );
}
