export function registServiceWorker() {
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker
      .register('/serviceWorker.js')
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
  }
}
