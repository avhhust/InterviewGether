import "./styles/styles.css";
import React from "react"
import LoginPage from "./components/page/LoginPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import SignupPage from "./components/page/SignupPage";
import HomePage from "./components/page/HomePage";
import { Navbar } from "./components/common/Navbar";

function App(){
    return(
        <BrowserRouter>
            <Navbar/>
            <Routes>
                <Route path="/home" element={<HomePage/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/signup" element={<SignupPage/>}/>
                <Route path="/profile" element={""}/>
            </Routes>
        </BrowserRouter>
    )
}

export default App;