import { css } from 'styled-components';

/**
 * 메인 페이지 색상 팔레트
 * 사용 예: ${color(0.5).main}
 * @param alpha
 * @returns
 */
export function color(alpha: number) {
  return {
    main: `rgba(255, 97, 97, ${alpha})`,
    sub: `rgba(255, 159, 153, ${alpha})`,
    line: `rgba(255, 232, 232, ${alpha})`,
    light: `rgba(255, 232, 232, ${alpha})`,
    gray: `rgba(177, 177, 177, ${alpha})`,
    white: `rgba(255, 255, 255, ${alpha})`,
    dark: `rgba(64, 64, 64, ${alpha})`,
    black: `rgba(17, 17, 17, ${alpha})`,
    _main: `rgba(255, 65, 65,${alpha})`,
  };
}

export const bg = css`
  display: flex;
  justify-content: center;
  align-items: center;

  width: 100svw;
  height: 100svh;
`;

export const textShadowRed = css`
  text-shadow: 0.2svw 0 0 ${color(1).main}, 0.2svw 0.2svw 0 ${color(1).main}, 0 0.2svw 0 ${color(1).main},
    -0.2svw 0.2svw 0 ${color(1).main}, -0.2svw 0svw 0 ${color(1).main}, -0.2svw -0.2svw 0 ${color(1).main},
    0svw -0.2svw 0 ${color(1).main}, 0.2svw -0.2svw 0 ${color(1).main}, 0 1svh 0.8svh ${color(1).black};
`;

export const backButton = css`
  position: fixed;
  top: 3svh;
  left: 3svw;
`;

export const insetShadow = css`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  content: '';
`;

/**
 * button : 버튼 CSS
 * font-size, width, height 따로 지정 필요
 */
export const button = css`
  background-color: ${color(1).main};

  border: none;

  color: ${color(1).white};
  font-family: 'DNF';

  display: flex;
  align-items: center;
  justify-content: center;
  gap: 2svw;

  border-radius: 7svw;

  position: relative;
  box-shadow: 0 0.5svh 0.4svh ${color(0.5).black};

  &::after {
    ${insetShadow}

    border-radius: 7svw;
    box-shadow: inset 0 1svh 1svh ${color(0.5).white};
  }

  &::before {
    ${insetShadow}

    border-radius: 7svw;
    box-shadow: inset 0 -1svh 1svh ${color(0.25).black};
  }
`;
