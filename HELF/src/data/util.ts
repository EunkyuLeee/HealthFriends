export const makeArray = (length: number) => new Array(length).fill(null)
export const random = (min: number, max: number): number =>
  Math.round(Math.random() * (max - min)) + min
export const unsplashUrl = (width: number, height: number): string =>
  `https://source.unsplash.com/random/${width}x${height}`
export const avatarUriByName = (name: string) =>
  `https://ui-avatars.com/api/?name=${name.split(' ').join('+')}`
export const currentDate = () : string => {

  const current_date = new Date();

  return (
    '오늘은 ' + current_date.getFullYear() + '년 ' + (current_date.getMonth() + 1) + '월 ' + current_date.getDate() + '일이에요! \n'
    + '현재는 ' +current_date.getHours() + '시 ' + current_date.getMinutes() + '분입니다.'
  )
}
