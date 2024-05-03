import { customAxios } from '@/shared';

export async function postMakeReport(setReportsId: any) {
  customAxios
    .post(
      `/reports`,
      {},
      {
        headers: {
          Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1dWlkIjoiNTgxMGJmZTAtNTIxOC00MWNkLThiNzEtNzc0MTdlNWI4YjQ0IiwiaWF0IjoxNzE0NTcyMzIxLCJleHAiOjE3MTU3ODE5MjF9.nBXZXPoO1UM4jS5_LaeVttS9l8XMYfStecwvORVOFvM`,
        },
      },
    )
    .then((res) => setReportsId(res.data.reportsId))
    .catch((err) => console.log(err));
}
