import axios from "axios";

// Sets up base URL for all requests
const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1'
});

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

export default api;