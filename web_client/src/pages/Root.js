import React from "react";
import Header from "../components/ui/Header"
import { Outlet } from "react-router-dom";
import Footer from "../components/ui/Footer";

const Root = () => {
    return (
        <div className="main_container">
            <Header/>
            <div className="page">
                <Outlet/>            
            </div>
            <Footer/>
        </div>
    );
}

export default Root; 