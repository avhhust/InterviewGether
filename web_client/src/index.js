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
import ProtectedRoutes from './auth/ProtectedRoutes';

export const router = createBrowserRouter([
  {
    element: <Root/>,
    errorElement: <NotFoundPage/>,
    children: [
      {
        element: <ProtectedRoutes/>,
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
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();