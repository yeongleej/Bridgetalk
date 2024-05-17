self.addEventListener('install', (e) => {
  console.log('installed', e);
});

self.addEventListener('activate', (e) => {
  console.log('activated', e);
});

self.addEventListener('fetch', (e) => {
  console.log('fetched', e);
});
