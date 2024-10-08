import React, { useState } from "react";
import InputField from "../common/InputField.js";
import TextWithLines from "../common/TextWithLines.js";
import SocialAuthButtons from "../common/SocialAuthButtons.js";
import { Link, useNavigate } from "react-router-dom";
import * as authAPI from '../../api/authApi.js'

const LoginForm = () => {
    const[userData, setUserData] = useState({
        username: '', 
        password: '',
    });
    const [errors, setErrors] = useState({
        email: [], username: [], password: []
    });
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();


    const validateField = (field) => {
        const value = userData[field];
        const errorMessages = [];
        if(!value) {
            errorMessages.push("This field is required");
        }
        setErrors(prevErrors => ({...prevErrors, [field]: errorMessages}));
        return errorMessages.length === 0;
    }

    const isFormValid = () => {
        let isValid = true;
        for(const field in userData){
            if(!validateField(field)) isValid = false;
        }
        return isValid;
    }

    const handleInput = (e) => {
        const {name, value} = e.target;
        setUserData(
            prevUserData => ({...prevUserData, [name]: value})
        );
    };

    const handleBlur = ({target}) => {
        validateField(target.name);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if(!isFormValid()) return;
        try{
            setIsLoading(true);
            authAPI.authenticate(userData);
            navigate('/home');
        } catch(error){
            console.error(error);
        } finally {
            setIsLoading(false);
        }
    }

    return(
        <div className="auth_container" id="login_cont">
            <h2>Log In</h2>
            <form onSubmit={handleSubmit}>
                <InputField
                    type="text" 
                    name="username"
                    value={userData.username}
                    onChange={handleInput}
                    onBlur={handleBlur}
                    placeholder="Username" 
                    errors={errors.username}
                />
                <InputField
                    type="password" 
                    name="password"
                    value={userData.password}
                    onChange={handleInput}
                    onBlur={handleBlur}
                    placeholder="Password"
                    errors={errors.password}
                />
                <div>
                    <button className="bar" type={"submit"} id={"auth_btn"} disabled={isLoading}>Log In</button>
                    <p>Don't have an account? <Link to={"/register"}>Sign up</Link></p>
                </div>
            </form>
            <TextWithLines text="or"/>
            <SocialAuthButtons/>
        </div>
    );
}

export default LoginForm;