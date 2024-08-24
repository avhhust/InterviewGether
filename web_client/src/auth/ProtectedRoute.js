import React, { useEffect } from 'react'
import { Outlet } from 'react-router-dom';
import { redirectToAuthServer } from '../services/apiService';
import { useAuth } from './AuthContext';

const ProtectedRoute = () => {
    const {isAuthenticated, login } = useAuth();
    
    return (
        isAuthenticated ? 
        <Outlet/> : <div>Sign in</div>
    ) 
}

export default ProtectedRoute;