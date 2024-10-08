import './styles/index.css';
import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';
import { 
  createBrowserRouter,
  RouterProvider 
} from 'react-router-dom';
import Root from './pages/Root'
import HomePage from './pages/HomePage';
import ProfilePage from './pages/ProfilePage';
import NotFoundPage from './pages/NotFoundPage';
import ProtectedRoute from './auth/ProtectedRoute';
import { AuthProvider } from './auth/AuthContext';

export const router = createBrowserRouter([
  {
    element: <Root/>,
    errorElement: <NotFoundPage/>,
    children: [
      {
        // element: <ProtectedRoute/>,
        children: [
          {
            path: '/profile',
            element: <ProfilePage/>
          },
        ]
      },
      {
        path: '/home',
        element: <HomePage/>,
      },
      {
        path: '/about',
        // element:
      },
      {
        path: '/contacts',
        // element:
      }
    ]
  }
], {basename: "/ui"});

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <RouterProvider router={router}/>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();