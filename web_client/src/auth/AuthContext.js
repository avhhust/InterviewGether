import React, { createContext, useState, useContext, useEffect } from 'react';
import { api, redirectToAuthServer } from '../services/apiService';

export const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [authUser, setAuthUser] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const login = () => {
    setIsAuthenticated(false);
  }

  const logout = () => {

    setIsAuthenticated(false);
  }

  return (
    <AuthContext.Provider value={{ authUser, isAuthenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};