export function BackButton() {
    return (
        <button
            onClick={() => {
                window.history.back();
            }}
        >
            뒤로가기
        </button>
    );
}
