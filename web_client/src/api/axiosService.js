import axios from "axios";
import {router} from "../index"

export const axiosInstance = axios.create();

axiosInstance.interceptors.response.use(
    response => response,
    error => {
        if(error.response.status === 401){
            router.navigate('/login');
        }
        return Promise.reject(error);
    }
)