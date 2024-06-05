import React, { useEffect, useState } from 'react';
import { useMediaQuery } from 'react-responsive';
import { Link } from "react-router-dom";
import { ProfileIcon } from './ProfileIcon';
import { AppLogo } from './AppLogo';



export const Navbar = () => {
  const isMobile = useMediaQuery({maxWidth: 767});
  const [isUserAuthenticated, setIsUserAuthenticated] = useState(false);
  
  useEffect(() => {
    // get user's authentication status
  }, [])

  const authButtons = (
    <>
      <Link to={"/login"} id='login_btn'>Login</Link>
      <Link to={"/signup"} id='signup_btn'>Sign Up</Link>
    </>
  );

  const profileImage = (
    <ProfileIcon src={""} alt={"Photo"}/>
  );
  
  const links = [
    {path: "/home", label: "Home"},
    {path: "/about", label: "About"},
    {path: "/contact", label: "Contact"}
  ]

  const desktopVersion = (
    <>
      <ul className="refers">
        {links.map(link => (
          <li key={link.path}>
            <Link to={link.path}>{link.label}</Link>
          </li>
        ))}
      </ul>
      {isUserAuthenticated ? profileImage : authButtons}
    </>
  );

  const mobileVersion = (
    <div className="slide-menu">
      <div className="slide-menu-content">
        
      </div>
    </div>
  );

  return (
    <div className="navbar_container">
      <div className="navbar">
        <AppLogo appName={"InterviewGether"}/>
        {isMobile ? mobileVersion : desktopVersion}
      </div>
    </div>
  );
}