import { axiosInstance } from './axiosService';

const BASE_URL = 'http://localhost:9090';

const handleApiError = async (error) => {
    if(error.response){
        return {
            message: error.response.data.message,
            status: error.response.status,
            details: error.response.data.details || {}
        }
    } else if(error.request){
        return { message: 'No response received from server', status: null};
    } else {
        return {message: error.message, status: null};
    }
}

export const post = async (url, body) => {
    return axiosInstance
            .post(BASE_URL + url, body)
            .catch(handleApiError);
}

export const register = async (body) => {
    return axiosInstance
            .post(BASE_URL + '/auth/register', body)
            .catch(handleApiError);
}

export const authenticate = async (body) => {
    return axiosInstance.
            post(BASE_URL + '/auth/login', body)
            .catch
}

// Interceptor to include JWT token stored in browser for all outgoing HTTP requests
// It automatically includes Authorization header to every outgoing HTTP request
// Ensures that if user is authenticated(has valid token in localStorage), their credentials will be included in all requests allowing server to verify user's identity

// axiosInstance.interceptors.request.use(config => {
//     const token = localStorage.getItem('token');
//     if (token) {
//         config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
// });

