import './index.css';
import "./styles/styles.css";
import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';
import { 
  createBrowserRouter,
  RouterProvider 
} from 'react-router-dom';
import Root from './routes/Root'
import LoginPage from './routes/LoginPage';
import HomePage from './routes/HomePage';
import SignupPage from './routes/SignupPage';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Root/>,
    children: [
      {
        path: 'home',
        element: <HomePage/>,
      },
      {
        path: 'login',
        element: <LoginPage/>,
      },
      {
        path: 'register',
        element: <SignupPage/>,
      },
      {
        path: 'about',
        // element:
      },
      {
        path: 'contacts',
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