import axios from 'axios';

const BASE_URL = 'http://localhost:9090';

// Sets up base URL for all requests
const axiosInstance = axios.create();

axiosInstance.interceptors.response.use(
    response => response,
    error => {
        if(error.response.status === 401){
            // ToDo: navigate to login page
        }
        return Promise.reject(error);
    }
)

const handleApiError = async (error) => {
    if(error.response){
        return {
            message: error.response.data.message,
            status: error.response.status,
            errors: error.response.data.details || {}
        }
    } else if(error.request){
        return { message: 'No response received from server', status: null};
    } else {
        return {message: error.message, status: null};
    }
}

export const post = async (url, payload) => {
    return axiosInstance
            .post(BASE_URL + url, payload)
            .catch(handleApiError);
}

export const register = async (payload) => {
    return axiosInstance
            .post(BASE_URL + '/register', payload)
            .catch(handleApiError);
}

export const authenticate = async (payload) => {
    return axiosInstance.
            post(BASE_URL + '/login', payload)
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

