export function dateToString(date: string) {
  const dateArray = date.split('T')[0].split('-').join('.');

  return dateArray;
}
