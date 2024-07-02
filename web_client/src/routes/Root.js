import React from "react";
import Navbar from "../components/common/Navbar"
import { Outlet } from "react-router-dom";

const Root = () => {
    return (
        <div className="main_container">
            <Navbar/>
            <div className="container">
                <Outlet/>
            </div>
        </div>
    );
}

export default Root; 