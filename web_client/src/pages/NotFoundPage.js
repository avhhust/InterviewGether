import React from 'react'
import Navbar from '../components/ui/Header';
import Footer from '../components/ui/Footer';
import { Link } from 'react-router-dom';

const NotFoundPage = () => {
  
  return (
    <div className='main_container'>
        <Navbar/>
        <div className="page">
            <div className="not_foud_container">
                <div className="wrapper">
                    <h1>404</h1>
                    <h2>Oops... Page not found!</h2>
                    <p>The page you were looking for, doesn't exist or may have moved</p>
                    <Link to={'/home'} className='rounded_btn'>Go Home</Link>
                </div>
            </div>
        </div>
        <Footer/>
    </div>
  )
}

export default NotFoundPage;