export function registServiceWorker() {
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker
      .register('/serviceWorker.js')
      .then(
        (res) =>
          (res.onupdatefound = () => {
            const installingWorker = res.installing;

            if (!installingWorker) return;

            installingWorker.onstatechange = () => {
              if (installingWorker.state === 'installed') {
                if (navigator.serviceWorker.controller) {
                  // 새로운 콘텐츠가 사용 가능함을 사용자에게 알림
                  // console.log('New content is available; please refresh.');
                } else {
                  // 콘텐츠가 캐시되었음을 사용자에게 알림
                  // console.log('Content is cached for offline use.');
                }
              }
            };
          }),
      )
      .catch((err) => {
        // console.log(err);
      });
  }
}
