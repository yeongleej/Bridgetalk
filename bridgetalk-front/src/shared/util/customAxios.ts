import axios, { AxiosInstance } from 'axios';
import { getCookie } from './cookie';
import Swal from 'sweetalert2';
import { decodeToken } from '../model';

export const customAxios: AxiosInstance = axios.create({
  baseURL: `${process.env.REACT_APP_API_URL}`,
});

customAxios.interceptors.request.use(
  (config) => {
    const accessToken = decodeToken('access');
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);
