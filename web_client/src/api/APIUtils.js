import axios from 'axios';


const BASE_URL = 'http://localhost:8080';

const AUTH_URL = 'http://localhost:9090';

// Sets up base URL for all requests
const api = axios.create({
    baseURL: BASE_URL
});

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

export const register = async (payload) => {
    return api.post(AUTH_URL + '/register', payload)
                .catch(handleApiError);
}

export const authenticate = async (payload) => {
    return api.post(AUTH_URL + '/login', payload)
}

// Interceptor to include JWT token stored in browser for all outgoing HTTP requests
// It automatically includes Authorization header to every outgoing HTTP request
// Ensures that if user is authenticated(has valid token in localStorage), their credentials will be included in all requests allowing server to verify user's identity

// api.interceptors.request.use(config => {
//     const token = localStorage.getItem('token');
//     if (token) {
//         config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
// });

