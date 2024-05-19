export const importAll = (r: __WebpackModuleApi.RequireContext) => {
  return r.keys().map(r);
};

// 특정 디렉토리의 모든 이미지 파일 가져오기
export const imageUrls: any = importAll(require.context('../../../public/assets', true, /\.(png|jpe?g|svg)$/));

// 특정 디렉토리의 모든 GLB 파일 가져오기
export const glbUrls: any = importAll(require.context('../../../public/assets', true, /\.glb$/));
