import { customAxios } from '@/shared';

/**
 * getProfile: 부모, 자녀 프로필 정보
 * @param setChildrenList
 * @returns
 */
export async function getProfile(setChildrenList: any) {
  return customAxios
    .get('/profile')
    .then((res) => {
      // 부모의 프로필에서 자녀들의 정보를 빼 childrneList에 저장
      const childrenList: any = [];
      const profileList = res.data.profileList;

      for (let i = 1; i < profileList.length; i++) {
        childrenList.push(profileList[i]);
      }

      setChildrenList(childrenList);
    })
    .catch((err) => {
      // console.log(err);
    });
}
