import React, { useEffect } from 'react'
import { Outlet } from 'react-router-dom';
import { redirectToAuthServer } from '../services/apiService';
import Button from '../components/ui/Button';

const ProtectedRoutes = () => {
    const user = null;
    // get user context 

    useEffect(() => {
        if(user === null){
            // redirectToAuthServer();
        }
    }, [])


    const handleClick = (e) => {
        e.preventDefault();
        redirectToAuthServer();
    }

    return (
        user ? <Outlet/> : <div>Don't miss out opportunities And Sign In to start your preparation today!<Button label={'Sign In'} onClick={handleClick}/> </div>
    )
}

export default ProtectedRoutes;