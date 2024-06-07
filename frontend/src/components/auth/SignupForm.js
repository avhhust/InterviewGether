import React, { useState }  from "react";
import InputField from "../common/InputField.js";
import TextWithLines from "../common/TextWithLines.js"
import { Link, useNavigate } from "react-router-dom";
import api from "../../auth/server-api.js";


const initialState = { email: [], username: [], password: [] }

const SignupForm = () => {    
    const [userData, setUserData] = useState({
        email: "", username: "", password: ""
    }); // stores user input data from the form
    const [errors, setErrors] = useState(initialState); // used to store error messages that are displayed under the input field
    // const [hints, setHints] = useState(initialState);
    const [isLoading, setIsLoading] = useState(false); // used to prevent accidental multiple submittions

    const navigate = useNavigate();

    // preforms validation for only one field and sets error message for that field to be displayed
    const validateField = (field) => {
        const value = userData[field];
        const errorMessages = [];
        if(!value){
            errorMessages.push("This field is required");
        }
        else {
            if(field === "email"){
                if(!value.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)){
                    errorMessages.push("Enter a valid email")
                }
            } else if(field === "username"){
                if(value.length < 3 || value.length > 15){
                    errorMessages.push("Should be between 3 and 15 characters long");
                } 
                if(!value.match(/^(?=.*[a-zA-Z])(?!^\d+$)[a-zA-Z\d]+$/)){
                    errorMessages.push("It can only contain letters and numbers");
                }  
            } else if(field === "password"){
                if(value.length < 8){
                    errorMessages.push("Shoud contain at least 8 symbols");
                }
                if(!value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+/)){
                    errorMessages.push("Should contain at least 1 capital letter and 1 number ")
                }
            }   
        }
        setErrors(prevErrors => ({...prevErrors, [field]: errorMessages}));
        return errorMessages.length === 0;
    }

    // perform validation for each field and returns true/false
    const isFormValid = () => {
        let isValid = true;
        for(const field in userData){
            // check if error already exists to skip unnecessary validation
            if(errors[field].length !== 0){
                isValid = false;
            } 
            // if there are no errors then validate  
            else if(!validateField(field)){
                isValid = false;
            }
        }
        return isValid;
    }

    const handleInput = (e) => {
        const {name, value} = e.target;
        setUserData(
            prevUserData => ({...prevUserData, [name]: value})
        );
    };

    // fucntion dedicated for password input to handle real time validaton 
    const handlePasswordInput = (e) => {
        // extra logic for validating while users typing in password
        // ...  
        handleInput(e);
    }
    
    // Calls validation function to validate field when user unfocus input
    const handleBlur = ({target}) => {
        // setHints(initialState); // hide hints
        validateField(target.name);
    }

    // Implements custom logic for submiting form
    const handleSubmit = async (e) => {
        e.preventDefault();
        // perform validation and if any field is failed then exit function
        // to prevent form submit while there are errors
        if(!isFormValid()) return;
        try {
            setIsLoading(true); 
            const response = await api.post('/signup', userData);
            console.log(response);
            navigate("/home"); // redirect to home page after successful registration
        } catch (error) {
            // Request reached server but server responded with an error
            if(error.response){
                if(error.response.data.details){
                    const details = error.response.data.details;
                    // iterate over exception details which contains 
                    for(const field in details){
                        setErrors(prevErrors => ({...prevErrors, [field]: [details[field]]}));
                    }
                }
                console.error(error.response);
            }
            // Request reached server but no response was recieved  
            else if(error.request){
                // ... logic for 
            }
            // Request didn't reach server
            else {
                // ... logic for displaying network error
                console.error(error);
            }
        } finally{
            setIsLoading(false);
        } 
    };

    return(
        <div className="auth_container" id="signup_cont">
            <h2>Sign Up</h2>
            <form onSubmit={handleSubmit}>
                <InputField
                    type="text" 
                    name="email"
                    value={userData.email}
                    onChange={handleInput}
                    onBlur={handleBlur}
                    placeholder="Email"
                    errors={errors.email}
                />
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
                    onChange={handlePasswordInput}
                    onBlur={handleBlur}
                    placeholder="Password" 
                    errors={errors.password}
                />
                <div>
                    <button className="bar" type="submit" id="auth_btn" disabled={isLoading }>Sign Up</button>
                    <p>Already have an account? <Link to={"/login"}>Login</Link></p>
                </div>
            </form>
            <TextWithLines text="or"/>
            <button id="google" className="bar">Signup with Google</button>
            <button id="github" className="bar">Signup with GitHub</button>
            <div className="agreement">
                <p>By signing up, you agree to our <Link to={"/"}>Terms of Service</Link> and <Link to={"/"}>Privacy Policy</Link></p>
            </div>
        </div>
    );
}

export default SignupForm;