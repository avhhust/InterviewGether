import axios from "axios";

export const BASE_URL = 'http://gateway:8000/api' // not neccessary to include full url, since everything is called from same origin

export const redirectToAuthServer = () => {
    window.location.href = 'http://gateway:8000/oauth2/authorization/auth-server';
}

export const api = axios.create();

api.interceptors.response.use(
    response => response,
    error => {
        if(error.response){
            if(error.response.status === 401){
                redirectToAuthServer();
            }
        }
        return Promise.reject(error);
    }
)
