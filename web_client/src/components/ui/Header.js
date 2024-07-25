import React, { useState } from 'react';
import { useMediaQuery } from 'react-responsive';
import { Link } from "react-router-dom";
import { ProfileIcon } from './ProfileIcon';
import { AppLogo } from './AppLogo';
import { redirectToAuthServer } from '../../services/apiService';
import Button from './Button';

const Navbar = () => {
  const isMobile = useMediaQuery({ maxWidth: 767 });
  const [isUserAuthenticated, setIsUserAuthenticated] = useState(false);

  const handleSignIn = (e) => {
    e.preventDefault();
    redirectToAuthServer();
  }

  const authBtn = (
    <Button label={'Sign In'} onClick={handleSignIn} id={'signin_btn'}/>
  );

  const profile = (
    <ProfileIcon src={""} alt={"Photo"} />
  );

  const menu_links = [
    { path: "/home", label: "Home" },
    { path: "/about", label: "About" },
    { path: "/contacts", label: "Contacts" }
  ]

  return (
    <div className="navbar_container">
      <div className="navbar">
        
        <AppLogo appName={"InterviewGether"} link={'/'} />
        
        <div className={'menu ' + isMobile && 'slide_menu'}>
          <ul className="links">
            {menu_links.map(link => (
              <li key={link.path}>
                <Link to={link.path}>{link.label}</Link>
              </li>
            ))}
          </ul>
        </div>

        {isUserAuthenticated ? profile : authBtn}
      </div>
    </div>
  );
}

export default Navbar;