import { axiosInstance } from './axiosService';

export const BASE_URL = 'http://localhost:9090';

export const getHomeInfo = async () => {
    return axiosInstance.get(BASE_URL + '/home')
                        .catch(error => console.error(error));
}